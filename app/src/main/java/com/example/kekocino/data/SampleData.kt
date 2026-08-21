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
        nutritionalTip = "El pollo es una excelente fuente de proteína magra. Retira la piel antes de cocinar para reducir las grasas saturadas sin perder sabor.",
        steps = listOf(
            CookingStep(1, "Dora las presas de pollo en una olla con aceite caliente.", 5, "La piel del pollo pasa de rosada a dorada pareja por todos los lados."),
            CookingStep(2, "Agrega agua hasta cubrir y deja hervir con las papas y la zanahoria.", 20, "El agua burbujea con fuerza en toda la superficie, no solo en el borde."),
            CookingStep(3, "Incorpora el zapallo y el choclo, cocina hasta que las verduras estén blandas.", 15, "Al pinchar la papa con un tenedor, entra sin resistencia."),
            CookingStep(4, "Sirve caliente con perejil fresco picado por encima.", 0, "El vapor se ve subir apenas se sirve en el plato.")
        ),
        audioTranscript = "Video de la receta: 'Dora el pollo por ambos lados, agrega agua y papas, cocina veinte minutos, suma el zapallo y el choclo quince minutos más, y sirve con perejil fresco.'"
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
        nutritionalTip = "El zapallo es rico en betacaroteno, que el cuerpo convierte en vitamina A. Ayuda a la visión y al sistema inmune. Déjalo con cáscara al cocinarlo para conservar mas nutrientes.",
        steps = listOf(
            CookingStep(1, "Sofríe la cebolla y el ajo hasta que se vean transparentes.", 4, "La cebolla pasa de blanca opaca a translúcida y brillante."),
            CookingStep(2, "Agrega la carne molida y cocina revolviendo.", 6, "La carne cambia de rojo a un color café parejo, sin partes rosadas."),
            CookingStep(3, "Incorpora las papas y el zapallo con un poco de agua, cocina hasta que se deshagan.", 20, "El zapallo se ve desarmarse solo al remover con la cuchara."),
            CookingStep(4, "Fríe un huevo por persona para servir encima del guiso.", 3, "La clara pasa de transparente a blanca sólida, la yema queda brillante.")
        ),
        audioTranscript = "Video de la receta: 'Sofríe cebolla y ajo, agrega la carne molida hasta que cambie de color, suma papas y zapallo con agua veinte minutos, y sirve con un huevo frito encima.'"
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
        nutritionalTip = "El pescado blanco como la merluza es bajo en grasas y alto en proteínas. Comerlo dos veces por semana reduce el riesgo cardiovascular. El limón aporta vitamina C que ayuda a absorber mejor el hierro del pescado.",
        steps = listOf(
            CookingStep(1, "Acomoda el pescado sobre las verduras en una fuente para horno.", 0, "El filete se ve firme y de un blanco parejo, sin partes translúcidas."),
            CookingStep(2, "Rocía con aceite de oliva, limón y hierbas, y lleva al horno precalentado.", 25, "Los bordes de los pimentones y tomates empiezan a dorarse."),
            CookingStep(3, "Retira cuando el pescado se separe en láminas fácilmente.", 0, "Al presionar el filete con un tenedor, se abre en capas sin resistencia.")
        ),
        audioTranscript = "Video de la receta: 'Acomoda el pescado sobre las verduras, rocía con aceite y limón, hornea veinticinco minutos y retira cuando el filete se abra en láminas fácilmente.'"
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
        nutritionalTip = "Los porotos son una fuente vegetal de proteínas y hierro. Combinarlos con el choclo forma una proteína completa, similar a la de la carne. Además son ricos en fibra, lo que ayuda a la digestión.",
        steps = listOf(
            CookingStep(1, "Sofríe la cebolla y el ajo con un poco de aceite.", 4, "La cebolla se ve translúcida y ligeramente dorada en los bordes."),
            CookingStep(2, "Agrega los porotos granados, el zapallo y el choclo con agua.", 25, "El caldo se ve espeso y el zapallo empieza a deshacerse en el borde."),
            CookingStep(3, "Incorpora la albahaca fresca al final y deja reposar unos minutos.", 3, "Las hojas de albahaca se ven marchitas y oscurecidas al mezclarse con el calor.")
        ),
        audioTranscript = "Video de la receta: 'Sofríe cebolla y ajo, agrega porotos, zapallo y choclo con agua veinticinco minutos, incorpora albahaca fresca al final y deja reposar.'"
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
        nutritionalTip = "Terminar la semana con una ensalada fresca ayuda a equilibrar la ingesta de fibra y vitaminas. La lechuga romana es rica en acido fólico y vitamina K. Prepara el aderezo en casa para controlar la cantidad de sal y grasa.",
        steps = listOf(
            CookingStep(1, "Cocina la pechuga de pollo a la plancha por ambos lados.", 8, "El pollo pasa de rosado a blanco parejo por dentro al cortar una punta."),
            CookingStep(2, "Tuesta el pan cortado en cubos hasta que quede crujiente.", 5, "Los cubos de pan se ven dorados parejos por todos los lados."),
            CookingStep(3, "Mezcla la lechuga con el aderezo, agrega los crutones, el pollo y el parmesano.", 0, "El aderezo cubre las hojas dándoles un brillo uniforme.")
        ),
        audioTranscript = "Video de la receta: 'Cocina el pollo a la plancha, tuesta el pan en cubos, y mezcla la lechuga con el aderezo, los crutones, el pollo y el parmesano.'"
    )
)
