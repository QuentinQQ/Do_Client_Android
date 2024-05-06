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
//import com.example.doapp.login.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.GoogleAuthProvider



class MainActivity : ComponentActivity() {

    private val viewModel: ViewModel by viewModels()

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        // 配置 Google 登录
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()

        setContent {
//            DoAppTheme {
//                BottomNavigationBar()
//            }
//            FirebaseApp.initializeApp(this)

            val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
            App(lifecycleScope, viewModel, googleSignInClient, auth)

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
