# KekoCino 🍲

Aplicación móvil Android que entrega una **minuta nutricional semanal de 5 recetas**, diseñada para que la dueña de casa pueda seleccionar y cocinar por día.

Desarrollada con **Kotlin**, **Jetpack Compose** y **Material Design 3** como actividad formativa de la asignatura de Desarrollo de Aplicaciones Móviles.

---

## Cómo ejecutar

**Requisitos:**
- Android Studio (versión reciente)
- SDK Android 24 o superior (la app soporta desde Android 7.0)
- Conexión a internet activa (para cargar las fotos de las recetas)

**Pasos:**
1. Clonar o descargar este repositorio.
2. Abrir la carpeta del proyecto en Android Studio.
3. Esperar que Gradle sincronice las dependencias.
4. Seleccionar un emulador o dispositivo físico.
5. Presionar ▶ **Run**.

**Credenciales de prueba (usuario demo):**
| Campo | Valor |
|---|---|
| Correo | `demo@kekocino.cl` |
| Contraseña | `123456` |

---

## Pantallas

| Pantalla | Descripción |
|---|---|
| **Login** | Inicio de sesión con validación contra arreglo en memoria |
| **Registro** | Formulario completo con todos los componentes UI requeridos |
| **Recuperar contraseña** | Simulación de envío de instrucciones por correo |
| **Minuta semanal** | Grilla adaptativa con las 5 recetas de la semana |
| **Detalle de receta** | Ingredientes, tabla nutricional y recomendación |

---

## Cobertura de requisitos

| Requisito del enunciado | Componente usado | Dónde se implementa |
|---|---|---|
| **Input** | `OutlinedTextField` (vía `TextField`) | `LoginScreen`, `RegisterScreen`, `RecoverPasswordScreen` |
| **Botones** | `Button` (vía `ButtonPrimary`) | Todas las pantallas |
| **Tablas** | `Row` con fondo alterno + `HorizontalDivider` | `RecipeDetailScreen` |
| **Grillas** | `LazyVerticalGrid` con `GridCells.Adaptive` | `MinutaScreen` |
| **Vínculos** | `TextButton` | `LoginScreen`, `RegisterScreen`, `RecoverPasswordScreen` |
| **Textos** | `Text` con `MaterialTheme.typography` | Todas las pantallas |
| **Combo box** | `ExposedDropdownMenuBox` | `RegisterScreen` (personas en casa) |
| **Check list** | `Checkbox` | `RegisterScreen` (términos), `RecipeDetailScreen` (ingredientes) |
| **Radio buttons** | `RadioButton` | `RegisterScreen` (preferencia alimentaria) |
| **Array de 5 recetas + recomendaciones** | `weeklyRecipes: List<Recipe>` | `data/SampleData.kt` |
| **Material Design + Compose** | Material 3 (`Scaffold`, `Card`, `TopAppBar`) | Toda la app |
| **Adaptativa a múltiples dispositivos** | `GridCells.Adaptive`, `widthIn(max=)`, `sp` para textos | `MinutaScreen`, todas las pantallas |
| **Conexión a internet** | `AsyncImage` (Coil) + permiso `INTERNET` | `MinutaScreen`, `RecipeDetailScreen` |
| **Sin base de datos** | `listOf` / `mutableListOf` en memoria | `data/SampleData.kt`, `data/User.kt` |
| **Repositorio Git** | 9 commits organizados por etapa | Historial de este repo |
| **Comentarios de documentación** | KDoc (`/** ... */`) en todos los archivos | Todos los archivos `.kt` |

---

## Estructura de archivos

```
app/src/main/java/com/example/kekocino/
├── MainActivity.kt               ← Router: enum Screen + KekoCinoApp()
├── data/
│   ├── Recipe.kt                 ← data class con 11 campos
│   ├── User.kt                   ← data class + lista registeredUsers
│   └── SampleData.kt             ← arreglo weeklyRecipes (5 recetas)
└── ui/
    ├── theme/
    │   ├── Color.kt              ← paleta de cocina (verde/naranjo/crema)
    │   ├── Theme.kt              ← KekoCinoTheme con esquemas claro y oscuro
    │   └── Type.kt               ← tipografía ampliada (18sp base)
    ├── components/
    │   └── CommonComponents.kt   ← TextField, ButtonPrimary, TitlePrimary
    └── screens/
        ├── LoginScreen.kt
        ├── RegisterScreen.kt
        ├── RecoverPasswordScreen.kt
        ├── MinutaScreen.kt
        └── RecipeDetailScreen.kt
```

---

## Decisiones técnicas relevantes

- **Sin Navigation Compose**: la navegación usa `enum class Screen` + `remember { mutableStateOf(...) }`. Es más simple y suficiente para el alcance del proyecto.
- **Coil 3.3.0** (no la última): la versión 3.4+ requiere Kotlin stdlib 2.3, incompatible con el compilador Kotlin 2.2 del proyecto.
- **`dynamicColor = false`**: se desactivó el color dinámico de Material You para garantizar un diseño consistente en todos los dispositivos y respetar el contraste de accesibilidad.
- **Imágenes desde Nestlé Chile**: las fotos de las recetas se cargan desde `recetasnestle.cl`. Requieren conexión activa.

---

## Tecnologías

| Tecnología | Versión |
|---|---|
| Kotlin | 2.2.10 |
| Jetpack Compose BOM | 2026.02.01 |
| Material Design 3 | incluido en BOM |
| Coil (carga de imágenes) | 3.3.0 |
| Android mínimo | API 24 (Android 7.0) |
| Android objetivo | API 37 |
