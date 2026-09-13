package com.example.kekocino.ui.navigation

/**
 * Rutas de navegación de la app, centralizadas para no repetir strings sueltos
 * en cada llamada a [androidx.navigation.NavController.navigate].
 *
 * [DETAIL] y [TIMER] llevan un argumento en la ruta (el patrón `{nombre}` de
 * Navigation Compose); los helpers [detail] y [timer] arman la ruta concreta
 * con el valor real al navegar.
 */
object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val RECOVER = "recover"
    const val MENU = "menu"
    const val SHOPPING = "shopping"

    const val RECIPE_ID_ARG = "recipeId"
    const val DETAIL = "detail/{$RECIPE_ID_ARG}"

    const val MINUTES_ARG = "minutes"
    const val TIMER = "timer/{$MINUTES_ARG}"

    /** Construye la ruta concreta al detalle de la receta [recipeId]. */
    fun detail(recipeId: Int) = "detail/$recipeId"

    /** Construye la ruta concreta al temporizador con [minutes] minutos iniciales. */
    fun timer(minutes: Int) = "timer/$minutes"
}
