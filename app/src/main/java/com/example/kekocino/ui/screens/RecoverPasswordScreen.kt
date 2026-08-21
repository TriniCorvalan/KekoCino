package com.example.kekocino.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.kekocino.ui.components.ButtonPrimary
import com.example.kekocino.ui.components.TextField
import com.example.kekocino.ui.components.TitlePrimary
import com.example.kekocino.ui.theme.KekoCinoTheme

/**
 * Pantalla de recuperación de contraseña.
 *
 * El envío es simulado: solo muestra un
 * mensaje de confirmación (sin backend real).
 *
 * @param onGoToLogin navega de vuelta a la pantalla de inicio de sesión.
 */
@Composable
fun RecoverPasswordScreen(onGoToLogin: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var emailSent by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 480.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TitlePrimary(text = "Recuperar contraseña")

            Text(
                text = "Escribe tu correo y te enviaremos las instrucciones para crear una nueva contraseña.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Input: correo electrónico.
            TextField(
                value = email,
                onValueChange = { email = it; emailError = null; emailSent = false },
                label = "Correo electrónico",
                keyboardType = KeyboardType.Email,
                errorMessage = emailError
            )

            // Botón principal.
            ButtonPrimary(
                text = "Enviar instrucciones",
                onClick = {
                    if (email.isBlank() || !email.contains("@")) {
                        emailError = "Escribe un correo electrónico válido."
                    } else {
                        emailSent = true
                    }
                }
            )

            // Texto: mensaje de confirmación simulado (aparece tras presionar el botón).
            if (emailSent) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(
                        text = "✓  Si ese correo está registrado, recibirás las instrucciones en los próximos minutos. Revisa también tu carpeta de spam.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // Vínculo: volver al login.
            TextButton(onClick = onGoToLogin) {
                Text("Volver al inicio de sesión")
            }
        }
    }
}

// ---------------------------------------------------------------------------
// PREVIEWS
// ---------------------------------------------------------------------------

@Preview(showBackground = true, name = "Recuperar - Teléfono")
@Composable
fun RecoverPasswordScreenPhonePreview() {
    KekoCinoTheme {
        RecoverPasswordScreen(onGoToLogin = {})
    }
}

@Preview(showBackground = true, name = "Recuperar - Tablet", widthDp = 800, heightDp = 1280)
@Composable
fun RecoverPasswordScreenTabletPreview() {
    KekoCinoTheme {
        RecoverPasswordScreen(onGoToLogin = {})
    }
}
