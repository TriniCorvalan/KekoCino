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
 * Un "color scheme" es la paleta completa que Material Design reparte
 * automaticamente a todos los componentes. Al definirlo una sola vez aqui,
 * TODOS los botones, tarjetas y campos de texto de la app quedan coordinados
 * sin tener que pintarlos uno por uno.
 *
 * Los roles mas importantes que se definen abajo:
 *   primary      -> color de la accion principal (botones "Entrar", "Guardar")
 *   onPrimary    -> color del texto que va ENCIMA de primary
 *   secondary    -> color de apoyo (datos nutricionales)
 *   background   -> fondo de la pantalla completa
 *   surface      -> fondo de las tarjetas y campos, "encima" del background
 *   error        -> mensajes de error
 * La regla del prefijo "on": onX es siempre el color del contenido que va
 * sobre X. Respetarla es lo que garantiza que el texto siempre se lea.
 */

/** Paleta para cuando el telefono esta en modo claro (el caso normal). */
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
 * Se usa una sola vez, en MainActivity, rodeando toda la app:
 *
 *     KekoCinoTheme {
 *         // ...toda la interfaz...
 *     }
 *
 * @param darkTheme si true usa la paleta oscura. Por defecto pregunta al
 *   sistema operativo con isSystemInDarkTheme(), asi la app respeta la
 *   preferencia que la usuaria ya tiene configurada en su telefono.
 * @param content la interfaz que va a recibir estos colores y tipografias.
 *
 * NOTA IMPORTANTE (cambio respecto de la plantilla original):
 * Android Studio genera este archivo con "dynamic color" activado, una funcion
 * de Android 12+ que recolorea la app segun el fondo de pantalla del usuario.
 * Aqui se ELIMINO a proposito, por dos razones:
 *   1. La app se veria de un color distinto en cada telefono, lo que impide
 *      garantizar el contraste que necesita nuestro publico objetivo.
 *   2. El diseno documentado en el informe debe coincidir con lo que se ve.
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
 *
 * El paso intermedio por `surface` suaviza la transición para que no se vea
 * como una línea de corte entre dos colores. Se define a partir de roles del
 * esquema de color en vez de colores fijos para que se adapte solo entre modo
 * claro y oscuro. Se aplica una sola vez, detrás del NavHost en
 * `MainActivity.kt`, para que todas las pantallas compartan el mismo fondo.
 */
@Composable
fun kekoCinoBackgroundBrush(): Brush = Brush.verticalGradient(
    colors = listOf(
        MaterialTheme.colorScheme.background,
        MaterialTheme.colorScheme.surface,
        MaterialTheme.colorScheme.primaryContainer
    )
)
