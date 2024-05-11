package com.example.doapp.ui.onboarding.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.doapp.R
import com.example.doapp.db.ViewModel
import com.example.doapp.db.users.Users
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.doapp.login.containsNoSpaces
import com.example.doapp.login.hasLettersAndDigits
import com.example.doapp.login.isValidEmail


@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: ViewModel,
    auth: FirebaseAuth
    ) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val db = Firebase.firestore

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        // Snackbar host setup
        SnackbarHost(hostState = snackbarHostState)

        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "BACK",
                    modifier = Modifier.size(32.dp),
                    tint = Color.Black
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Control layout
            Spacer(modifier = Modifier.weight(0.5f))

            // top logo
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
            // Sign up text
            Text(
                text = "Sign up",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .wrapContentWidth(Alignment.Start)
                    .padding(vertical = 16.dp)
            )
            // Username Email
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
            // Password
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
//                trailingIcon = {
//                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                        Icon(
//                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.Visibility,
//                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
//                        )
//                    }
//                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Default.Visibility
                    else Icons.Default.Visibility

                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(imageVector  = image, "")
                    }
                },
                label = { Text("Password") },
                placeholder = { Text("Enter your password") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                shape = RoundedCornerShape(50)
            )
            // confirm password
            OutlinedTextField(
                value = confirmPassword.value,
                onValueChange = { confirmPassword.value = it },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (confirmPasswordVisible)
                        Icons.Default.Visibility
                    else Icons.Default.Visibility

                    IconButton(onClick = {confirmPasswordVisible = !confirmPasswordVisible}){
                        Icon(imageVector  = image, "")
                    }
                },
                label = { Text("Confirm") },
                placeholder = { Text("Confirm your password") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                shape = RoundedCornerShape(50)
            )

            Spacer(modifier = Modifier.height(16.dp))
            // Sign up button
            Button(
                onClick = {
                    if (!isValidEmail(email.value)) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Please enter a valid email address")
                        }
                        return@Button
                    }
                    if (!containsNoSpaces(password.value)) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Password cannot contain spaces")
                        }
                        return@Button
                    }
                    if (!hasLettersAndDigits(password.value)) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Password must include upper and lower case letters and digits")
                        }
                        return@Button
                    }
                    if (password.value != confirmPassword.value) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Passwords do not match")
                        }
                        return@Button
                    }
                    if (
                        password.value == confirmPassword.value
                        && password.value.isNotEmpty()
                        && confirmPassword.value.isNotEmpty()
                    ){
                        // Create user data hash map
                        val userHashMap = hashMapOf("email" to email.value, "password" to password.value)
                        // 本地Room存储用户数据
//                        val user = Users(email = email.value, password = password.value)
//                        viewModel.insertUsers(user)
                        // 用户数据同步云端
                        // Create user data hash map
//                        val userHashMap = hashMapOf("uid" to uid,"email" to email.value, "password" to password.value)
                        // Add user to Firestore
                        db.collection("users").add(userHashMap).addOnSuccessListener {documentReference ->
                        // Firestore 自动生成的 UID
                            val firestoreUid = documentReference.id
                            val newUser = Users(uid = firestoreUid, email = email.value, password = password.value)
                            coroutineScope.launch {
                                viewModel.insertUsers(newUser)
                            }
                            navController.navigate("login") { popUpTo("signup") { inclusive = true } }
//                            Toast.makeText(LocalContext.current, "Signup successful", Toast.LENGTH_LONG).show()
                        }.addOnFailureListener { e ->
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Error adding document: ${e.message}")
                            }
                        }
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Passwords do not match", duration = SnackbarDuration.Short)
                        }
                    }
                },
//                onClick = {
//                    if (password.value == confirmPassword.value) {
//                        coroutineScope.launch {
//                            try {
//                                val user = hashMapOf("email" to email.value, "password" to password.value)
//                                db.collection("users").add(user).addOnSuccessListener {
//                                    navController.navigate("login") { popUpTo("signup") { inclusive = true } }
//                                }.addOnFailureListener { e ->
//                                    snackbarHostState.showSnackbar("Error adding document: ${e.message}")
//                                }
//                            } catch (e: Exception) {
//                                snackbarHostState.showSnackbar("Failed to create user: ${e.message}")
//                            }
//                        }
//                    } else {
//                        coroutineScope.launch {
//                            snackbarHostState.showSnackbar("Passwords do not match", duration = SnackbarDuration.Short)
//                        }
//                    }
//                },
                modifier = Modifier.fillMaxWidth(0.8f).height(60.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3757FF), contentColor = Color.White)
            ) {
                Text("Sign Up", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
            // Control layout
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


//fun createAccount(
//    email: String,
//    password: String,
//    auth: FirebaseAuth
//){
//    auth.createUserWithEmailAndPassword(email, password)
//        .addOnCompleteListener(this) { task ->
//            if (task.isSuccessful) {
//                // Sign in success, update UI with the signed-in user's information
//                Log.d(TAG, "createUserWithEmail:success")
//                val user = auth.currentUser
//                updateUI(user)
//            } else {
//                // If sign in fails, display a message to the user.
//                Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                Toast.makeText(
//                    baseContext,
//                    "Authentication failed.",
//                    Toast.LENGTH_SHORT,
//                ).show()
//                updateUI(null)
//            }
//        }
//}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSignUpScreen() {
//    val navController = rememberNavController()
//    val viewModel: ViewModel
//    SignUpScreen(navController, viewModel)
//}