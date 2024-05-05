package com.example.doapp.ui.onboarding.screens

import android.content.IntentSender
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnitType.Companion.Sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.startIntentSenderForResult
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.doapp.R
import com.example.doapp.login.GoogleAuthUiClient
import com.example.doapp.login.SignInState
import com.example.doapp.login.SignInViewModel
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


@Composable
fun LoginScreen(
    navController: NavHostController,
    signInState: SignInState,
    onSignInClick: () -> Unit,
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val db = Firebase.firestore
    val isLoading = remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(key1 = signInState.signInError) {
        signInState.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
//    val signInClient: SignInClient = Identity.getSignInClient(context)
//    val signInRequest = BeginSignInRequest.builder()
//
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.StartIntentSenderForResult()
//    ) { result ->
//        if (result.resultCode == ComponentActivity.RESULT_OK) {
//            val credential = Identity.getSignInClient(context).getSignInCredentialFromIntent(result.data)
//            val idToken = credential.googleIdToken
//            // 这里可以使用 idToken 进行进一步的处理，例如使用Firebase进行身份验证
//        }
//    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SnackbarHost(hostState = snackbarHostState)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.weight(0.5f))

            Box(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .background(Color.Transparent)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.client_logo),
                    contentDescription = "Client logo",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Text(
                text = "Log in",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .wrapContentWidth(Alignment.Start)
                    .padding(vertical = 16.dp)
            )

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                leadingIcon = { Icon(Icons.Filled.AccountCircle, contentDescription = null) },
                label = { Text("Email") },
                placeholder = { Text("Enter your email") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                shape = RoundedCornerShape(50)
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                label = { Text("Password") },
                placeholder = { Text("Enter your password") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                shape = RoundedCornerShape(50)
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading.value) {
                CircularProgressIndicator()  // Show progress indicator if loading
            } else {
                Button(
                    onClick = {
                        handleLogin(email.value, password.value, db, snackbarHostState, coroutineScope, navController, isLoading)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(60.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3757FF),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        "Login now",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Switch to other option
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ){
                Text(
                    text = "Forget Password？",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 16.dp),
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Sign up",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable {
                            navController.navigate("signup")
                        },
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.weight(1f))


            Button(
                onClick = {
                    onSignInClick()
                          //第一种
//                    // 使用rememberCoroutineScope获取到的协程作用域来启动新协程
//                    coroutineScope.launch {
//                        // 调用signIn挂起函数，尝试获取IntentSender
//                        val signInIntentSender = googleLogin.signIn()
//                        // 如果获取成功，则构建IntentSenderRequest并启动它
//                        signInIntentSender?.let {
//                            val request = IntentSenderRequest.Builder(it).build()
//                            signInResultLauncher.launch(request)  // 在ActivityResultLauncher中启动IntentSender
//                        }
//                    }
                          //第二种
//                    coroutineScope.launch {
//                        googleLogin.signIn()?.let { intentSender ->
//                            val intentSenderRequest = IntentSenderRequest.Builder(intentSender).build()
//                            launcher.launch(intentSenderRequest)
//                        }
//                    }
                          //第三种
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF7F7F7)
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = "Google Login",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


fun handleLogin(
    email: String,
    password: String,
    db: FirebaseFirestore,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
    isLoading: MutableState<Boolean>
) {
    isLoading.value = true  // Start loading

    if (email.isEmpty() || password.isEmpty()) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar("Email and password cannot be empty", duration = SnackbarDuration.Short)
            isLoading.value = false // Stop loading
        }
        return
    }

    db.collection("users")
        .whereEqualTo("email", email)
        .get()
        .addOnSuccessListener { documents ->
            if (documents.isEmpty) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Invalid login or password.", duration = SnackbarDuration.Short)
                }
            } else {
                var loginStatus = false
                for (document in documents) {
                    if (document.getString("password") == password) {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                        loginStatus = true
                        break
                    }
                }
                if (!loginStatus) {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Invalid login or password.", duration = SnackbarDuration.Short)
                    }
                }
            }
        }
        .addOnFailureListener { exception ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar("Failed to query database: ${exception.message}", duration = SnackbarDuration.Short)
            }
        }
    isLoading.value = false // Stop loading
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewLogIn() {
//    val navController = rememberNavController()
//    LoginScreen(navController)
//}