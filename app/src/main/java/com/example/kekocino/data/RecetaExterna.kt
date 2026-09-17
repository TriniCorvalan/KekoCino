package com.example.kekocino.data

/**
 * Modelo de UI para recetas del catálogo externo (DummyJSON),
 * leídas vía ContentResolver desde [com.example.kekocino.provider.RecetasContentProvider].
 */
data class RecetaExterna(
    val id: Int,
    val nombre: String,
    val cocina: String,
    val dificultad: String,
    val calorias: Int,
    val prepMinutos: Int,
    val imagen: String,
    val ingredientes: String
)
