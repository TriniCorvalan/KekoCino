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
import androidx.compose.material3.HorizontalDivider
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
import com.example.kekocino.data.User
import com.example.kekocino.data.registeredUsers
import com.example.kekocino.ui.components.ButtonPrimary
import com.example.kekocino.ui.components.TextField
import com.example.kekocino.ui.components.TitlePrimary
import com.example.kekocino.ui.theme.KekoCinoTheme

/**
 * Pantalla de inicio de sesión.
 *
 * Componentes UI que cubre:
 *  - Input: campos de correo y contraseña ([TextField]).
 *  - Botón: "Entrar" ([ButtonPrimary]).
 *  - Vínculos: "¿Olvidaste tu contraseña?" Y "Crear una cuenta" ([TextButton]).
 *  - Texto: título, subtítulo y mensaje de error.
 *
 * La validación compara contra [registeredUsers] (arreglo en memoria que
 * simula la base de datos).
 *
 * @param onLoginSuccess se ejecuta al entrar con credenciales correctas,
 *   recibe el [User] que inició sesión.
 * @param onGoToRegister navega a la pantalla de registro.
 * @param onGoToRecover navega a la pantalla de recuperar contraseña.
 */
@Composable
fun LoginScreen(
    onLoginSuccess: (User) -> Unit,
    onGoToRegister: () -> Unit,
    onGoToRecover: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf<String?>(null) }

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
            TitlePrimary(text = "Bienvenida a KekoCino")

            Text(
                text = "Inicia sesión para ver tu minuta semanal",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Input: correo electrónico.
            TextField(
                value = email,
                onValueChange = { email = it; loginError = null },
                label = "Correo electrónico",
                keyboardType = KeyboardType.Email
            )

            // Input: contraseña con botón de mostrar/ocultar.
            TextField(
                value = password,
                onValueChange = { password = it; loginError = null },
                label = "Contraseña",
                isPassword = true,
                errorMessage = loginError
            )

            // Botón principal.
            ButtonPrimary(
                text = "Entrar",
                onClick = {
                    val user = registeredUsers.find {
                        it.email.trim() == email.trim() && it.password == password
                    }
                    if (user != null) {
                        onLoginSuccess(user)
                    } else {
                        loginError = "Correo o contraseña incorrectos. Intenta nuevamente."
                    }
                }
            )

            // Vínculo: recuperar contraseña.
            TextButton(onClick = onGoToRecover) {
                Text("¿Olvidaste tu contraseña?")
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            // Vínculo: ir al registro.
            TextButton(onClick = onGoToRegister) {
                Text("¿No tienes cuenta? Crear una cuenta")
            }
        }
    }
}

// ---------------------------------------------------------------------------
// PREVIEWS — permiten ver la pantalla directamente en Android Studio sin
// ejecutar la app. El panel "Design" o "Split" las muestra en tiempo real.
// ---------------------------------------------------------------------------

/** Vista previa en teléfono (tamaño normal). */
@Preview(showBackground = true, name = "Login - Teléfono")
@Composable
fun LoginScreenPhonePreview() {
    KekoCinoTheme {
        LoginScreen(onLoginSuccess = {}, onGoToRegister = {}, onGoToRecover = {})
    }
}

/** Vista previa en tablet (800dp de ancho). Demuestra que el formulario
 *  se centra y no se estira de borde a borde en pantallas grandes. */
@Preview(showBackground = true, name = "Login - Tablet", widthDp = 800, heightDp = 1280)
@Composable
fun LoginScreenTabletPreview() {
    KekoCinoTheme {
        LoginScreen(onLoginSuccess = {}, onGoToRegister = {}, onGoToRecover = {})
    }
}
