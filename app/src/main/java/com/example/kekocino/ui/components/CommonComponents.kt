package com.example.kekocino.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Componentes reutilizables
 *
 * Este archivo junta las piezas de interfaz que se repiten en varias pantallas
 * (Login, Registro, Recuperar contraseña, Minuta, Detalle de receta, etc.).
 */

/**
 * Campo de texto estándar de la app (input de un formulario).
 *
 * @param value texto actual del campo.
 * @param onValueChange se llama cada vez que la usuaria escribe algo nuevo.
 * @param label texto que aparece arriba del campo (ej.: "Correo electrónico").
 * @param errorMessage si no es null, el campo se pinta en rojo y se muestra
 *   este texto debajo (ej.: "Falta escribir tu correo").
 * @param isPassword si true, el texto se oculta como puntos y aparece el
 *   icono de ojo para mostrarlo/ocultarlo.
 * @param keyboardType qué tipo de teclado mostrar (texto normal, email, etc.).
 */
@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var showPassword by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier
            .widthIn(max = 320.dp)
            .fillMaxWidth(),
        isError = errorMessage != null,
        supportingText = {
            if (errorMessage != null) {
                Text(errorMessage, color = MaterialTheme.colorScheme.error)
            }
        },
        singleLine = true,
        visualTransformation = if (isPassword && !showPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else keyboardType
        ),
        trailingIcon = {
            if (isPassword) {
                Icon(
                    imageVector = if (showPassword) {
                        Icons.Filled.VisibilityOff
                    } else {
                        Icons.Filled.Visibility
                    },
                    contentDescription = if (showPassword) {
                        "Ocultar contraseña"
                    } else {
                        "Mostrar contraseña"
                    },
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { showPassword = !showPassword }
                        .padding(12.dp)
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors()
    )
}

/**
 * Botón principal de la app (ej.: "Entrar", "Crear cuenta", "Enviar instrucciones").
 *
 * @param text lo que dice el botón.
 * @param onClick que pasa cuando se presiona.
 * @param enabled si false, el botón se ve atenuado y no responde al toque.
 *   Se usa por ejemplo en Registro, para evitar enviar el formulario incompleto.
 */
@Composable
fun ButtonPrimary(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .widthIn(max = 320.dp)
            .fillMaxWidth()
            .height(56.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors()
    ) {
        Text(text)
    }
}

// Logo: icono de cocina de Material Icons (no hay gorro de chef en el set).
@Composable
fun AppLogo(
    modifier: Modifier = Modifier,
    size: Dp = 88.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.SoupKitchen,
            contentDescription = "Logo de KekoCino",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(size * 0.55f)
        )
    }
}

@Composable
fun TitlePrimary(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
    )
}

/**
 * Contenedor tipo tarjeta reutilizable para el dominio de recetas
 * (grilla de la minuta y bloques del detalle).
 * -- Similar a Fragment
 *
 * @param modifier modificador externo (ancho, espaciado, etc.).
 * @param onClick si no es null, la tarjeta responde al toque (grilla de la minuta).
 * @param containerColor color de fondo; null usa el color por defecto del tema.
 * @param contentPadding padding interno del contenido; 0.dp si el hijo
 *   gestiona su propio espaciado.
 * @param content slot composable con el cuerpo de la tarjeta.
 */
@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    containerColor: Color? = null,
    contentPadding: Dp = 16.dp,
    content: @Composable () -> Unit
) {
    val colors: CardColors = if (containerColor != null) {
        CardDefaults.cardColors(containerColor = containerColor)
    } else {
        CardDefaults.cardColors()
    }
    val body: @Composable () -> Unit = {
        Column(modifier = Modifier.padding(contentPadding)) {
            content()
        }
    }

    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier.fillMaxWidth(),
            colors = colors,
            content = { body() }
        )
    } else {
        Card(
            modifier = modifier.fillMaxWidth(),
            colors = colors,
            content = { body() }
        )
    }
}
