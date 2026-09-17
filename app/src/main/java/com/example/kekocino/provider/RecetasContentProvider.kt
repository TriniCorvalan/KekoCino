package com.example.kekocino.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import com.example.kekocino.network.RecetaApi

/**
 * ContentProvider:
 * guarda en memoria las recetas obtenidas por Retrofit y las expone
 * como Cursor vía [query].
 */
class RecetasContentProvider : ContentProvider() {

    companion object {

        const val AUTHORITY = "com.example.kekocino.recetas"

        val CONTENT_URI: Uri =
            Uri.parse("content://$AUTHORITY/recetas")

        private val recetas = mutableListOf<RecetaApi>()

        fun cargarRecetas(nuevasRecetas: List<RecetaApi>) {
            recetas.clear()
            recetas.addAll(nuevasRecetas)
        }
    }

    override fun onCreate(): Boolean = true

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor {
        val cursor = MatrixCursor(
            arrayOf(
                "id",
                "nombre",
                "cocina",
                "dificultad",
                "calorias",
                "prepMinutos",
                "imagen",
                "ingredientes"
            )
        )

        recetas.forEach { receta ->
            cursor.addRow(
                arrayOf(
                    receta.id,
                    receta.name ?: "Sin nombre",
                    receta.cuisine ?: "No disponible",
                    receta.difficulty ?: "No disponible",
                    receta.caloriesPerServing ?: 0,
                    receta.prepTimeMinutes ?: 0,
                    receta.image ?: "",
                    receta.ingredients?.joinToString(", ") ?: "No disponible"
                )
            )
        }

        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0

    override fun getType(uri: Uri): String =
        "vnd.android.cursor.dir/vnd.kekocino.receta"
}
