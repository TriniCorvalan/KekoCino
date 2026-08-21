package com.example.kekocino.ui.screens

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kekocino.data.User
import com.example.kekocino.ui.theme.KekoCinoTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

/** Opciones de minutos disponibles para el temporizador. */
private val MINUTE_OPTIONS = listOf(1, 3, 5, 10, 15, 20, 30)

/** Modos de aviso al terminar la cuenta regresiva. */
private val ALERT_MODES = listOf("Solo destello", "Solo vibración", "Destello y vibración")

/**
 * Dispara una vibración corta usando la API correspondiente según la versión
 * de Android (VibratorManager desde API 31, Vibrator clásico antes).
 */
private fun triggerVibration(context: Context) {
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val manager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        manager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        @Suppress("DEPRECATION")
        vibrator.vibrate(500)
    }
}

/**
 * Temporizador de cocción sin depender del oído: en vez de una alarma sonora,
 * avisa con un **destello de pantalla completa** y/o **vibración**.
 *
 * Componentes UI que cubre (requisito de la entrega):
 *  - Combo box: minutos a temporizar ([ExposedDropdownMenuBox]).
 *  - Radio buttons: tipo de aviso al terminar ([RadioButton]).
 *  - Botones: Iniciar / Pausar / Reiniciar ([Button], [OutlinedButton]).
 *  - Texto: cuenta regresiva en tipografía grande.
 *
 * @param initialMinutes minutos con los que se abre el temporizador (llega
 *   desde el paso de la receta que lo activó).
 * @param user usuaria logueada; sus preferencias de accesibilidad
 *   ([User.visualAlerts], [User.vibration]) definen el modo de aviso inicial.
 * @param onBack navega de vuelta al detalle de la receta.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisualTimerScreen(
    initialMinutes: Int,
    user: User?,
    onBack: () -> Unit
) {
    var selectedMinutes by remember { mutableIntStateOf(initialMinutes.coerceIn(1, 30)) }
    var dropdownExpanded by remember { mutableStateOf(false) }

    val defaultMode = when {
        user?.visualAlerts == true && user.vibration -> ALERT_MODES[2]
        user?.vibration == true -> ALERT_MODES[1]
        else -> ALERT_MODES[0]
    }
    var selectedMode by remember { mutableStateOf(defaultMode) }

    var remainingSeconds by remember { mutableIntStateOf(selectedMinutes * 60) }
    var isRunning by remember { mutableStateOf(false) }
    var isAlerting by remember { mutableStateOf(false) }
    var flashOn by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val shouldFlash = selectedMode == ALERT_MODES[0] || selectedMode == ALERT_MODES[2]
    val shouldVibrate = selectedMode == ALERT_MODES[1] || selectedMode == ALERT_MODES[2]

    // Cuenta regresiva: se reinicia cada vez que isRunning cambia (Iniciar/Pausar).
    LaunchedEffect(isRunning) {
        while (isRunning && remainingSeconds > 0) {
            delay(1000.milliseconds)
            remainingSeconds--
        }
        if (isRunning && remainingSeconds == 0) {
            isRunning = false
            isAlerting = true
        }
    }

    // Destello: alterna el color de fondo mientras isAlerting esté activo.
    LaunchedEffect(isAlerting, shouldFlash) {
        if (isAlerting && shouldFlash) {
            while (isAlerting) {
                flashOn = !flashOn
                delay(400.milliseconds)
            }
        } else {
            flashOn = false
        }
    }

    // Vibración: se repite mientras isAlerting esté activo.
    LaunchedEffect(isAlerting, shouldVibrate) {
        while (isAlerting && shouldVibrate) {
            triggerVibration(context)
            delay(800.milliseconds)
        }
    }

    val backgroundColor = if (flashOn) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.background

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Temporizador visual", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver a la receta"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundColor),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 480.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Cuenta regresiva en tipografía grande, legible desde lejos.
                Text(
                    text = "%02d:%02d".format(remainingSeconds / 60, remainingSeconds % 60),
                    style = MaterialTheme.typography.displayLarge
                )

                if (isAlerting) {
                    Text(
                        text = "¡Tiempo cumplido!",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                HorizontalDivider()

                // Combo box: minutos a temporizar.
                Text(
                    text = "Minutos",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                ExposedDropdownMenuBox(
                    expanded = dropdownExpanded,
                    onExpandedChange = { if (!isRunning) dropdownExpanded = it }
                ) {
                    OutlinedTextField(
                        value = "$selectedMinutes min",
                        onValueChange = {},
                        readOnly = true,
                        enabled = !isRunning,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownExpanded)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                    )
                    ExposedDropdownMenu(
                        expanded = dropdownExpanded,
                        onDismissRequest = { dropdownExpanded = false }
                    ) {
                        MINUTE_OPTIONS.forEach { minutes ->
                            DropdownMenuItem(
                                text = { Text("$minutes min", style = MaterialTheme.typography.bodyLarge) },
                                onClick = {
                                    selectedMinutes = minutes
                                    remainingSeconds = minutes * 60
                                    isAlerting = false
                                    dropdownExpanded = false
                                }
                            )
                        }
                    }
                }

                HorizontalDivider()

                // Radio buttons: tipo de aviso.
                Text(
                    text = "Avisarme con",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                ALERT_MODES.forEach { mode ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = selectedMode == mode,
                                onClick = { selectedMode = mode }
                            )
                            .padding(vertical = 2.dp)
                    ) {
                        RadioButton(
                            selected = selectedMode == mode,
                            onClick = null
                        )
                        Text(
                            text = mode,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Botones: Iniciar / Pausar / Reiniciar.
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            isAlerting = false
                            isRunning = !isRunning
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                    ) {
                        Text(if (isRunning) "Pausar" else "Iniciar", style = MaterialTheme.typography.labelLarge)
                    }
                    OutlinedButton(
                        onClick = {
                            isRunning = false
                            isAlerting = false
                            remainingSeconds = selectedMinutes * 60
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                    ) {
                        Text("Reiniciar", style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }
    }
}

// ---------------------------------------------------------------------------
// PREVIEWS
// ---------------------------------------------------------------------------

@Preview(showBackground = true, name = "Temporizador - Teléfono")
@Composable
fun VisualTimerScreenPhonePreview() {
    KekoCinoTheme {
        VisualTimerScreen(
            initialMinutes = 5,
            user = User(name = "María González", email = "demo@kekocino.cl", password = ""),
            onBack = {}
        )
    }
}

@Preview(showBackground = true, name = "Temporizador - Tablet", widthDp = 800, heightDp = 1280)
@Composable
fun VisualTimerScreenTabletPreview() {
    KekoCinoTheme {
        VisualTimerScreen(
            initialMinutes = 5,
            user = User(name = "María González", email = "demo@kekocino.cl", password = ""),
            onBack = {}
        )
    }
}
