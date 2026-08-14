package com.example.kekocino.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.example.kekocino.data.Recipe
import com.example.kekocino.data.User
import com.example.kekocino.data.weeklyRecipes
import com.example.kekocino.ui.theme.KekoCinoTheme

/**
 * Pantalla principal: grilla con las 5 recetas de la semana.
 *
 * Componentes UI que cubre (requisito de la entrega):
 *  - Grilla: [LazyVerticalGrid] con [GridCells.Adaptive] — en un teléfono
 *    muestra 2 columnas; en una tablet o rotado, 3 o más. Esto es lo que
 *    hace la app "adaptativa a múltiples dispositivos" sin código extra.
 *  - Tarjetas: [Card] con imagen cargada desde internet con [AsyncImage].
 *  - Texto: día, nombre y calorías de cada receta.
 *
 * @param user la usuaria que inició sesión (para el saludo en el encabezado).
 * @param onRecipeClick se ejecuta cuando la usuaria toca una tarjeta de receta.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinutaScreen(
    user: User?,
    onRecipeClick: (Recipe) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Mi Minuta Semanal",
                            style = MaterialTheme.typography.titleLarge
                        )
                        if (user != null) {
                            Text(
                                text = "Hola, ${user.name.split(" ").first()}!",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        // LazyVerticalGrid: la grilla adaptativa.
        // GridCells.Adaptive(170.dp) calcula automáticamente cuántas columnas
        // caben según el ancho de la pantalla — eso es lo que la hace adaptativa.
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 170.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(weeklyRecipes) { recipe ->
                RecipeCard(recipe = recipe, onClick = { onRecipeClick(recipe) })
            }
        }
    }
}

/**
 * Tarjeta de receta para la grilla.
 *
 * Muestra imagen (descargada desde internet), día, nombre y calorías.
 * Toda la tarjeta es tocable para abrir el detalle.
 *
 * @param recipe datos de la receta a mostrar.
 * @param onClick acción al tocar la tarjeta.
 */
@Composable
fun RecipeCard(recipe: Recipe, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Imagen descargada desde internet con Coil.
        // El fondo de color se ve mientras carga o si no hay conexión.
        AsyncImage(
            model = recipe.imageUrl,
            contentDescription = recipe.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Día de la semana (destacado).
            Text(
                text = recipe.day,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            // Nombre de la receta.
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.titleMedium
            )
            // Calorías.
            Text(
                text = "${recipe.calories} kcal",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// ---------------------------------------------------------------------------
// PREVIEWS
// ---------------------------------------------------------------------------

/** En teléfono se ven 2 columnas. */
@Preview(showBackground = true, name = "Minuta - Teléfono")
@Composable
fun MinutaScreenPhonePreview() {
    KekoCinoTheme {
        MinutaScreen(
            user = User(name = "María González", email = "demo@kekocino.cl", password = ""),
            onRecipeClick = {}
        )
    }
}

/** En tablet se ven 3 o más columnas automáticamente gracias a GridCells.Adaptive. */
@Preview(showBackground = true, name = "Minuta - Tablet", widthDp = 800, heightDp = 1280)
@Composable
fun MinutaScreenTabletPreview() {
    KekoCinoTheme {
        MinutaScreen(
            user = User(name = "María González", email = "demo@kekocino.cl", password = ""),
            onRecipeClick = {}
        )
    }
}
