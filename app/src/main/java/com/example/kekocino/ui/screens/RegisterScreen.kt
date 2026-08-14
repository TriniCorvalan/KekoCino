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
import com.example.kekocino.data.User
import com.example.kekocino.data.registeredUsers
import com.example.kekocino.ui.components.ButtonPrimary
import com.example.kekocino.ui.components.TextField
import com.example.kekocino.ui.components.TitlePrimary

/**
 * Pantalla de registro de nueva usuaria.
 *
 * Componentes UI que cubre (requisito de la entrega):
 *  - Input: nombre, correo, contraseña, repetir contraseña ([TextField]).
 *  - Radio buttons: preferencia alimentaria ([RadioButton]).
 *  - Combo box: cantidad de personas en el hogar ([ExposedDropdownMenuBox]).
 *  - Check list: aceptar términos y recibir consejos ([Checkbox]).
 *  - Botón: "Crear cuenta", deshabilitado hasta que el formulario sea válido ([ButtonPrimary]).
 *  - Vínculo: "Ya tengo cuenta" ([TextButton]).
 *
 * Al registrar: agrega un [User] a [registeredUsers] (arreglo en memoria)
 * y llama a [onRegisterSuccess] para volver al Login.
 *
 * @param onRegisterSuccess se ejecuta tras registrar con éxito.
 * @param onGoToLogin navega de vuelta al inicio de sesión.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onGoToLogin: () -> Unit
) {
    // --- Estado del formulario ---
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Radio buttons: preferencia alimentaria.
    val preferences = listOf("Sin restricción", "Vegetariana", "Sin gluten")
    var selectedPreference by remember { mutableStateOf(preferences[0]) }

    // Combo box: cantidad de personas.
    val householdOptions = (1..6).map { if (it == 1) "1 persona" else "$it personas" }
    var selectedHousehold by remember { mutableStateOf(householdOptions[0]) }
    var dropdownExpanded by remember { mutableStateOf(false) }

    // Check list.
    var acceptTerms by remember { mutableStateOf(false) }
    var receiveNewsletter by remember { mutableStateOf(false) }

    // Mensajes de error por campo.
    var nameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    // El botón "Crear cuenta" solo se activa cuando los campos mínimos están completos.
    val isFormValid = name.isNotBlank()
        && email.isNotBlank()
        && password.length >= 6
        && password == confirmPassword
        && acceptTerms

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

            // --- Inputs ---
            TextField(
                value = name,
                onValueChange = { name = it; nameError = null },
                label = "Nombre completo",
                errorMessage = nameError
            )
            TextField(
                value = email,
                onValueChange = { email = it; emailError = null },
                label = "Correo electrónico",
                keyboardType = KeyboardType.Email,
                errorMessage = emailError
            )
            TextField(
                value = password,
                onValueChange = { password = it; passwordError = null },
                label = "Contraseña",
                isPassword = true,
                errorMessage = passwordError
            )
            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "Repetir contraseña",
                isPassword = true,
                errorMessage = if (confirmPassword.isNotEmpty() && confirmPassword != password)
                    "Las contraseñas no coinciden." else null
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            // --- Radio buttons: preferencia alimentaria ---
            Text(
                text = "Preferencia alimentaria",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
            )
            preferences.forEach { pref ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedPreference == pref,
                            onClick = { selectedPreference = pref }
                        )
                        .padding(vertical = 2.dp)
                ) {
                    RadioButton(
                        selected = selectedPreference == pref,
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

            // --- Combo box: cantidad de personas ---
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
                    value = selectedHousehold,
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
                    householdOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option, style = MaterialTheme.typography.bodyLarge) },
                            onClick = {
                                selectedHousehold = option
                                dropdownExpanded = false
                            }
                        )
                    }
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            // --- Check list ---
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { acceptTerms = !acceptTerms }
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = acceptTerms,
                    onCheckedChange = { acceptTerms = it }
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
                    .clickable { receiveNewsletter = !receiveNewsletter }
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = receiveNewsletter,
                    onCheckedChange = { receiveNewsletter = it }
                )
                Text(
                    text = "Quiero recibir consejos nutricionales",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // --- Botón principal (deshabilitado hasta que el formulario sea válido) ---
            ButtonPrimary(
                text = "Crear cuenta",
                enabled = isFormValid,
                onClick = {
                    var valid = true
                    if (name.isBlank()) {
                        nameError = "Falta escribir tu nombre."
                        valid = false
                    }
                    if (email.isBlank() || !email.contains("@")) {
                        emailError = "Escribe un correo electrónico válido."
                        valid = false
                    }
                    if (password.length < 6) {
                        passwordError = "La contraseña debe tener al menos 6 caracteres."
                        valid = false
                    }
                    if (valid) {
                        // Extraer el número de la opción seleccionada (ej: "3 personas" → 3).
                        val householdSize = selectedHousehold.first().digitToInt()
                        registeredUsers.add(
                            User(
                                name = name.trim(),
                                email = email.trim(),
                                password = password,
                                preference = selectedPreference,
                                householdSize = householdSize
                            )
                        )
                        onRegisterSuccess()
                    }
                }
            )

            // Vínculo: volver al login.
            TextButton(onClick = onGoToLogin) {
                Text("¿Ya tienes cuenta? Inicia sesión")
            }
        }
    }
}
