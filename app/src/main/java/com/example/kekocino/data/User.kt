package com.example.kekocino.data

data class User(
    val name: String,
    val email: String,
    val password: String,
    val preference: String = "Sin restricción",
    val householdSize: Int = 1,
    val visualAlerts: Boolean = true,
    val vibration: Boolean = true,
    val showTranscripts: Boolean = true
)

/**
 * Lista de usuarias registradas. Simula una base de datos en memoria.
 *
 * Incluye 5 usuarias de prueba precargadas, cada una con una combinación
 * distinta de preferencias de accesibilidad, para poder iniciar sesión de
 * inmediato durante el desarrollo y la evaluación del proyecto.
 */
val registeredUsers = mutableListOf(
    User(
        name = "Demo User",
        email = "demo@kekocino.cl",
        password = "123456",
        preference = "Sin restricción",
        householdSize = 4,
        visualAlerts = true,
        vibration = true,
        showTranscripts = true
    ),
    User(
        name = "María González",
        email = "maria@kekocino.cl",
        password = "maria123",
        preference = "Vegetariana",
        householdSize = 2,
        visualAlerts = true,
        vibration = false,
        showTranscripts = true
    ),
    User(
        name = "Pedro Soto",
        email = "pedro@kekocino.cl",
        password = "pedro123",
        preference = "Sin gluten",
        householdSize = 3,
        visualAlerts = false,
        vibration = true,
        showTranscripts = true
    ),
    User(
        name = "Ana Rojas",
        email = "ana@kekocino.cl",
        password = "ana12345",
        preference = "Sin restricción",
        householdSize = 1,
        visualAlerts = true,
        vibration = true,
        showTranscripts = false
    ),
    User(
        name = "Jorge Muñoz",
        email = "jorge@kekocino.cl",
        password = "jorge123",
        preference = "Vegetariana",
        householdSize = 5,
        visualAlerts = true,
        vibration = true,
        showTranscripts = true
    )
)

// Map correo -> contraseña, armado al consultar para incluir altas nuevas.
fun passwordsByEmail(): Map<String, String> =
    registeredUsers.associate { it.email to it.password }

// Función inline: corre un bloque y atrapa el error.
inline fun <T> runSafely(block: () -> T): T? {
    return try {
        block()
    } catch (e: Exception) {
        null
    }
}

// try, catch y finally al buscar una cuenta por correo.
fun findAccountMessage(email: String): String {
    var message = ""
    try {
        val key = email.trim()
        val password = passwordsByEmail().getValue(key)
        val user = runSafely { registeredUsers.first { it.email == key } }
        message = if (user != null && password.isNotEmpty()) {
            "Cuenta encontrada: ${user.name}"
        } else {
            "Cuenta encontrada"
        }
    } catch (e: NoSuchElementException) {
        message = "No hay una cuenta con ese correo"
    } finally {
        if (email.trim().isEmpty()) {
            message = "Escribe un correo para buscar"
        }
    }
    return message
}
