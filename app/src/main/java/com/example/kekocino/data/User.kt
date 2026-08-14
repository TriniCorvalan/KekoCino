package com.example.kekocino.data

/**
 * Representa a una usuaria registrada en la app.
 *
 * @param name nombre completo de la usuaria.
 * @param email correo electrónico, usado como identificador para iniciar sesión.
 * @param password contraseña de acceso.
 * @param preference preferencia alimentaria seleccionada en el registro
 *   (ej.: "Sin restricción", "Vegetariana", "Sin gluten").
 * @param householdSize cantidad de personas para las que se cocina (1 a 6).
 */
data class User(
    val name: String,
    val email: String,
    val password: String,
    val preference: String = "Sin restricción",
    val householdSize: Int = 1
)

/**
 * Lista de usuarias registradas. Simula una base de datos en memoria.
 *
 * Se usa mutableListOf para poder agregar nuevas usuarias desde la
 * pantalla de Registro sin reiniciar la app.
 *
 * Incluye una usuaria de prueba para poder iniciar sesión de inmediato
 * durante el desarrollo y la evaluacion del proyecto.
 */
val registeredUsers = mutableListOf(
    User(
        name = "Demo User",
        email = "demo@kekocino.cl",
        password = "123456",
        preference = "Sin restricción",
        householdSize = 4
    )
)
