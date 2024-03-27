package com.example.doapp.ui.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationbar.NavBarItem
import com.example.doapp.ui.dashboard.Routes
import com.example.doapp.ui.theme.ButtonBlue
import com.example.doapp.ui.theme.LightBackground

@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    val showNewPageOverlay = remember { mutableStateOf(false) }

//    if (showNewPageOverlay.value) {
//        // If the "New" page is visible, the page of the Scrim package will be displayed.
//        New(navController, onDismiss = { showNewPageOverlay.value = false })
//    } else {
    Scaffold(
        bottomBar = {
            BottomNavigation (backgroundColor= LightBackground ){
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                NavBarItem.NavBarItems().forEach {
                        navItem ->
                    val isSelected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                navItem.icon,
                                contentDescription = null,
                                tint = if (isSelected) ButtonBlue else Color.Gray
                            )
                        },
                        label = { Text(navItem.label) },
                        selected = currentDestination?.hierarchy?.any
                        {
                            it.route == navItem.route
                        } == true,
                        onClick = {
                            // When click on "New" button
                            if (navItem.route == Routes.New.value) {
                                showNewPageOverlay.value = true
                            } else {
                                // Others dashboard screen
                                navController.navigate(navItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )

                }
            }
        }
    ) {
            paddingValues ->
        NavHost(
            navController,
            startDestination = Routes.Home.value,
            Modifier.padding(paddingValues)
        ) {
            composable(Routes.Home.value) {
                Home(navController, showNewPageOverlay.value, onShowNewPageChange = { showNewPageOverlay.value = it }) //navController
            }
            composable(Routes.Course.value) {
                Course(navController)
            }
            composable(Routes.New.value) {
                New(navController)
            }
            composable(Routes.History.value) {
                History(navController) //navController
            }
            composable(Routes.Me.value) {
                Me(navController)
            }
        }
    }
}
