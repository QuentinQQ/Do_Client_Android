package com.example.doapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.D
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.doapp.ui.dashboard.BottomNavigationBar
import com.example.doapp.ui.dashboard.myprofile.PersonalInfoScreen
import com.example.doapp.ui.onboarding.screens.LoginScreen
import com.example.doapp.ui.theme.DoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            DoAppTheme {
//                BottomNavigationBar()
//            }
            BottomNavigationBar()
//            PersonalInfoScreen()

        }
    }
}