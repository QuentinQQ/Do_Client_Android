package com.example.doapp.ui.onboarding.screens

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.doapp.R

@Composable
fun SignUpScreen(navController: NavHostController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
//    val image =

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "BACK",
                modifier = Modifier
                    .size(32.dp),
                tint = Color.Black
            )
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
                    // 假设注册成功
                    navController.navigate("login") {
                        popUpTo("signup") { inclusive = true }  // 清除回退栈中的注册页面
                    }
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
                    "Sign Up",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            // Control layout
            Spacer(modifier = Modifier.weight(1f))
        }


//            Spacer(modifier = Modifier.weight(1f))
//            Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    val navController = rememberNavController()
    SignUpScreen(navController)
}