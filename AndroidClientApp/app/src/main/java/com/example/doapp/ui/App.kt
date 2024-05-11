package com.example.doapp.ui

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
//import com.example.doapp.login.GoogleAuthUiClient
import com.example.doapp.ui.onboarding.screens.LoginScreen
//import com.example.doapp.login.SignInViewModel

import com.example.doapp.ui.dashboard.Course
import com.example.doapp.ui.dashboard.Home
import com.example.doapp.ui.dashboard.Me
import com.example.doapp.ui.dashboard.History
import com.example.doapp.ui.dashboard.New
import com.example.doapp.ui.dashboard.MainNavigationBar
import com.example.doapp.ui.onboarding.screens.SignUpScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.livedata.observeAsState
import com.example.doapp.db.ViewModel
import com.example.doapp.db.users.Users
import com.example.doapp.login.UserData
import com.example.doapp.ui.dashboard.Routes
import com.example.doapp.ui.dashboard.course.CourseDetailsScreen
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth

@Composable
fun App(
    lifecycleScope: CoroutineScope,
    viewModel: ViewModel,
    googleSignInClient: GoogleSignInClient,
    auth: FirebaseAuth
) {
    val navController = rememberNavController()
    val showNewPageOverlay = remember { mutableStateOf(false) }
    val showOverlay = remember { mutableStateOf(false) }
    val context = androidx.compose.ui.platform.LocalContext.current

    // initial room viewModel
    val users by viewModel.allUsers.observeAsState()
    val selectedUser = remember { mutableStateOf<Users?>(null) }
    val insertDialog = remember { mutableStateOf(false) }

    val userData = remember { mutableStateOf<UserData?>(null) }


    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
//

                LoginScreen(
                    navController,
                    googleSignInClient,
                    auth,
                    viewModel
                )
            }
//            composable("login") { LoginScreen(navController) }  // 确保 LoginScreen 可以进行导航
            composable("signup") { SignUpScreen(navController, viewModel, auth) }
//            composable("about") { AboutDoScreen(navController)}
            composable("home") { Home(navController, showOverlay) }  // 假设有一个 HomeScreen
            composable("course") { Course(navController, showOverlay) }  // 假设有一个 CourseScreen
            composable("history") { History(navController, showOverlay) }  // 假设有一个 HistoryScreen
            composable("me") { Me(
                navController,
                showOverlay,
                googleSignInClient,
                auth,
                viewModel,
                context
//                userData = userData.value

//                userData = googleAuthUiClient.getSignedInUser(),
//                onSignOut = {
//                    lifecycleScope.launch {
//                        googleAuthUiClient.signOut()
//                        Toast.makeText(
//                            context,
//                            "Signed out",
//                            Toast.LENGTH_LONG
//                        ).show()
//                        navController.popBackStack()
//                    }
//                }
            ) }
        }

        // 使用当前后退栈条目获取当前路由
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        if (currentRoute != "login" && currentRoute != "signup" && !showNewPageOverlay.value) {
            MainNavigationBar(
                lifecycleScope,
                googleSignInClient,
                auth,
                viewModel
//                userData = userData.value
            )
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
