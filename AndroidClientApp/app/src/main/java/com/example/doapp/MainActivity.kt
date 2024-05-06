package com.example.doapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.FirebaseApp
import androidx.lifecycle.lifecycleScope
import com.example.doapp.ui.App

import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.example.doapp.db.ViewModel


class MainActivity : ComponentActivity() {

    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
//            DoAppTheme {
//                BottomNavigationBar()
//            }
//            FirebaseApp.initializeApp(this)

            val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
            App(lifecycleScope, viewModel)

//            val navController = rememberNavController()
//            AboutDoScreen(navController)

        }
    }
}


//class MainActivity : ComponentActivity() {
//    private lateinit var googleLogin: GoogleLogin
//    private lateinit var signInViewModel: SignInViewModel

//    // 注册ActivityResult启动器
//    private val signInResultLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
//        if (result.resultCode == RESULT_OK) {
//            val data: Intent? = result.data
//            // 使用非空检查确保 data 不为 null
//            data?.let {
//                lifecycleScope.launch {
//                    // 在这里调用 signInWithIntent，data 已确保非空
//                    googleLogin.signInWithIntent(it).let { signInResult ->
//                        signInViewModel.onSignInResult(signInResult)
//                    }
//                }
//            }
//        }
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        FirebaseApp.initializeApp(this)
//        googleLogin = GoogleLogin(this)
//        signInViewModel = SignInViewModel()
//        setContent {
//            App()
//        }
//    }
//
//    @Composable
//    fun App() {
//        val navController = rememberNavController()
//        DoAppTheme {
//            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                NavHost(navController, startDestination = "login") {
//                    composable("login") { LoginScreen(navController
////                        googleLogin, signInViewModel, signInResultLauncher
//                    ) }
//                    // 可以在此添加其他导航目的地
//                }
//            }
//        }
//    }
//}
