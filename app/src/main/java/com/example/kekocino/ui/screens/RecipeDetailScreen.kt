package com.example.kekocino.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.example.kekocino.data.Recipe
import com.example.kekocino.data.weeklyRecipes
import com.example.kekocino.ui.theme.KekoCinoTheme

/**
 * Pantalla de detalle de una receta.
 *
 * Componentes UI que cubre (requisito de la entrega):
 *  - Imagen: foto de la receta descargada con [AsyncImage].
 *  - Check list de ingredientes: [Checkbox] por ingrediente para marcar
 *    lo que ya se tiene al momento de cocinar.
 *  - Tabla nutricional: encabezado + filas con fondo alterno, separadas
 *    por [HorizontalDivider].
 *  - Texto: descripción, recomendación nutricional en una [Card].
 *  - Botón: flecha "atrás" en el [TopAppBar].
 *
 * @param recipe la receta cuyos datos se muestran.
 * @param onBack navega de vuelta a la grilla de la minuta.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(recipe: Recipe, onBack: () -> Unit) {
    // Estado de los checkboxes de ingredientes (uno por cada ingrediente).
    // remember(recipe.id) reinicia los checks cuando se abre una receta nueva.
    val checkedIngredients = remember(recipe.id) {
        mutableStateListOf(*Array(recipe.ingredients.size) { false })
    }

    // Filas de la tabla nutricional.
    val nutritionRows = listOf(
        "Calorías" to "${recipe.calories} kcal",
        "Proteínas" to "${recipe.proteins} g",
        "Carbohidratos" to "${recipe.carbohydrates} g",
        "Grasas" to "${recipe.fats} g"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(recipe.name, style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver a la minuta"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Foto grande de la receta.
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Descripción.
                Text(
                    text = recipe.description,
                    style = MaterialTheme.typography.bodyLarge
                )

                HorizontalDivider()

                // --- Check list de ingredientes ---
                Text(
                    text = "Ingredientes",
                    style = MaterialTheme.typography.titleMedium
                )
                recipe.ingredients.forEachIndexed { index, ingredient ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = checkedIngredients[index],
                            onCheckedChange = { checkedIngredients[index] = it }
                        )
                        Text(
                            text = ingredient,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }

                HorizontalDivider()

                // --- Tabla de información nutricional (por porción) ---
                Text(
                    text = "Información nutricional (por porción)",
                    style = MaterialTheme.typography.titleMedium
                )
                Card(modifier = Modifier.fillMaxWidth()) {
                    // Encabezado de la tabla.
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(horizontal = 12.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = "Nutriente",
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Por porción",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    // Filas con fondo alterno para facilitar la lectura.
                    nutritionRows.forEachIndexed { index, (nutrient, value) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (index % 2 == 0) MaterialTheme.colorScheme.surface
                                    else MaterialTheme.colorScheme.surfaceVariant
                                )
                                .padding(horizontal = 12.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = nutrient,
                                modifier = Modifier.weight(1f),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = value,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        if (index < nutritionRows.size - 1) HorizontalDivider()
                    }
                }

                // --- Recomendación nutricional ---
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Recomendación nutricional",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = recipe.nutritionalTip,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

// ---------------------------------------------------------------------------
// PREVIEWS
// ---------------------------------------------------------------------------

@Preview(showBackground = true, name = "Detalle - Teléfono")
@Composable
fun RecipeDetailScreenPhonePreview() {
    KekoCinoTheme {
        RecipeDetailScreen(recipe = weeklyRecipes.first(), onBack = {})
    }
}

@Preview(showBackground = true, name = "Detalle - Tablet", widthDp = 800, heightDp = 1280)
@Composable
fun RecipeDetailScreenTabletPreview() {
    KekoCinoTheme {
        RecipeDetailScreen(recipe = weeklyRecipes.first(), onBack = {})
    }
}
