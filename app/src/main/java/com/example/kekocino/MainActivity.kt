package com.example.kekocino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
import com.example.kekocino.ui.screens.ShoppingScreen
import com.example.kekocino.ui.screens.SplashScreen
import com.example.kekocino.ui.screens.VisualTimerScreen
import com.example.kekocino.ui.theme.KekoCinoTheme
import com.example.kekocino.ui.theme.kekoCinoBackgroundBrush

/**
 * Punto de entrada de la aplicación.
 * Arrancar Compose y aplica el tema.
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
 * [currentUser] vive fuera del [NavHost] para su reutilización.
 *
 * El [Box] exterior pinta el degradado de fondo de la app.
 */
@Composable
fun KekoCinoApp() {
    val navController = rememberNavController()
    var currentUser by remember { mutableStateOf<User?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(kekoCinoBackgroundBrush())
    ) {
        NavHost(navController = navController, startDestination = Routes.SPLASH) {
            composable(Routes.SPLASH) {
                SplashScreen(
                    onFinished = {
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(Routes.SPLASH) { inclusive = true }
                        }
                    }
                )
            }
            composable(Routes.LOGIN) {
                LoginScreen(
                    onLoginSuccess = { user ->
                        currentUser = user
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
                    },
                    onOpenShopping = { navController.navigate(Routes.SHOPPING) }
                )
            }
            composable(Routes.SHOPPING) {
                ShoppingScreen(onBack = { navController.popBackStack() })
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
                        onBack = { navController.popBackStack() },
                        onStartTimer = { minutes -> navController.navigate(Routes.timer(minutes)) }
                    )
                }
            }
            composable(
                route = Routes.TIMER,
                arguments = listOf(navArgument(Routes.MINUTES_ARG) { type = NavType.IntType })
            ) { backStackEntry ->
                val minutes = backStackEntry.arguments?.getInt(Routes.MINUTES_ARG) ?: 1
                VisualTimerScreen(
                    initialMinutes = minutes,
                    user = currentUser,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
