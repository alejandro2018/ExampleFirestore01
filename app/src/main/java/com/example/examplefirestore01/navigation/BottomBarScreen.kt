package com.example.examplefirestore01.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Inicio : BottomBarScreen(
        route = "inicio",
        title = "Inicio",
        icon = Icons.Default.Home
    )

    object Setting : BottomBarScreen(
        route = "setting",
        title = "Setting",
        icon = Icons.Default.Person
    )
}