package com.example.kekocino.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kekocino.data.User
import com.example.kekocino.data.registeredUsers

/**
 * Estado y validación del login. La pantalla solo muestra estos datos.
 */
class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var error by mutableStateOf<String?>(null)
        private set

    fun onEmailChange(value: String) {
        email = value
        error = null
    }

    fun onPasswordChange(value: String) {
        password = value
        error = null
    }

    fun login(): User? {
        val user = registeredUsers.find {
            it.email.trim() == email.trim() && it.password == password
        }
        if (user == null) {
            error = "Correo o contraseña incorrectos. Intenta nuevamente."
        }
        return user
    }
}
