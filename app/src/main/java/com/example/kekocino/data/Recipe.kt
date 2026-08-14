package com.example.kekocino.data

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
 * @param imageUrl URL de la fotografía. Se descarga con Coil desde internet.
 * @param description texto corto que describe el plato.
 * @param ingredients lista de ingredientes principales.
 * @param calories total de calorías por porción.
 * @param proteins gramos de proteínas por porción.
 * @param carbohydrates gramos de carbohidratos por porción.
 * @param fats gramos de grasas por porción.
 * @param nutritionalTip recomendación nutricional personalizada para la receta.
 */
data class Recipe(
    val id: Int,
    val day: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val ingredients: List<String>,
    val calories: Int,
    val proteins: Int,
    val carbohydrates: Int,
    val fats: Int,
    val nutritionalTip: String
)
