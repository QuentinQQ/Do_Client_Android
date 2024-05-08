package com.example.doapp.ui.onboarding.screens

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.doapp.R
import com.example.doapp.db.ViewModel
import com.example.doapp.db.userinfo.UserInfo
import com.example.doapp.db.userinfo.UserInfoDAO
import com.example.doapp.login.firebaseAuthWithGoogle
import com.example.doapp.login.initiateGoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope



@Composable
fun LoginScreen(
    navController: NavHostController,
    googleSignInClient: GoogleSignInClient,
    auth: FirebaseAuth,
    viewModel: ViewModel
//    signInState: SignInState,
//    signInViewModel: SignInViewModel,
//    googleAuthUiClient: GoogleAuthUiClient,
//    onSignInClick: () -> Unit,
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val db = Firebase.firestore
    val isLoading = remember { mutableStateOf(false) }
    val context = LocalContext.current

    // 创建 Google 登录的启动器
    val googleSignInLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!, auth) { isSuccess, user ->
                    isLoading.value = false
                    if (isSuccess) {
                        val userId = account.id.toString()
                        val userName = account.displayName.toString()
                        val userPhotoUrl = account.photoUrl.toString()
                        val userEmail = account.email
                        coroutineScope.launch {
                        }
                        navController.navigate("home")
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Authentication Failed.")
                        }
                    }
                }
            } catch (e: ApiException) {
                isLoading.value = false
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Google sign-in failed: ${e.localizedMessage}")
                }
            }
        } else {
            isLoading.value = false
            if (result.resultCode == Activity.RESULT_CANCELED) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Google sign-in cancelled")
                }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
//        if (isLoading.value) {
//            CircularProgressIndicator()
//        }

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
//                    isLoading.value = true
//                    initiateGoogleSignIn(googleSignInClient, auth, googleSignInLauncher)
                    isLoading.value = true
//                    val signInIntent = googleSignInClient.signInIntent
//                    googleSignInLauncher.launch(signInIntent)
                    initiateGoogleSignIn(googleSignInClient, googleSignInLauncher)
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
    isLoading.value = false  // Stop loading
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewLogIn() {
//    val navController = rememberNavController()
//    LoginScreen(navController)
//}