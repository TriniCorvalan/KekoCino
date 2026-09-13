package com.example.kekocino.ui.screens

import android.app.Application
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kekocino.data.User
import com.example.kekocino.ui.theme.kekoCinoBackgroundBrush
import com.example.kekocino.ui.viewmodel.TimerViewModel

/**
 * Temporizador visual. Muestra el estado de [TimerViewModel].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisualTimerScreen(
    initialMinutes: Int,
    user: User?,
    onBack: () -> Unit
) {
    val app = LocalContext.current.applicationContext as Application
    val viewModel: TimerViewModel = viewModel {
        TimerViewModel(app, initialMinutes, user)
    }
    var dropdownExpanded by remember { mutableStateOf(false) }

    val alertColor = MaterialTheme.colorScheme.error
    val restBrush = kekoCinoBackgroundBrush()

    Scaffold(
        containerColor = Color.Transparent,
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
                .then(
                    if (viewModel.flashOn) Modifier.background(alertColor) else Modifier.background(restBrush)
                ),
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
                    text = "%02d:%02d".format(viewModel.remainingSeconds / 60, viewModel.remainingSeconds % 60),
                    style = MaterialTheme.typography.displayLarge
                )
                if (viewModel.isAlerting) {
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
                    onExpandedChange = { if (!viewModel.isRunning) dropdownExpanded = it }
                ) {
                    OutlinedTextField(
                        value = "${viewModel.selectedMinutes} min",
                        onValueChange = {},
                        readOnly = true,
                        enabled = !viewModel.isRunning,
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
                        viewModel.minuteOptions.forEach { minutes ->
                            DropdownMenuItem(
                                text = { Text("$minutes min", style = MaterialTheme.typography.bodyLarge) },
                                onClick = {
                                    viewModel.onMinutesChange(minutes)
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
                // [kotlin] iteración forEach
                viewModel.alertModes.forEach { mode ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = viewModel.selectedMode == mode,
                                onClick = { viewModel.onModeChange(mode) }
                            )
                            .padding(vertical = 2.dp)
                    ) {
                        RadioButton(
                            selected = viewModel.selectedMode == mode,
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
                        onClick = viewModel::toggleRunning,
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                    ) {
                        Text(if (viewModel.isRunning) "Pausar" else "Iniciar", style = MaterialTheme.typography.labelLarge)
                    }
                    OutlinedButton(
                        onClick = viewModel::reset,
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
