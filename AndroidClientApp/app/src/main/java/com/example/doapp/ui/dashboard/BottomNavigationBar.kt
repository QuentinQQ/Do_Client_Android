package com.example.doapp.ui.dashboard

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination.Companion.hierarchy import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationbar.NavBarItem
import com.example.doapp.login.UserData
//import com.example.doapp.login.GoogleAuthUiClient
import com.example.doapp.ui.NewScreenScrim
import com.example.doapp.ui.dashboard.HomeSubScreens.DetailsScreen
import com.example.doapp.ui.dashboard.Routes
import com.example.doapp.ui.dashboard.myprofile.AboutDoScreen
import com.example.doapp.ui.dashboard.myprofile.HelpScreen
import com.example.doapp.ui.theme.ButtonBlue
import com.example.doapp.ui.theme.LightBackground
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    showNewPageOverlay: MutableState<Boolean>
) {
    BottomNavigation(backgroundColor = LightBackground) {
        NavBarItem.NavBarItems().forEach { navItem ->
            // 判断当前项是否被选中
            val isSelected = navController.currentBackStackEntryAsState().value?.destination?.route == navItem.route

            BottomNavigationItem(
                icon = {
                    Icon(
                        navItem.icon,
                        contentDescription = null,
                        // 当前选中时图标为ButtonBlue，否则为Gray
                        tint = if (isSelected) ButtonBlue else Color.Gray
                    )
                },
                label = { Text(navItem.label) },
                selected = isSelected,
                onClick = {
                    if (navItem.route == Routes.New.value) {
                        showNewPageOverlay.value = true
                    } else {
                        if (navController.currentDestination?.route != navItem.route) {
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                }
            )
        }
    }
}



@Composable
fun MainNavigationBar(
//    googleAuthUiClient: GoogleAuthUiClient,
    lifecycleScope: CoroutineScope,
    googleSignInClient: GoogleSignInClient,
    auth: FirebaseAuth,
    userData: UserData?,
    context: Context = LocalContext.current
) {
    val navController = rememberNavController()
    val showNewPageOverlay = remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController, showNewPageOverlay)
        }
    ) {
        paddingValues ->
        NavHost(
            navController,
            startDestination = Routes.Home.value,
            modifier = Modifier.padding(paddingValues)
        ) {

            composable(Routes.Home.value) {
                Home(navController,
                    showNewPageOverlay
                ) //navController
            }
            composable(Routes.Course.value) {
                Course(
                    navController,
                    showNewPageOverlay
                )
            }
            composable(Routes.History.value) {
                History(
                    navController,
                    showNewPageOverlay
                ) //navController
            }
            composable(Routes.Me.value) {
                Me(
                    navController,
                    showNewPageOverlay,
                    googleSignInClient,
                    auth,
                    userData = userData
//                    userData = googleAuthUiClient.getSignedInUser(),
//                    onSignOut = {
//                        lifecycleScope.launch {
//                            googleAuthUiClient.signOut()
//                            Toast.makeText(
//                                context,
//                                "Signed out",
//                                Toast.LENGTH_LONG
//                            ).show()
//
//                            navController.popBackStack()
//                        }
//                    }
                )
            }
            composable(Routes.AboutDo.value) {
                AboutDoScreen(
                    navController
                ) //navController
            }
            composable(Routes.HelpAndFeedback.value){
                HelpScreen(navController = navController)
            }
            composable(Routes.DetailScreen.value) {
                DetailsScreen(
                    navController
                ) //navController
            }
        }
    }
    if (showNewPageOverlay.value) {
        NewScreenScrim(navController, onDismiss = { showNewPageOverlay.value = false })
    }
}