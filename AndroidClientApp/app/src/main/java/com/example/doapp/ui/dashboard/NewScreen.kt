package com.example.doapp.ui.dashboard

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.doapp.R
import com.example.doapp.ui.theme.ButtonBlue


@Composable
fun New (
    navController: NavHostController,
) {
    // Scrim Layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(alpha = 0.7f))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize()
        ) {
            // Adjust the position of the button in the vertical direction of the screen
            Spacer(modifier = Modifier.height(300.dp))
            // add button
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ButtonWithIconAndText(iconResourceId = R.drawable.new_training_logo, text = "New Training")
                Spacer(modifier = Modifier.width(80.dp))
                ButtonWithIconAndText(iconResourceId = R.drawable.new_diet_logo, text = "New Diet")
            }
        }

        // add cancel button at the bottom
        FloatingActionButton(
            onClick = { /* TODO: handle click */ },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            containerColor = ButtonBlue
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Back")
        }
    }
}

@Composable
fun ButtonWithIconAndText(@DrawableRes iconResourceId: Int, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentSize()
    ) {
        Icon(
            painter = painterResource(id = iconResourceId),
            contentDescription = text,
            tint = Color.Unspecified,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

//@Preview
//@Composable
//fun PreviewMainScreen() {
//    New()
//}
