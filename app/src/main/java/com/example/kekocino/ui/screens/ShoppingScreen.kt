package com.example.kekocino.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kekocino.data.IngredientItem
import com.example.kekocino.data.buildShoppingItems
import com.example.kekocino.data.cleaned
import com.example.kekocino.data.isLight
import com.example.kekocino.data.recipeNamesByDay
import com.example.kekocino.data.uniqueIngredients
import com.example.kekocino.data.weeklyRecipes
import com.example.kekocino.ui.theme.KekoCinoTheme

/**
 * Lista de compras de la semana.
 *
 * El filtro usa [buildShoppingItems]. El menú sale del mapa día → receta
 * y los ingredientes sin repetir, del set.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingScreen(onBack: () -> Unit) {
    var onlyLight by remember { mutableStateOf(false) }
    val items = buildShoppingItems(onlyLight)
    val menu = if (onlyLight) {
        recipeNamesByDay.filter { (_, name) ->
            weeklyRecipes.any { it.name == name && it.isLight }
        }
    } else {
        recipeNamesByDay
    }
    val ingredients = if (onlyLight) {
        items.filterIsInstance<IngredientItem>().map { it.name.cleaned() }.toSet()
    } else {
        uniqueIngredients
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                title = { Text("Lista de compras", style = MaterialTheme.typography.titleLarge) },
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = onlyLight,
                    onCheckedChange = { onlyLight = it }
                )
                Text("Solo recetas livianas (menos de 350 kcal)")
            }

            SectionTitle("Esta semana")
            menu.forEach { (day, name) ->
                Text("$day: $name", style = MaterialTheme.typography.bodyMedium)
            }

            SectionTitle("Para comprar")
            items.forEach { item ->
                Text(item.label(), style = MaterialTheme.typography.bodyMedium)
            }

            SectionTitle("Sin repetir")
            ingredients.forEach { ingredient ->
                Text(ingredient, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ShoppingScreenPreview() {
    KekoCinoTheme {
        ShoppingScreen(onBack = {})
    }
}
