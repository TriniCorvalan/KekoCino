package com.example.kekocino.data

/**
 * ARREGLO DE LAS 5 RECETAS SEMANALES
 *
 * Esta lista simula los datos que en una app real vendrían de una base
 * de datos o de una API. Por ahora se guardan directamente en el código.
 *
 * Las imágenes se cargan desde el servidor de Nestle Chile usando Coil.
 * Requieren conexión a internet activa (permiso INTERNET en el Manifest).
 */
val weeklyRecipes: List<Recipe> = listOf(

    Recipe(
        id = 1,
        day = "Lunes",
        name = "Cazuela de Pollo",
        imageUrl = "https://www.recetasnestle.cl/sites/default/files/styles/recipe_detail_desktop_new/public/srh_recipes/c034e2c5ad786fba0b9c3a15ac15ce5c.webp?itok=1G-TsBeK",
        description = "Sopa tradicional chilena con trozos de pollo, papas, zapallo y choclo. Reconfortante y nutritiva, ideal para el almuerzo de la semana.",
        ingredients = listOf(
            "1 presa de pollo por persona",
            "2 papas medianas",
            "1 trozo de zapallo",
            "1 choclo partido en rodajas",
            "1 zanahoria",
            "Arroz o fideos (opcional)",
            "Sal, pimienta y orégano al gusto",
            "Perejil fresco para servir"
        ),
        calories = 380,
        proteins = 32,
        carbohydrates = 35,
        fats = 8,
        nutritionalTip = "El pollo es una excelente fuente de proteína magra. Retira la piel antes de cocinar para reducir las grasas saturadas sin perder sabor."
    ),

    Recipe(
        id = 2,
        day = "Martes",
        name = "Charquicán de Zapallo",
        imageUrl = "https://www.recetasnestle.cl/sites/default/files/styles/recipe_detail_desktop_new/public/srh_recipes/0babbc61e358a388c8a1c22bcd8f984e.webp?itok=aH90geiI",
        description = "Guiso espeso de zapallo, papas y carne molida. Clasico de la cocina chilena, muy rendidor y fácil de preparar para toda la familia.",
        ingredients = listOf(
            "500 g de carne molida",
            "1/2 zapallo en cubos",
            "3 papas medianas en cubos",
            "1 cebolla picada",
            "1 diente de ajo",
            "1 huevo por persona (para el huevo frito encima)",
            "Sal, comino, oregano y merkén al gusto",
            "Aceite para sofreir"
        ),
        calories = 450,
        proteins = 28,
        carbohydrates = 42,
        fats = 15,
        nutritionalTip = "El zapallo es rico en betacaroteno, que el cuerpo convierte en vitamina A. Ayuda a la visión y al sistema inmune. Déjalo con cáscara al cocinarlo para conservar mas nutrientes."
    ),

    Recipe(
        id = 3,
        day = "Miércoles",
        name = "Pescado al Horno con Verduras",
        imageUrl = "https://www.recetasnestle.cl/sites/default/files/styles/recipe_detail_desktop_new/public/srh_recipes/48295c12241d26e4c36a4a5857ff06f4.webp?itok=lyHMVLPj",
        description = "Filete de merluza al horno con pimentones, tomates y hierbas. Liviano, saludable y listo en 30 minutos. Perfecto para el día de en medio de la semana.",
        ingredients = listOf(
            "1 filete de merluza por persona",
            "1 pimentón rojo en tiras",
            "2 tomates en rodajas",
            "1 cebolla en plumas",
            "Jugo de 1 limón",
            "Aceite de oliva",
            "Sal, pimienta y eneldo al gusto",
            "Perejil fresco"
        ),
        calories = 280,
        proteins = 35,
        carbohydrates = 12,
        fats = 9,
        nutritionalTip = "El pescado blanco como la merluza es bajo en grasas y alto en proteínas. Comerlo dos veces por semana reduce el riesgo cardiovascular. El limón aporta vitamina C que ayuda a absorber mejor el hierro del pescado."
    ),

    Recipe(
        id = 4,
        day = "Jueves",
        name = "Porotos Granados",
        imageUrl = "https://www.recetasnestle.cl/sites/default/files/styles/recipe_detail_desktop_new/public/srh_recipes/4716ca31a6f16f8c918b9d08fdf606c5.webp?itok=UnKKdy2x",
        description = "Guiso veraniego de porotos frescos con choclo, zapallo y albahaca. Un plato vegetariano completo, lleno de sabor y fibra.",
        ingredients = listOf(
            "2 tazas de porotos granados (frescos o congelados)",
            "1 trozo de zapallo en cubos",
            "1 choclo desgranado",
            "1 cebolla picada",
            "2 dientes de ajo",
            "Hojas de albahaca fresca",
            "Sal, pimienta y color al gusto",
            "Aceite para sofreir"
        ),
        calories = 320,
        proteins = 16,
        carbohydrates = 55,
        fats = 5,
        nutritionalTip = "Los porotos son una fuente vegetal de proteínas y hierro. Combinarlos con el choclo forma una proteína completa, similar a la de la carne. Además son ricos en fibra, lo que ayuda a la digestión."
    ),

    Recipe(
        id = 5,
        day = "Viernes",
        name = "Ensalada César con Pollo",
        imageUrl = "https://www.recetasnestle.cl/sites/default/files/styles/recipe_detail_desktop_new/public/srh_recipes/3a5e42e262ec4394def6f80a9cc038b4.webp?itok=IEioeLFT",
        description = "Lechuga romana con pollo a la plancha, tostadas de pan y aderezo cesar. Una opción fresca y liviana para terminar la semana con una comida mas ligera.",
        ingredients = listOf(
            "1 pechuga de pollo a la plancha",
            "1/2 lechuga romana",
            "2 rebanadas de pan para crutones",
            "Queso parmesano rallado",
            "Aderezo cesar (mayonesa, limón, mostaza, ajo)",
            "Sal y pimienta al gusto"
        ),
        calories = 310,
        proteins = 38,
        carbohydrates = 18,
        fats = 12,
        nutritionalTip = "Terminar la semana con una ensalada fresca ayuda a equilibrar la ingesta de fibra y vitaminas. La lechuga romana es rica en acido fólico y vitamina K. Prepara el aderezo en casa para controlar la cantidad de sal y grasa."
    )
)
