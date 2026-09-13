package com.example.kekocino.data

// Herencia: item genérico de la lista, las hijas cambian el texto.
open class ShopItem(val name: String) {
    open fun label(): String = name
}

class IngredientItem(name: String, val recipeName: String) : ShopItem(name) {
    override fun label(): String = "$name ($recipeName)"
}

class NoteItem(name: String) : ShopItem(name) {
    override fun label(): String = "Nota: $name"
}

// Función de extension.
fun String.cleaned(): String = trim()

// Propiedad de extension: receta liviana si tiene menos de 350 kcal.
val Recipe.isLight: Boolean
    get() = calories < 350

// Función de orden superior: recibe otra función para ordenar.
fun List<Recipe>.orderBy(selector: (Recipe) -> Int): List<Recipe> = sortedBy(selector)

// Map día -> nombre de receta.
val recipeNamesByDay: Map<String, String> =
    weeklyRecipes.associate { it.day to it.name }

// Set: ingredientes sin repetir.
val uniqueIngredients: Set<String> = weeklyRecipes
    .flatMap { it.ingredients }
    .map { it.cleaned() }
    .toSet()

// Lista de compras. Si onlyLight es true, filtra las recetas livianas.
fun buildShoppingItems(onlyLight: Boolean): List<ShopItem> {
    val recipes = if (onlyLight) {
        weeklyRecipes.filter { it.isLight }
    } else {
        weeklyRecipes.orderBy { it.calories }
    }
    val items: List<ShopItem> = recipes.flatMap { recipe ->
        recipe.ingredients.map { IngredientItem(it, recipe.name) }
    }
    return items + NoteItem("Revisar la despensa antes de comprar")
}
