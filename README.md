# KekoCino 🍲

Aplicación móvil Android que entrega una **minuta nutricional semanal de 5 recetas**, diseñada para que la dueña de casa pueda seleccionar y cocinar por día — con una capa de **accesibilidad para personas con discapacidad sensorial auditiva**: cada alerta sonora (temporizador, indicaciones narradas) tiene su equivalente visual y háptico.

Desarrollada con **Kotlin**, **Jetpack Compose**, **Navigation Compose** y **Material Design 3** como actividad formativa de la asignatura de Desarrollo de Aplicaciones Móviles.

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

**Credenciales de prueba (5 usuarias precargadas):**

| Correo | Contraseña | Preferencia | Accesibilidad |
|---|---|---|---|
| `demo@kekocino.cl` | `123456` | Sin restricción | Destello + vibración + transcripción |
| `maria@kekocino.cl` | `maria123` | Vegetariana | Destello + transcripción (sin vibración) |
| `pedro@kekocino.cl` | `pedro123` | Sin gluten | Vibración + transcripción (sin destello) |
| `ana@kekocino.cl` | `ana12345` | Sin restricción | Destello + vibración (sin transcripción) |
| `jorge@kekocino.cl` | `jorge123` | Vegetariana | Destello + vibración + transcripción |

---

## Pantallas

| Pantalla | Descripción |
|---|---|
| **Login** | Inicio de sesión con validación contra arreglo en memoria |
| **Registro** | Formulario completo con preferencias alimentarias y de accesibilidad |
| **Recuperar contraseña** | Simulación de envío de instrucciones por correo |
| **Minuta semanal** | Grilla adaptativa con las 5 recetas de la semana |
| **Detalle de receta** | Ingredientes, tabla nutricional, pasos con señal visual y transcripción de audio |
| **Temporizador visual** | Avisa el término de un paso de cocción con destello de pantalla y/o vibración, sin depender del oído |

---

## Accesibilidad auditiva

- Cada paso de una receta indica su **señal visual** (`visualCue`): cómo se ve el plato cuando el paso está listo, en vez de "cuando escuches...".
- El **temporizador visual** reemplaza la alarma sonora por un destello de pantalla completa y/o vibración, configurable con radio buttons.
- Cada receta incluye la **transcripción escrita** de su contenido narrado.
- Al registrarse, cada usuaria elige sus propias preferencias de accesibilidad (destellos, vibración, transcripciones), que se guardan en su perfil.

---
## Cobertura de requisitos

 Requisito del enunciado | Componente usado | Dónde se implementa |
|---|---|---|
| **Input** | `OutlinedTextField` (vía `TextField`) | `LoginScreen`, `RegisterScreen`, `RecoverPasswordScreen` |
| **Botones** | `Button` (vía `ButtonPrimary`) | Todas las pantallas; Iniciar/Pausar/Reiniciar en `VisualTimerScreen` |
| **Tablas** | `Row` con fondo alterno + `HorizontalDivider` | `RecipeDetailScreen` |
| **Grillas** | `LazyVerticalGrid` con `GridCells.Adaptive` | `MinutaScreen` |
| **Vínculos** | `TextButton` | `LoginScreen`, `RegisterScreen`, `RecoverPasswordScreen` |
| **Textos** | `Text` con `MaterialTheme.typography` | Todas las pantallas |
| **Combo box** | `ExposedDropdownMenuBox` | `RegisterScreen` (personas en casa), `VisualTimerScreen` (minutos) |
| **Check list** | `Checkbox` | `RegisterScreen` (términos y accesibilidad), `RecipeDetailScreen` (ingredientes) |
| **Radio buttons** | `RadioButton` | `RegisterScreen` (preferencia alimentaria), `VisualTimerScreen` (tipo de aviso) |
| **Navegación entre vistas** | `NavHost` + `NavController` (Navigation Compose) | `MainActivity.kt`, `ui/navigation/Routes.kt` |
| **Array de 5 usuarios con contraseñas** | `registeredUsers: MutableList<User>` | `data/User.kt` |
| **Array de 5 recetas + recomendaciones** | `weeklyRecipes: List<Recipe>` | `data/SampleData.kt` |
| **Material Design + Compose** | Material 3 (`Scaffold`, `Card`, `TopAppBar`) | Toda la app |
| **Adaptativa a múltiples dispositivos** | `GridCells.Adaptive`, `widthIn(max=)`, `sp` para textos | `MinutaScreen`, todas las pantallas |
| **Imágenes** | `Image` + `painterResource` sobre recursos locales en `res/drawable` | `MinutaScreen`, `RecipeDetailScreen` |
| **Sin base de datos** | `listOf` / `mutableListOf` en memoria | `data/SampleData.kt`, `data/User.kt` |
| **Repositorio Git** | Commits organizados por etapa | Historial de este repo |
| **Comentarios de documentación** | KDoc (`/** ... */`) en todos los archivos | Todos los archivos `.kt` |

