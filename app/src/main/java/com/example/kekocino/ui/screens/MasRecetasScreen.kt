package com.example.kekocino.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kekocino.data.RecetaExterna
import com.example.kekocino.network.RetrofitRecetas
import com.example.kekocino.provider.RecetasContentProvider

/**
 * Lee las recetas expuestas por [RecetasContentProvider] mediante ContentResolver.
 */
fun consultarRecetas(context: Context): List<RecetaExterna> {
    val resultado = mutableListOf<RecetaExterna>()

    val cursor = context.contentResolver.query(
        RecetasContentProvider.CONTENT_URI,
        null,
        null,
        null,
        null
    )

    cursor?.use {
        val idIndex = it.getColumnIndex("id")
        val nombreIndex = it.getColumnIndex("nombre")
        val cocinaIndex = it.getColumnIndex("cocina")
        val dificultadIndex = it.getColumnIndex("dificultad")
        val caloriasIndex = it.getColumnIndex("calorias")
        val prepIndex = it.getColumnIndex("prepMinutos")
        val imagenIndex = it.getColumnIndex("imagen")
        val ingredientesIndex = it.getColumnIndex("ingredientes")

        while (it.moveToNext()) {
            resultado.add(
                RecetaExterna(
                    id = it.getInt(idIndex),
                    nombre = it.getString(nombreIndex),
                    cocina = it.getString(cocinaIndex),
                    dificultad = it.getString(dificultadIndex),
                    calorias = it.getInt(caloriasIndex),
                    prepMinutos = it.getInt(prepIndex),
                    imagen = it.getString(imagenIndex),
                    ingredientes = it.getString(ingredientesIndex)
                )
            )
        }
    }

    return resultado
}

/**
 * Catálogo externo de recetas (DummyJSON) vía ContentProvider.
 * Flujo: Retrofit → cargarRecetas → ContentResolver.query.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasRecetasScreen(
    onVolver: () -> Unit
) {
    val context = LocalContext.current

    var recetas by remember { mutableStateOf<List<RecetaExterna>>(emptyList()) }
    var cargando by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            // 1. Consumimos API
            val respuesta = RetrofitRecetas.api.obtenerRecetas()

            // 2. Entregamos datos al Provider
            RecetasContentProvider.cargarRecetas(respuesta.recipes)

            // 3. Consultamos mediante ContentResolver
            recetas = consultarRecetas(context)
        } catch (_: Exception) {
            error = "No fue posible cargar las recetas"
        } finally {
            cargando = false
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
        topBar = {
            TopAppBar(
                title = { Text("Más recetas") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Ideas para cocinar",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                cargando -> CircularProgressIndicator()
                error.isNotEmpty() -> {
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                else -> {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(recetas) { receta ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    if (receta.imagen.isNotEmpty()) {
                                        AsyncImage(
                                            model = receta.imagen,
                                            contentDescription = receta.nombre,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(180.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = receta.nombre,
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    Text(text = "Cocina: ${receta.cocina}")
                                    Text(text = "Dificultad: ${receta.dificultad}")
                                    Text(text = "Calorías: ${receta.calorias} kcal")
                                    Text(text = "Prep: ${receta.prepMinutos} min")
                                    Text(
                                        text = "Ingredientes: ${receta.ingredientes}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onVolver,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver a la minuta")
            }
        }
    }
}
