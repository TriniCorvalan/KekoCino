package com.example.kekocino.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush

/**
 * TEMA VISUAL DE LA APLICACION (Material Design 3)
 *
 *   primary      -> color de la accion principal (botones "Entrar", "Guardar")
 *   onPrimary    -> color del texto que va ENCIMA de primary
 *   secondary    -> color de apoyo (datos nutricionales)
 *   background   -> fondo de la pantalla completa
 *   surface      -> fondo de las tarjetas y campos, "encima" del background
 *   error        -> mensajes de error
 */

/** Paleta para cuando el telefono esta en modo claro. */
private val EsquemaClaro = lightColorScheme(
    primary = Ocre,
    onPrimary = CarbonOscuro,
    primaryContainer = OcreContainerClaro,
    onPrimaryContainer = CarbonOscuro,

    secondary = CarbonOscuro,
    onSecondary = CremaSuave,
    secondaryContainer = GrisClaro,
    onSecondaryContainer = CarbonOscuro,

    tertiary = OcreAcento,
    onTertiary = CremaSuave,

    background = CremaSuave,
    onBackground = CarbonOscuro,

    surface = GrisClaro,
    onSurface = CarbonOscuro,
    surfaceVariant = SuperficieVarianteClara,
    onSurfaceVariant = CarbonSecundario,

    error = RojoError,
    onError = CremaSuave
)

/** Paleta para cuando el telefono esta en modo oscuro / nocturno. */
private val EsquemaOscuro = darkColorScheme(
    primary = OcreClaro,
    onPrimary = CarbonOscuro,
    primaryContainer = OcreContainerOscuro,
    onPrimaryContainer = OcreClaro,

    secondary = GrisClaro,
    onSecondary = CarbonOscuro,
    secondaryContainer = GrisContainerOscuro,
    onSecondaryContainer = GrisClaro,

    tertiary = OcreClaro,
    onTertiary = CarbonOscuro,

    background = CarbonOscuro,
    onBackground = CremaSuave,

    surface = CarbonSuperficie,
    onSurface = CremaSuave,
    surfaceVariant = CarbonSuperficieVariante,
    onSurfaceVariant = GrisClaro,

    error = RojoErrorOscuro,
    onError = CarbonOscuro
)

/**
 * Envoltorio que aplica el tema de KekoCino a todo lo que este dentro.
 *
 * @param darkTheme si true usa la paleta oscura. 
 * @param content la interfaz que va a recibir estos colores y tipografias.
 */
@Composable
fun KekoCinoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val esquemaDeColor = if (darkTheme) EsquemaOscuro else EsquemaClaro

    MaterialTheme(
        colorScheme = esquemaDeColor,
        typography = Typography,
        shapes = KekoCinoShapes,
        content = content
    )
}

/**
 * Degradado de fondo de la app: del color `background` (arriba), pasando por
 * `surface`, hasta el naranjo durazno suave de `primaryContainer` (abajo).
 */
@Composable
fun kekoCinoBackgroundBrush(): Brush = Brush.verticalGradient(
    colors = listOf(
        MaterialTheme.colorScheme.background,
        MaterialTheme.colorScheme.surface,
        MaterialTheme.colorScheme.primaryContainer
    )
)
