package com.example.doapp.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.doapp.R
import com.example.doapp.ui.theme.DarkSecondary
import com.example.doapp.ui.theme.LightFouth

@Composable
fun Me(
//    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .background(Color(0xFFF4F5F6))
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // 1. First Part, including profile photo and username
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberVectorPainter(image = Icons.Filled.AccountCircle),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                )

                Spacer(Modifier.width(20.dp))

                Column {
                    Text(
                        text = "Quentin",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                    Text(text = "User ID: 7894495")
                }
            }

        }
        // 2. Second Part, including body data
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(2.dp, Color.Gray, RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            // Left side
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Column (
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                ){
                    Row {
                        Text(
                            text = "My Body Data",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Icons.AutoMirrored.Filled
                    }
                    Row {
                        Text("180cm")
                        Spacer(Modifier.width(20.dp))
                        Text("70kg")
                        Spacer(Modifier.width(20.dp))
                        Text("21.6")
                    }
                    Row {
                        Text("Height")
                        Spacer(Modifier.width(20.dp))
                        Text("Weight")
                        Spacer(Modifier.width(20.dp))
                        Text("BMI")
                    }

                }

                // Right side
                Image(
                    painter = painterResource(id = R.drawable.bar_chart),
                    contentDescription = "BarChart",
                    modifier = Modifier.size(100.dp)
                )
            }


        }

        // 3. Third Part, including other option
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .border(2.dp, Color.Gray, RoundedCornerShape(20.dp))
                        .background(Color(0xFFFFFFFF)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberVectorPainter(image = Icons.Filled.Person),
                        contentDescription = "Personal Information",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(30.dp)
                    )
                    Spacer(Modifier.width(20.dp))
                    Text("Personal Information")
                    Spacer(Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Arrow",
                        modifier = Modifier
                            .padding(end = 10.dp)
                    )
                }

                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .border(2.dp, Color.Gray, RoundedCornerShape(20.dp))
                        .background(Color(0xFFFFFFFF)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberVectorPainter(image = Icons.Filled.Settings),
                        contentDescription = "Preferences",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(30.dp)
                    )
                    Spacer(Modifier.width(20.dp))
                    Text("Preferences")
                    Spacer(Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Arrow",
                        modifier = Modifier
                            .padding(end = 10.dp)
                    )
                }

                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .border(2.dp, Color.Gray, RoundedCornerShape(20.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberVectorPainter(image = Icons.Filled.Email),
                        contentDescription = "Call",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(30.dp)
                    )
                    Spacer(Modifier.width(20.dp))
                    Text("Help & Feedback")
                    Spacer(Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Arrow",
                        modifier = Modifier
                            .padding(end = 10.dp)
                    )
                }

                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .border(2.dp, Color.Gray, RoundedCornerShape(20.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberVectorPainter(image = Icons.Filled.Info),
                        contentDescription = "Call",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(30.dp)
                    )
                    Spacer(Modifier.width(20.dp))
                    Text("About Do")
                    Spacer(Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Arrow",
                        modifier = Modifier
                            .padding(end = 10.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // 4. Log Out Button
        Button(
            onClick = { /* To do */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = DarkSecondary)
        ) {
            Text("Log Out")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewMe() {
    Me()
}