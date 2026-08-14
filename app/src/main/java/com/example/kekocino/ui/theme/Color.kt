package com.example.kekocino.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * PALETA DE COLORES DE KEKOCINO
 *
 * La app esta pensada para usuarias con baja habilidad informatica, por lo tanto
 * los colores no son solo decoracion: son una ayuda para entender la pantalla.
 *
 * Criterios que se usaron para elegirlos:
 *  1. CONTRASTE ALTO: el texto oscuro sobre fondo claro se lee bien incluso
 *     con poca luz o con problemas de vision.
 *  2. SIGNIFICADO: verde = accion principal (avanzar, entrar, guardar),
 *     naranjo = informacion nutricional destacada, rojo = error.
 *  3. CALIDEZ: tonos de cocina (verde hierba, naranjo zanahoria, crema) en
 *     lugar del morado por defecto de la plantilla de Android Studio.
 *
 * Nota sobre el formato: 0xFF2E6E4E se lee como
 *   FF = opacidad total, 2E = rojo, 6E = verde, 4E = azul (igual que #2E6E4E en CSS).
 */

// ----------------------------------------------------------------------------
// TEMA CLARO (el que se usa por defecto)
// ----------------------------------------------------------------------------

/** Verde cocina. Color de los botones principales: "Entrar", "Crear cuenta". */
val VerdeCocina = Color(0xFF2E6E4E)

/** Texto e iconos que van ENCIMA del verde. Blanco para maximo contraste. */
val VerdeCocinaTexto = Color(0xFFFFFFFF)

/** Verde muy suave, para fondos de tarjetas resaltadas sin gritar. */
val VerdeSuave = Color(0xFFD3EBDC)

/** Naranjo zanahoria. Se usa para destacar los datos nutricionales. */
val NaranjoZanahoria = Color(0xFFB4530A)

/** Texto que va encima del naranjo. */
val NaranjoTexto = Color(0xFFFFFFFF)

/** Naranjo muy suave, fondo de la tarjeta de recomendacion nutricional. */
val NaranjoSuave = Color(0xFFFFE3CC)

/** Mostaza, tercer color de apoyo (encabezado de la tabla nutricional). */
val Mostaza = Color(0xFF7A5900)

/** Crema. Fondo general de la app: mas calido y menos agresivo que el blanco puro. */
val Crema = Color(0xFFFBF7F0)

/** Blanco. Fondo de tarjetas y campos de texto, para que "floten" sobre la crema. */
val BlancoTarjeta = Color(0xFFFFFFFF)

/** Gris muy oscuro para el texto. No se usa negro puro: cansa menos la vista. */
val TextoOscuro = Color(0xFF1C1B17)

/** Gris medio para textos secundarios (ayudas, subtitulos). */
val TextoGris = Color(0xFF4F4C45)

/** Rojo de error, para los mensajes tipo "Falta escribir tu correo". */
val RojoError = Color(0xFFB3261E)

// ----------------------------------------------------------------------------
// TEMA OSCURO (cuando el telefono esta en modo noche)
// ----------------------------------------------------------------------------
// Se invierte la logica: los colores de accion se aclaran para que sigan
// destacando sobre un fondo oscuro.

val VerdeCocinaOscuro = Color(0xFF9BD5AF)
val VerdeCocinaTextoOscuro = Color(0xFF00391F)
val VerdeContenedorOscuro = Color(0xFF14512F)
val NaranjoZanahoriaOscuro = Color(0xFFFFB782)
val NaranjoTextoOscuro = Color(0xFF522300)
val NaranjoContenedorOscuro = Color(0xFF743500)
val MostazaOscuro = Color(0xFFE9C26A)
val FondoOscuro = Color(0xFF1B1B17)
val SuperficieOscura = Color(0xFF2A2925)
val TextoClaro = Color(0xFFE7E2D9)
val RojoErrorOscuro = Color(0xFFF2B8B5)
