package com.example.kekocino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kekocino.data.User
import com.example.kekocino.data.weeklyRecipes
import com.example.kekocino.ui.navigation.Routes
import com.example.kekocino.ui.screens.LoginScreen
import com.example.kekocino.ui.screens.MinutaScreen
import com.example.kekocino.ui.screens.RecipeDetailScreen
import com.example.kekocino.ui.screens.RecoverPasswordScreen
import com.example.kekocino.ui.screens.RegisterScreen
import com.example.kekocino.ui.theme.KekoCinoTheme

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
 * Composable raíz que gestiona la navegación completa de la app con
 * Navigation Compose ([NavHost] + [rememberNavController]).
 *
 * El [NavController] mantiene su propio back stack: el botón "atrás" del
 * sistema navega automáticamente a la pantalla anterior, así que ya no
 * hace falta interceptarlo a mano con `BackHandler`.
 *
 * [currentUser] vive fuera del [NavHost] porque varias pantallas necesitan
 * conocer a la usuaria que inició sesión (saludo en la minuta, preferencias
 * de accesibilidad en el temporizador).
 */
@Composable
fun KekoCinoApp() {
    val navController = rememberNavController()
    var currentUser by remember { mutableStateOf<User?>(null) }

    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = { user ->
                    currentUser = user
                    // Se limpia el login del back stack: "atrás" desde la minuta
                    // no debe volver a mostrar la pantalla de inicio de sesión.
                    navController.navigate(Routes.MENU) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onGoToRegister = { navController.navigate(Routes.REGISTER) },
                onGoToRecover = { navController.navigate(Routes.RECOVER) }
            )
        }
        composable(Routes.REGISTER) {
            RegisterScreen(
                onRegisterSuccess = { navController.popBackStack() },
                onGoToLogin = { navController.popBackStack() }
            )
        }
        composable(Routes.RECOVER) {
            RecoverPasswordScreen(
                onGoToLogin = { navController.popBackStack() }
            )
        }
        composable(Routes.MENU) {
            MinutaScreen(
                user = currentUser,
                onRecipeClick = { recipe ->
                    navController.navigate(Routes.detail(recipe.id))
                }
            )
        }
        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument(Routes.RECIPE_ID_ARG) { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt(Routes.RECIPE_ID_ARG) ?: 0
            val recipe = weeklyRecipes.find { it.id == recipeId }
            if (recipe != null) {
                RecipeDetailScreen(
                    recipe = recipe,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
