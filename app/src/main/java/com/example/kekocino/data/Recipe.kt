package com.example.kekocino.data

import androidx.annotation.DrawableRes

data class Recipe(
    val id: Int,
    val day: String,
    val name: String,
    @DrawableRes val image: Int,
    val description: String,
    val ingredients: List<String>,
    val calories: Int,
    val proteins: Int,
    val carbohydrates: Int,
    val fats: Int,
    val nutritionalTip: String,
    val steps: List<CookingStep> = emptyList(),
    val audioTranscript: String = ""
)

data class CookingStep(
    val order: Int,
    val instruction: String,
    val minutes: Int,
    val visualCue: String
)
