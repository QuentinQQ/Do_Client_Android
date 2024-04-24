package com.example.doapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationbar.NavBarItem
import com.example.doapp.ui.dashboard.BottomNavigationBar
import com.example.doapp.ui.dashboard.Course
import com.example.doapp.ui.dashboard.Home
import com.example.doapp.ui.dashboard.Me
import com.example.doapp.ui.dashboard.History
import com.example.doapp.ui.dashboard.New
import com.example.doapp.ui.dashboard.MainNavigationBar
import com.example.doapp.ui.NewScreenScrim
import com.example.doapp.ui.dashboard.Routes
import com.example.doapp.ui.onboarding.screens.LoginScreen
import com.example.doapp.ui.onboarding.screens.SignUpScreen
import com.example.doapp.ui.theme.DoAppTheme
import com.example.doapp.ui.theme.LightBackground

@Composable
fun App() {
    val navController = rememberNavController()
    val showNewPageOverlay = remember { mutableStateOf(false) }
    val showOverlay = remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        NavHost(navController = navController, startDestination = "login") {
            composable("login") { LoginScreen(navController) }  // 确保 LoginScreen 可以进行导航
            composable("signup") { SignUpScreen(navController) }

            composable("home") { Home(navController, showOverlay) }  // 假设有一个 HomeScreen
            composable("course") { Course(navController, showOverlay) }  // 假设有一个 CourseScreen
            composable("history") { History(navController, showOverlay) }  // 假设有一个 HistoryScreen
            composable("me") { Me(navController, showOverlay) }  // 假设有一个 ProfileScreen
        }

        // 使用当前后退栈条目获取当前路由
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        if (currentRoute != "login" && currentRoute != "signup" && !showNewPageOverlay.value) {
            MainNavigationBar()
        }
    }
}


@Composable
fun NewScreenScrim(navController: NavHostController, onDismiss: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(alpha = 0.8f))
            .clickable(enabled = true) {}
    ) {
        // 实现 New 页面的 UI
        New(navController, onDismiss)
    }
}