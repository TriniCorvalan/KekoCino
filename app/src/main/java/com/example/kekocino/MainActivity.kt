package com.example.kekocino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.kekocino.data.Recipe
import com.example.kekocino.data.User
import com.example.kekocino.ui.screens.LoginScreen
import com.example.kekocino.ui.screens.MinutaScreen
import com.example.kekocino.ui.screens.RecipeDetailScreen
import com.example.kekocino.ui.screens.RecoverPasswordScreen
import com.example.kekocino.ui.screens.RegisterScreen
import com.example.kekocino.ui.theme.KekoCinoTheme

/**
 * Pantallas posibles de la app. Funciona como las rutas de un router:
 * cada valor representa una vista distinta que el usuario puede ver.
 */
enum class Screen {
    LOGIN, REGISTER, RECOVER, MENU, DETAIL
}

/**
 * Punto de entrada de la aplicación.
 * Su única responsabilidad es arrancar Compose y aplicar el tema.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KekoCinoTheme {
                KekoCinoApp()
            }
        }
    }
}

/**
 * Composable raíz que gestiona la navegación completa de la app.
 *
 * Guarda tres estados:
 * - [currentScreen]: la pantalla visible en este momento.
 * - [currentUser]: la usuaria que inició sesión (null antes de entrar).
 * - [selectedRecipe]: la receta que la usuaria seleccionó para ver el detalle.
 *
 * [BackHandler] intercepta el botón "atrás" del teléfono para navegar
 * a la pantalla anterior en lugar de cerrar la app.
 */
@Composable
fun KekoCinoApp() {
    var currentScreen by remember { mutableStateOf(Screen.LOGIN) }
    var currentUser by remember { mutableStateOf<User?>(null) }
    var selectedRecipe by remember { mutableStateOf<Recipe?>(null) }

    // Intercept system back button — go to previous screen instead of closing app.
    BackHandler(enabled = currentScreen != Screen.LOGIN) {
        currentScreen = when (currentScreen) {
            Screen.REGISTER, Screen.RECOVER -> Screen.LOGIN
            Screen.DETAIL -> Screen.MENU
            else -> Screen.LOGIN
        }
    }

    when (currentScreen) {
        Screen.LOGIN -> LoginScreen(
            onLoginSuccess = { user ->
                currentUser = user
                currentScreen = Screen.MENU
            },
            onGoToRegister = { currentScreen = Screen.REGISTER },
            onGoToRecover = { currentScreen = Screen.RECOVER }
        )
        Screen.REGISTER -> RegisterScreen(
            onRegisterSuccess = { currentScreen = Screen.LOGIN },
            onGoToLogin = { currentScreen = Screen.LOGIN }
        )
        Screen.RECOVER -> RecoverPasswordScreen(
            onGoToLogin = { currentScreen = Screen.LOGIN }
        )
        Screen.MENU -> MinutaScreen(
            user = currentUser,
            onRecipeClick = { recipe ->
                selectedRecipe = recipe
                currentScreen = Screen.DETAIL
            }
        )
        Screen.DETAIL -> selectedRecipe?.let { recipe ->
            RecipeDetailScreen(
                recipe = recipe,
                onBack = { currentScreen = Screen.MENU }
            )
        }
    }
}
