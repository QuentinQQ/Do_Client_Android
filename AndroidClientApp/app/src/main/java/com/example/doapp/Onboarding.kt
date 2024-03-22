package com.example.doapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//@Composable
//fun SplashAnimation() {
//
//}

//@Composable
//fun LoginScreen() {
//    val email = remember { mutableStateOf("") }
//    val password = remember { mutableStateOf("") }
////    val image =
//
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = colorScheme.background
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(20.dp, 20.dp)
//                    .background(Color.Blue)
//            ) {
//                "占位 后续补充image"
//            }
//
//            Text(
//                text = "Login in to Do",
//                style = MaterialTheme.typography.displayMedium,
//                modifier = Modifier.padding(vertical = 16.dp)
//            )
//
//            Surface(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(20.dp),
//                shape = RoundedCornerShape(16.dp),
//                tonalElevation = 4.dp,
//                color = MaterialTheme.colorScheme.onSecondary
//            ) {
//                Column(
//                    modifier = Modifier.padding(16.dp)
//                ) {
//                    OutlinedTextField(
//                        value = email.value,
//                        onValueChange = { email.value = it },
//                        leadingIcon = { Icon(Icons.Filled.AccountCircle, contentDescription = null) },
//                        label = { Text("Email") },
//                        placeholder = { Text("Enter your email") },
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//
//                    Divider(color = Color.LightGray, thickness = 5.dp)
//
//                    OutlinedTextField(
//                        value = password.value,
//                        onValueChange = { password.value = it },
//                        leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
//                        label = { Text("Password") },
//                        placeholder = { Text("Enter your email") },
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.weight(1f))
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(
//                onClick = {
//                    // Handle login logic here
//                },
//                modifier = Modifier
//                    .width(200.dp)
//                    .height(100.dp)
//                    .padding(vertical = 20.dp),
//                shape = RoundedCornerShape(50),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF3757FF),
//                    contentColor = Color.White
//                )
//            ) {
//                Text(
//                    "Login now",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.White
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//        }
//    }
//}

//@Composable
//fun SignUp() {
//
//}
//
//@Composable
//fun CreatePassword() {
//
//}

