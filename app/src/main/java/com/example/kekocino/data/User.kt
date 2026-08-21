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
 * @param visualAlerts si activa, los avisos del temporizador destellan en pantalla
 *   en vez de sonar. Preferencia de accesibilidad para discapacidad auditiva.
 * @param vibration si activa, los avisos del temporizador vibran el dispositivo.
 * @param showTranscripts si activa, las recetas muestran la transcripción escrita
 *   de cualquier contenido narrado en vez de depender del audio.
 */
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
 * Se usa mutableListOf para poder agregar nuevas usuarias desde la
 * pantalla de Registro sin reiniciar la app.
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
