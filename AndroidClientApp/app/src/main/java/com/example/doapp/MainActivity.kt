package com.example.doapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.FirebaseApp
import androidx.lifecycle.lifecycleScope
import com.example.doapp.ui.App

import androidx.activity.viewModels
import androidx.compose.material3.SnackbarDuration
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.example.doapp.dataProcess.convertFitnessMovementToFirestore
import com.example.doapp.db.ViewModel
import com.example.doapp.db.exercise.fitness_movement.FitnessMovement
import com.example.doapp.db.exercise.official_course.EachActionDetail
import com.example.doapp.db.exercise.official_course.OfficialCourse
import com.example.doapp.db.exercise.official_course.OfficialCourseSchedule
//import com.example.doapp.login.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


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
            val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
            App(lifecycleScope, viewModel, googleSignInClient, auth)

//            val navController = rememberNavController()
//            AboutDoScreen(navController)

        }

//        populateGymEquipmentMovements(viewModel) // 初始化健身动作数据
//        populateInitialData() // 初始化官方训练计划, 仅初始化其中一个
//        uploadDataToFirestore()

    }



//     初始化 fitness movement的数据
    private fun populateGymEquipmentMovements(viewModel: ViewModel) {
        val movements = listOf(
            FitnessMovement("FM101", "Flat Bench Press", "Chest"),
            FitnessMovement("FM102", "Incline Bench Press", "Chest"),
            FitnessMovement("FM103", "Decline Bench Press", "Chest"),
            FitnessMovement("FM104", "Chest Fly Machine", "Chest"),
            FitnessMovement("FM105", "Cable Crossovers", "Chest"),
            FitnessMovement("FM201", "Lat Pull Down", "Back"),
            FitnessMovement("FM202", "Seated Cable Row", "Back"),
            FitnessMovement("FM203", "T-Bar Row", "Back"),
            FitnessMovement("FM204", "Pull-Ups (Assisted)", "Back"),
            FitnessMovement("FM205", "Back Extension", "Back"),
            FitnessMovement("FM301", "Leg Press", "Legs"),
            FitnessMovement("FM302", "Squat Machine", "Legs"),
            FitnessMovement("FM303", "Leg Curl Machine", "Legs"),
            FitnessMovement("FM304", "Leg Extension Machine", "Legs"),
            FitnessMovement("FM305", "Calf Raise Machine", "Legs"),
            FitnessMovement("FM401", "Shoulder Press Machine", "Shoulders"),
            FitnessMovement("FM402", "Lateral Raise Machine", "Shoulders"),
            FitnessMovement("FM403", "Front Raise Cable", "Shoulders"),
            FitnessMovement("FM404", "Reverse Fly Machine", "Shoulders"),
            FitnessMovement("FM405", "Dumbbell Shrugs", "Shoulders"),
            FitnessMovement("FM501", "Bicep Curl Machine", "Arms"),
            FitnessMovement("FM502", "Tricep Extension Machine", "Arms"),
            FitnessMovement("FM503", "Cable Bicep Curl", "Arms"),
            FitnessMovement("FM504", "Cable Tricep Down", "Arms"),
            FitnessMovement("FM505", "Forearm Curl", "Arms")
        )

        movements.forEach { movement ->
            viewModel.insertFitnessMovement(movement)
        }
    }


    // 初始化official course的示例数据, 只初始化了Basic Full Body Workout for Male
    private fun populateInitialData() {
        lifecycleScope.launch {
            // Insert movements here, or check if they exist before inserting
            // For example purposes, insert a course and its schedules
            val course = OfficialCourse("OC1001", "Basic Full Body Workout For Male", 7, "Full body workout for beginners.")
            viewModel.insertOfficialCourse(course)

            // Create and insert schedules
            val schedules = listOf(
                OfficialCourseSchedule("SCH1001", "OC1001", "Chest and back",1),
                OfficialCourseSchedule("SCH1002", "OC1001", "Legs",2),
                OfficialCourseSchedule("SCH1003", "OC1001", "Rest",3),
                OfficialCourseSchedule("SCH1004", "OC1001", "Shoulders and arms",4),
                OfficialCourseSchedule("SCH1005", "OC1001", "Rest",5),
                OfficialCourseSchedule("SCH1006", "OC1001", "Chest and back again",6),
                OfficialCourseSchedule("SCH1007", "OC1001", "Rest",7),
            )
            schedules.forEach { schedule ->
                viewModel.insertOfficialCourseSchedule(schedule)
            }

            // Insert action details assuming movements are pre-defined and have specific IDs
            val actionDetails = listOf(
                EachActionDetail("AD101", "SCH1001", "FM101", 1, 3, 20.0), // 平板杠铃卧推
                EachActionDetail("AD102", "SCH1001", "FM204", 2, 3, 0.0), // 引体向上（假设用体重）
                EachActionDetail("AD103", "SCH1001", "FM102", 3, 3, 15.0), // 斜板杠铃卧推
                EachActionDetail("AD104", "SCH1001", "FM202", 4, 3, 25.0),  // 单臂哑铃划船（替代）
                EachActionDetail("AD201", "SCH1002", "FM301", 1, 3, 40.0), // 腿部推蹬
                EachActionDetail("AD202", "SCH1002", "FM302", 2, 3, 30.0), // 深蹲
                EachActionDetail("AD203", "SCH1002", "FM303", 3, 3, 20.0), // 腿弯举
                EachActionDetail("AD204", "SCH1002", "FM305", 4, 3, 10.0),  // 小腿提踵
                EachActionDetail("AD301", "SCH1004", "FM401", 1, 3, 20.0), // 肩部推举
                EachActionDetail("AD302", "SCH1004", "FM402", 2, 3, 5.0),  // 哑铃侧平举（替代）
                EachActionDetail("AD303", "SCH1004", "FM101", 3, 3, 20.0), // 杠铃卧推
                EachActionDetail("AD304", "SCH1004", "FM503", 4, 3, 10.0), // 弯举（替代）
                EachActionDetail("AD305", "SCH1004", "FM502", 5, 3, 10.0),  // 颈后臂屈伸（替代）
                EachActionDetail("AD401", "SCH1006", "FM105", 1, 3, 5.0),  // 哑铃飞鸟（替代）
                EachActionDetail("AD402", "SCH1006", "FM201", 2, 3, 20.0), // 坡度拉力器下拉（替代）
                EachActionDetail("AD403", "SCH1006", "FM101", 3, 3, 20.0), // 杠铃卧推
                EachActionDetail("AD404", "SCH1006", "FM105", 4, 3, 5.0)   // 哑铃飞鸟（替代）
            )
            actionDetails.forEach { detail ->
                viewModel.insertEachActionDetail(detail)
            }
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
