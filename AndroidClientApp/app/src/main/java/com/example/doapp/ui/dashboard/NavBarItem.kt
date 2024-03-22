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
    val iconHome : ImageVector = Icons.Filled.Home,
    val iconCourse : ImageVector = Icons.Filled.Build,
    val iconNew : ImageVector = Icons.Filled.AddCircle,
    val iconHistory : ImageVector = Icons.Filled.DateRange,
    val iconMe : ImageVector = Icons.Filled.AccountCircle,
    val route : String = ""
) {
    fun NavBarItems(): List<NavBarItem> {
        return listOf(
            NavBarItem(
                label = "Home",
                iconHome = Icons.Filled.Home,
                route = Routes.Home.value
            ),
            NavBarItem(
                label = "Course",
                iconCourse = Icons.Filled.Build,
                route = Routes.Course.value
            ),
            NavBarItem(
                label = "New",
                iconNew = Icons.Filled.AddCircle,
                route = Routes.New.value
            ),
            NavBarItem(
                label = "Home",
                iconHistory = Icons.Filled.DateRange,
                route = Routes.History.value
            ),
            NavBarItem(
                label = "Home",
                iconMe = Icons.Filled.Home,
                route = Routes.Me.value
            )
        )
    }

}
