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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kekocino.data.User
import com.example.kekocino.ui.components.AppLogo
import com.example.kekocino.ui.components.ButtonPrimary
import com.example.kekocino.ui.viewmodel.LoginViewModel
import com.example.kekocino.ui.components.TextField
import com.example.kekocino.ui.components.TitlePrimary
import com.example.kekocino.ui.theme.KekoCinoTheme

/**
 * Pantalla de inicio de sesión. Muestra el estado de [LoginViewModel].
 */
@Composable
fun LoginScreen(
    onLoginSuccess: (User) -> Unit,
    onGoToRegister: () -> Unit,
    onGoToRecover: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
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
                AppLogo()

                TitlePrimary(text = "Bienvenida a KekoCino")

                Text(
                    text = "Inicia sesión para ver tu minuta semanal",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Input correo electrónico.
                TextField(
                    value = viewModel.email,
                    onValueChange = viewModel::onEmailChange,
                    label = "Correo electrónico",
                    keyboardType = KeyboardType.Email
                )

                // Input contraseña
                TextField(
                    value = viewModel.password,
                    onValueChange = viewModel::onPasswordChange,
                    label = "Contraseña",
                    isPassword = true,
                    errorMessage = viewModel.error
                )

                // Botón principal.
                ButtonPrimary(
                    text = "Entrar",
                    onClick = {
                        viewModel.login()?.let(onLoginSuccess)
                    }
                )

                TextButton(
                    onClick = onGoToRecover,
                    modifier = Modifier.widthIn(max = 320.dp),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.tertiary
                    )
                ) {
                    Text("¿Olvidaste tu contraseña?")
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                TextButton(
                    onClick = onGoToRegister,
                    modifier = Modifier.widthIn(max = 320.dp),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.tertiary
                    )
                ) {
                    Text("¿No tienes cuenta? Crear una cuenta")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    KekoCinoTheme {
        LoginScreen(
            onLoginSuccess = {},
            onGoToRegister = {},
            onGoToRecover = {}
        )
    }
}
