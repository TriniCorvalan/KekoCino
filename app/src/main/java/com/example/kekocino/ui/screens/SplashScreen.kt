package com.example.kekocino.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kekocino.ui.components.AppLogo
import com.example.kekocino.ui.components.TitlePrimary
import com.example.kekocino.ui.theme.KekoCinoTheme
import kotlinx.coroutines.delay

/**
 * Pantalla de arranque. Muestra el logo un segundo y medio y sigue al login.
 * Es una pantalla Compose, no el splash del sistema, para poder capturarla.
 */
@Composable
fun SplashScreen(onFinished: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(1500)
        onFinished()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppLogo(size = 120.dp)
        TitlePrimary(
            text = "KekoCino",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    KekoCinoTheme {
        SplashScreen(onFinished = {})
    }
}
