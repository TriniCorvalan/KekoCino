package com.example.kekocino.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kekocino.data.User
import com.example.kekocino.data.registeredUsers

/**
 * Formulario de registro y alta en el arreglo de usuarios.
 * La pantalla solo muestra el estado y llama a estas funciones.
 */
class RegisterViewModel : ViewModel() {
    var name by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set

    val preferences = listOf("Sin restricción", "Vegetariana", "Sin gluten")
    var selectedPreference by mutableStateOf(preferences[0])
        private set

    val householdOptions = (1..6).map { if (it == 1) "1 persona" else "$it personas" }
    var selectedHousehold by mutableStateOf(householdOptions[0])
        private set

    var visualAlerts by mutableStateOf(true)
        private set
    var vibration by mutableStateOf(true)
        private set
    var showTranscripts by mutableStateOf(true)
        private set
    var acceptTerms by mutableStateOf(false)
        private set
    var receiveNewsletter by mutableStateOf(false)
        private set

    var nameError by mutableStateOf<String?>(null)
        private set
    var emailError by mutableStateOf<String?>(null)
        private set
    var passwordError by mutableStateOf<String?>(null)
        private set

    val isFormValid: Boolean
        get() = name.isNotBlank()
            && email.isNotBlank()
            && password.length >= 6
            && password == confirmPassword
            && acceptTerms

    val confirmPasswordError: String?
        get() = if (confirmPassword.isNotEmpty() && confirmPassword != password) {
            "Las contraseñas no coinciden."
        } else {
            null
        }

    fun onNameChange(value: String) {
        name = value
        nameError = null
    }

    fun onEmailChange(value: String) {
        email = value
        emailError = null
    }

    fun onPasswordChange(value: String) {
        password = value
        passwordError = null
    }

    fun onConfirmPasswordChange(value: String) {
        confirmPassword = value
    }

    fun onPreferenceChange(value: String) {
        selectedPreference = value
    }

    fun onHouseholdChange(value: String) {
        selectedHousehold = value
    }

    fun onVisualAlertsChange(value: Boolean) {
        visualAlerts = value
    }

    fun onVibrationChange(value: Boolean) {
        vibration = value
    }

    fun onTranscriptsChange(value: Boolean) {
        showTranscripts = value
    }

    fun onAcceptTermsChange(value: Boolean) {
        acceptTerms = value
    }

    fun onNewsletterChange(value: Boolean) {
        receiveNewsletter = value
    }

    fun register(): Boolean {
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
        if (!valid) return false

        val householdSize = selectedHousehold.first().digitToInt()
        registeredUsers.add(
            User(
                name = name.trim(),
                email = email.trim(),
                password = password,
                preference = selectedPreference,
                householdSize = householdSize,
                visualAlerts = visualAlerts,
                vibration = vibration,
                showTranscripts = showTranscripts
            )
        )
        return true
    }
}
