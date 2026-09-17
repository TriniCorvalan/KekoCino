package com.example.kekocino.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Respuesta de [https://dummyjson.com/recipes](https://dummyjson.com/docs/recipes).
 */
data class RespuestaRecetas(
    val recipes: List<RecetaApi>
)

/**
 * Receta tal como la entrega DummyJSON (campos usados por el ContentProvider).
 */
data class RecetaApi(
    val id: Int,
    val name: String?,
    val cuisine: String?,
    val difficulty: String?,
    val caloriesPerServing: Int?,
    val prepTimeMinutes: Int?,
    val image: String?,
    val ingredients: List<String>?
)

interface DummyJsonRecipesApi {
    @GET("recipes")
    suspend fun obtenerRecetas(
        @Query("limit") limit: Int = 20
    ): RespuestaRecetas
}

object RetrofitRecetas {
    val api: DummyJsonRecipesApi =
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DummyJsonRecipesApi::class.java)
}
