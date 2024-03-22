package com.example.doapp.ui.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem (
    val label: String,
    val icon: ImageVector,
    val route: String
)

enum class Routes(val value: String) {
    Home("home"),
    Course("course"),
    New("new"),
    History("history"),
    Me("me")
}

fun NavBarItems(): List<NavBarItem> {
    return listOf(
        NavBarItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = Routes.Home.value
        ),
        NavBarItem(
            label = "Course",
            icon = Icons.Filled.AccountCircle,
            route = Routes.Course.value
        ),
        NavBarItem(
            label = "New",
            icon = Icons.Filled.Person,
            route = Routes.New.value
        ),
        NavBarItem(
            label = "History",
            icon = Icons.Filled.Person,
            route = Routes.History.value
        ),
        NavBarItem(
            label = "Me",
            icon = Icons.Filled.Person,
            route = Routes.Me.value
        )
    )
}
