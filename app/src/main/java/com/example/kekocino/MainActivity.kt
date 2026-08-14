package com.example.kekocino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.kekocino.ui.theme.KekoCinoTheme

/**
 * Punto de entrada de la aplicación.
 *
 * En Android toda app arranca por una Activity. Esta es la única que tiene
 * el proyecto: recibe el arranque del sistema y le pasa el control a Compose.
 *
 * setContent { } le dice a Compose que tome el control de la pantalla.
 * Todo lo que se ponga dentro de las llaves es la interfaz de usuario de la app completa.
 *
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KekoCinoTheme {
                // Aqui se conectará la navegación en la siguiente etapa.
            }
        }
    }
}
