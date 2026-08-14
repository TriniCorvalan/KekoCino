package com.example.kekocino.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * TIPOGRAFIA DE KEKOCINO
 *
 * Los tamaños son MAS GRANDES que los que trae Material Design por defecto.
 * Esto es intencional: el publico objetivo son usuarias con baja habilidad
 * informatica, donde un texto pequeno es una barrera real de uso.
 *
 * Se usa la unidad "sp" (scalable pixels) y no "dp" para los textos.
 * Diferencia importante:
 *   - dp  -> tamano fijo, no cambia.
 *   - sp  -> respeta el ajuste "tamano de fuente" del sistema Android.
 * Es decir: si la usuaria agranda la letra en la configuracion de su telefono,
 * la app la acompana automaticamente. Esto es parte de ser ADAPTATIVA.
 *
 * Guia rapida de cuando usar cada estilo:
 *   displayLarge / headlineLarge -> titulo principal de una pantalla
 *   titleLarge / titleMedium     -> titulos de tarjeta y secciones
 *   bodyLarge                    -> texto normal de lectura
 *   bodyMedium                   -> textos secundarios y de ayuda
 *   labelLarge                   -> texto dentro de los botones
 */
val Typography = Typography(

    // Titulo grande de bienvenida (pantalla de Login).
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),

    // Titulo de pantalla (Registro, Recuperar contrasena, Minuta).
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 34.sp
    ),

    // Nombre de la receta en la pantalla de detalle.
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),

    // Titulos de seccion y nombre de receta dentro de la tarjeta de la grilla.
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 19.sp,
        lineHeight = 26.sp
    ),

    // Texto normal. 18sp en vez de los 16sp por defecto de Material.
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.4.sp
    ),

    // Textos de apoyo: ayudas debajo de los campos, mensajes de error.
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),

    // Texto que va DENTRO de los botones. Grande y en negrita para que se lea claro.
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    )
)
