package com.example.bottomnavigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.doapp.ui.dashboard.Routes

data class NavBarItem (
    val label : String = "",
    val icon: ImageVector,
    val route : String = ""
) {
    companion object {
        fun NavBarItems(): List<NavBarItem> {
            return listOf(
                NavBarItem(
                    label = "Home",
                    icon = Icons.Filled.Home,
                    route = Routes.Home.value
                ),
                NavBarItem(
                    label = "Course",
                    icon = Icons.Filled.Build,
                    route = Routes.Course.value
                ),
                NavBarItem(
                    label = "New",
                    icon = Icons.Filled.AddCircle,
                    route = Routes.New.value
                ),
                NavBarItem(
                    label = "History",
                    icon = Icons.Filled.DateRange,
                    route = Routes.History.value
                ),
                NavBarItem(
                    label = "Home",
                    icon = Icons.Filled.Home,
                    route = Routes.Me.value
                )
            )
        }
    }

}
