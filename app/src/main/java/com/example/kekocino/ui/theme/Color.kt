package com.example.kekocino.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * PALETA DE COLORES DE KEKOCINO
 *
 * Paleta moderna basada en 4 colores base:
 *  - Carbón oscuro:    color de texto y fondo en modo oscuro.
 *  - Crema suave:      fondo cálido en modo claro.
 *  - Ocre:             color principal (acciones, botones).
 *  - Gris claro:        superficies frías (tarjetas), contraste con la crema.
 *
 * Material 3 necesita, además de estos 4, una versión "container" (más clara
 * u oscura) de cada rol para chips, encabezados y estados seleccionados.
 * Esas versiones están marcadas como "derivado" abajo: son tintes/sombras de
 * los 4 colores base, no colores nuevos de marca.
 *
 * Se mantiene el criterio de contraste alto de la app: el texto oscuro
 * (Carbón) va siempre sobre superficies claras, y el texto claro (Crema)
 * va siempre sobre superficies oscuras (Carbón).
 */

// ----------------------------------------------------------------------------
// COLORES BASE
// ----------------------------------------------------------------------------

/** Carbón oscuro. Texto principal en modo claro; fondo en modo oscuro. */
val CarbonOscuro = Color(0xFF264653)

/** Crema suave. Fondo principal en modo claro; texto principal en modo oscuro. */
val CremaSuave = Color(0xFFF4F1DE)

/** Ocre. Color principal: botones "Entrar", "Crear cuenta". */
val Ocre = Color(0xFFCC7722)

/** Gris claro. Superficie de tarjetas: frío, contrasta con la crema cálida. */
val GrisClaro = Color(0xFFE2E8F0)

// ----------------------------------------------------------------------------
// TONOS DERIVADOS — TEMA CLARO
// ----------------------------------------------------------------------------

/** Derivado: tinte claro de ocre, para containers y chips destacados. */
val OcreContainerClaro = Color(0xFFE6C69C)

/** Derivado: ocre más oscuro, para acentos (encabezado de tabla, iconos). */
val OcreAcento = Color(0xFFA35F1B)

/** Derivado: mezcla neutra de crema y gris, para filas alternas y variantes de superficie. */
val SuperficieVarianteClara = Color(0xFFECEAE2)

/** Derivado: carbón más suave, para texto secundario sobre superficies claras. */
val CarbonSecundario = Color(0xFF4F6672)

/** Rojo de error. Deliberadamente distinto en matiz de la terracota (menos rojizo). */
val RojoError = Color(0xFFB3261E)

// ----------------------------------------------------------------------------
// TONOS DERIVADOS — TEMA OSCURO
// ----------------------------------------------------------------------------

/** Derivado: ocre aclarado, para que sirva de acento sobre fondo oscuro. */
val OcreClaro = Color(0xFFE0AD7A)

/** Derivado: contenedor oscuro de ocre (mezcla con carbón). */
val OcreContainerOscuro = Color(0xFF685A3F)

/** Derivado: carbón un poco más claro que el fondo, para que las superficies destaquen. */
val CarbonSuperficie = Color(0xFF33565F)

/** Derivado: variante de superficie oscura, un poco más clara que [CarbonSuperficie]. */
val CarbonSuperficieVariante = Color(0xFF3C6270)

/** Derivado: contenedor secundario oscuro (gris-azulado apagado). */
val GrisContainerOscuro = Color(0xFF3F5561)

/** Rojo de error para modo oscuro (versión clara/pastel, estándar Material). */
val RojoErrorOscuro = Color(0xFFF2B8B5)
