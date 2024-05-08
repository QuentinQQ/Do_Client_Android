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
import com.example.doapp.db.exercise.fitness_movement.FitnessMovement
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
            // 初始化健身动作数据
//            populateGymEquipmentMovements(viewModel)

            val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
            App(lifecycleScope, viewModel, googleSignInClient, auth)

//            val navController = rememberNavController()
//            AboutDoScreen(navController)

        }
    }
//    private fun populateGymEquipmentMovements(viewModel: ViewModel) {
//        val movements = listOf(
//            FitnessMovement("FM101", "Flat Bench Press", "Chest"),
//            FitnessMovement("FM102", "Incline Bench Press", "Chest"),
//            FitnessMovement("FM103", "Decline Bench Press", "Chest"),
//            FitnessMovement("FM104", "Chest Fly Machine", "Chest"),
//            FitnessMovement("FM105", "Cable Crossovers", "Chest"),
//            FitnessMovement("FM201", "Lat Pull Down", "Back"),
//            FitnessMovement("FM202", "Seated Cable Row", "Back"),
//            FitnessMovement("FM203", "T-Bar Row", "Back"),
//            FitnessMovement("FM204", "Pull-Ups (Assisted)", "Back"),
//            FitnessMovement("FM205", "Back Extension", "Back"),
//            FitnessMovement("FM301", "Leg Press", "Legs"),
//            FitnessMovement("FM302", "Squat Machine", "Legs"),
//            FitnessMovement("FM303", "Leg Curl Machine", "Legs"),
//            FitnessMovement("FM304", "Leg Extension Machine", "Legs"),
//            FitnessMovement("FM305", "Calf Raise Machine", "Legs"),
//            FitnessMovement("FM401", "Shoulder Press Machine", "Shoulders"),
//            FitnessMovement("FM402", "Lateral Raise Machine", "Shoulders"),
//            FitnessMovement("FM403", "Front Raise Cable", "Shoulders"),
//            FitnessMovement("FM404", "Reverse Fly Machine", "Shoulders"),
//            FitnessMovement("FM405", "Dumbbell Shrugs", "Shoulders"),
//            FitnessMovement("FM501", "Bicep Curl Machine", "Arms"),
//            FitnessMovement("FM502", "Tricep Extension Machine", "Arms"),
//            FitnessMovement("FM503", "Cable Bicep Curl", "Arms"),
//            FitnessMovement("FM504", "Cable Tricep Down", "Arms"),
//            FitnessMovement("FM505", "Forearm Curl", "Arms")
//        )
//
//        movements.forEach { movement ->
//            viewModel.insertFitnessMovement(movement)
//        }
//    }
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