---

## Estructura de archivos

```
app/src/main/java/com/example/kekocino/
├── MainActivity.kt ← NavHost + rutas de Navigation Compose
├── data/
│ ├── Recipe.kt ← data class Recipe + data class CookingStep
│ ├── User.kt ← data class User (+ preferencias de accesibilidad) y registeredUsers
│ └── SampleData.kt ← arreglo weeklyRecipes (5 recetas, con pasos y transcripción)
└── ui/
├── navigation/
│ └── Routes.kt ← rutas y argumentos del NavHost
├── theme/
│ ├── Color.kt ← paleta ocre/carbón/crema/gris
│ ├── Theme.kt ← KekoCinoTheme (esquemas claro/oscuro + degradado)
│ ├── Shape.kt ← esquinas redondeadas (estilo moderno)
│ └── Type.kt ← tipografía ampliada (18sp base)
├── components/
│ └── CommonComponents.kt ← TextField, ButtonPrimary, TitlePrimary
└── screens/
├── LoginScreen.kt
├── RegisterScreen.kt
├── RecoverPasswordScreen.kt
├── MinutaScreen.kt
├── RecipeDetailScreen.kt
└── VisualTimerScreen.kt ← temporizador con destello y vibración
app/src/main/res/drawable/ ← fotos de las 5 recetas (recursos locales)
```

---
## Decisiones técnicas relevantes
- **Navigation Compose**: la navegación usa `NavHost` + `NavController` con rutas tipadas en `Routes.kt`. El `NavController` gestiona el botón "atrás" del sistema automáticamente.
- **Navigation Compose 2.9.5**: fijado a propósito — evitar una versión que exija un `kotlin-stdlib` más nuevo que el compilador Kotlin 2.2 del proyecto.
- **`dynamicColor = false`**: se desactivó el color dinámico de Material You para garantizar un diseño consistente en todos los dispositivos y respetar el contraste de accesibilidad.
- **Paleta ocre / carbón / crema / gris**: rediseño de la paleta original, con roles de color (`primary`, `secondary`, `surface`, etc.) definidos a partir de estos 4 tonos base, más un degradado de fondo aplicado a toda la app.
- **Alerta visual por destello de pantalla, no por linterna**: se prefirió parpadear la pantalla completa en vez de controlar el flash físico de la cámara, para no requerir permiso de cámara y que el efecto sea visible también en el emulador.
- **Imágenes locales en `res/drawable`**: las fotos de las recetas se empaquetan dentro del APK, cargadas con `Image` + `painterResource`.
---

## Tecnologías

| Tecnología | Versión |
|---|---|
| Kotlin | 2.2.10 |
| Jetpack Compose BOM | 2026.02.01 |
| Navigation Compose | 2.9.5 |
| Material Design 3 | incluido en BOM |
| Android mínimo | API 24 (Android 7.0) |
| Android objetivo | API 37 |
