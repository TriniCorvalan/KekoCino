package com.example.kekocino.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kekocino.data.Recipe
import com.example.kekocino.data.weeklyRecipes
import com.example.kekocino.ui.theme.CremaSuave
import com.example.kekocino.ui.theme.Ocre
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Widget Compose in-app (Semana 6): muestra la receta del día
 * según el calendario, con colores de marca KekoCino.
 */
@Composable
fun RecetaDelDiaWidget(
    onRecipeClick: ((Recipe) -> Unit)? = null
) {
    val calendar = Calendar.getInstance()
    val locale = Locale("es", "CL")

    val formatoFecha = SimpleDateFormat("EEEE dd 'de' MMMM", locale)
    val fecha = formatoFecha.format(calendar.time)

    val diaSemana = when (calendar.get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> "Lunes"
        Calendar.TUESDAY -> "Martes"
        Calendar.WEDNESDAY -> "Miércoles"
        Calendar.THURSDAY -> "Jueves"
        Calendar.FRIDAY -> "Viernes"
        else -> null
    }

    val recetaHoy = diaSemana?.let { dia ->
        weeklyRecipes.find { it.day == dia }
    }

    val tipCorto = recetaHoy?.nutritionalTip?.let { tip ->
        if (tip.length <= 80) tip else tip.take(80).trimEnd() + "…"
    }

    val tiempoTotal = recetaHoy?.steps?.sumOf { it.minutes } ?: 0

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (recetaHoy != null && onRecipeClick != null) {
                    Modifier.clickable { onRecipeClick(recetaHoy) }
                } else {
                    Modifier
                }
            ),
        colors = CardDefaults.cardColors(containerColor = Ocre)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Receta del día",
                style = MaterialTheme.typography.titleLarge,
                color = CremaSuave
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = fecha.replaceFirstChar { it.uppercase() },
                color = CremaSuave
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (recetaHoy != null) {
                Text(
                    text = "${recetaHoy.day}: ${recetaHoy.name}",
                    style = MaterialTheme.typography.titleMedium,
                    color = CremaSuave
                )

                Text(
                    text = "${recetaHoy.calories} kcal · $tiempoTotal min de cocción",
                    color = CremaSuave
                )

                if (tipCorto != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = tipCorto,
                        style = MaterialTheme.typography.bodyMedium,
                        color = CremaSuave.copy(alpha = 0.9f)
                    )
                }
            } else {
                Text(
                    text = "Hoy descanso — la minuta retoma el lunes",
                    style = MaterialTheme.typography.titleMedium,
                    color = CremaSuave
                )
            }
        }
    }
}
