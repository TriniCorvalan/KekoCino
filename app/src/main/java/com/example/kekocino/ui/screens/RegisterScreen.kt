package com.example.kekocino.ui.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kekocino.ui.components.ButtonPrimary
import com.example.kekocino.ui.components.TextField
import com.example.kekocino.ui.components.TitlePrimary
import com.example.kekocino.ui.viewmodel.RegisterViewModel

/**
 * Pantalla de registro. Muestra el estado de [RegisterViewModel].
 * El dropdown abierto/cerrado se queda aquí porque es solo de la interfaz.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onGoToLogin: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {
    var dropdownExpanded by remember { mutableStateOf(false) }

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
            TitlePrimary(text = "Crear cuenta")

            TextField(
                value = viewModel.name,
                onValueChange = viewModel::onNameChange,
                label = "Nombre completo",
                errorMessage = viewModel.nameError
            )
            TextField(
                value = viewModel.email,
                onValueChange = viewModel::onEmailChange,
                label = "Correo electrónico",
                keyboardType = KeyboardType.Email,
                errorMessage = viewModel.emailError
            )
            TextField(
                value = viewModel.password,
                onValueChange = viewModel::onPasswordChange,
                label = "Contraseña",
                isPassword = true,
                errorMessage = viewModel.passwordError
            )
            TextField(
                value = viewModel.confirmPassword,
                onValueChange = viewModel::onConfirmPasswordChange,
                label = "Repetir contraseña",
                isPassword = true,
                errorMessage = viewModel.confirmPasswordError
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                text = "Preferencia alimentaria",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
            )
            viewModel.preferences.forEach { pref ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = viewModel.selectedPreference == pref,
                            onClick = { viewModel.onPreferenceChange(pref) }
                        )
                        .padding(vertical = 2.dp)
                ) {
                    RadioButton(
                        selected = viewModel.selectedPreference == pref,
                        onClick = null // el Row maneja el clic
                    )
                    Text(
                        text = pref,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                text = "¿Para cuántas personas cocinas?",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
            )
            ExposedDropdownMenuBox(
                expanded = dropdownExpanded,
                onExpandedChange = { dropdownExpanded = it }
            ) {
                OutlinedTextField(
                    value = viewModel.selectedHousehold,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownExpanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                )
                ExposedDropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false }
                ) {
                    viewModel.householdOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option, style = MaterialTheme.typography.bodyLarge) },
                            onClick = {
                                viewModel.onHouseholdChange(option)
                                dropdownExpanded = false
                            }
                        )
                    }
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                text = "Preferencias de accesibilidad",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.onVisualAlertsChange(!viewModel.visualAlerts) }
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = viewModel.visualAlerts,
                    onCheckedChange = viewModel::onVisualAlertsChange
                )
                Text(
                    text = "Avisarme con destellos de pantalla",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.onVibrationChange(!viewModel.vibration) }
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = viewModel.vibration,
                    onCheckedChange = viewModel::onVibrationChange
                )
                Text(
                    text = "Avisarme con vibración",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.onTranscriptsChange(!viewModel.showTranscripts) }
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = viewModel.showTranscripts,
                    onCheckedChange = viewModel::onTranscriptsChange
                )
                Text(
                    text = "Mostrar transcripción de los audios",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.onAcceptTermsChange(!viewModel.acceptTerms) }
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = viewModel.acceptTerms,
                    onCheckedChange = viewModel::onAcceptTermsChange
                )
                Text(
                    text = "Acepto los términos y condiciones",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.onNewsletterChange(!viewModel.receiveNewsletter) }
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = viewModel.receiveNewsletter,
                    onCheckedChange = viewModel::onNewsletterChange
                )
                Text(
                    text = "Quiero recibir consejos nutricionales",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            ButtonPrimary(
                text = "Crear cuenta",
                enabled = viewModel.isFormValid,
                onClick = {
                    if (viewModel.register()) onRegisterSuccess()
                }
            )

            // Vínculo: volver al login.
            TextButton(onClick = onGoToLogin) {
                Text("¿Ya tienes cuenta? Inicia sesión")
            }
        }
    }
}
