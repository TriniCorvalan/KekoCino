package com.example.kekocino.data

import androidx.annotation.DrawableRes

/**
 * Representa una receta de la minuta semanal.
 *
 * Es un "data class": una clase cuyo único rol es guardar datos.
 * Kotlin genera automáticamente equals(), hashCode() y toString()
 * para que no tengamos que escribirlos a mano.
 *
 * @param id identificador único de la receta (1 a 5).
 * @param day día de la semana en que se prepara (ej.: "Lunes").
 * @param name nombre del plato (ej.: "Cazuela de Pollo").
 * @param image ID de la fotografía en res.
 * @param description texto corto que describe el plato.
 * @param ingredients lista de ingredientes principales.
 * @param calories total de calorías por porción.
 * @param proteins gramos de proteínas por porción.
 * @param carbohydrates gramos de carbohidratos por porción.
 * @param fats gramos de grasas por porción.
 * @param nutritionalTip recomendación nutricional personalizada para la receta.
 * @param steps pasos de preparación, cada uno con su señal visual de término
 *   (accesibilidad auditiva: reemplaza avisos sonoros como "hasta que suene").
 * @param audioTranscript transcripción escrita de cualquier contenido narrado
 *   de la receta, para no depender del audio.
 */
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

/**
 * Un paso de la preparación de una receta.
 *
 * @param order número de orden del paso (1, 2, 3...).
 * @param instruction qué hacer en este paso.
 * @param minutes minutos que toma este paso si requiere temporizador; 0 si no aplica.
 * @param visualCue cómo se ve el plato cuando el paso está listo — el equivalente
 *   visual de una señal que normalmente sería sonora (ej.: "empieza a chisporrotear").
 */
data class CookingStep(
    val order: Int,
    val instruction: String,
    val minutes: Int,
    val visualCue: String
)
