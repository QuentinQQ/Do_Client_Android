package com.example.doapp.ui.dashboard.HomeSubScreens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.doapp.ui.theme.ButtonBlue
import com.example.doapp.ui.theme.LightBackground
import com.example.doapp.ui.theme.FontBlack

@Composable
fun DetailsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Today's Training",
                    color = FontBlack) },
                backgroundColor = LightBackground,
                actions = {
                    TextButton(onClick = { navController.popBackStack() } ,
                        colors = ButtonDefaults.textButtonColors(contentColor = FontBlack))
                    {
                        Text("Cancel")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = 4.dp
            ) {
                Column(Modifier.padding(16.dp)) {
                    TrainingItem("Chest • Arm", "Sets 3 180 Kcal 15min", Modifier.padding(bottom = 8.dp))
                    TrainingItem("Abdomen", "Sets 3 180 Kcal 15min", Modifier.padding(bottom = 8.dp))
                    TrainingItem("Lower Body Workout", "Sets 3 180 Kcal 15min", Modifier.padding(bottom = 8.dp))
                    TrainingItem("Targeted Weakness Training", "Sets 3 180 Kcal 15min")
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { /* Handle complete button click */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = ButtonBlue, contentColor = Color.White)
            ) {
                Text("Complete")
            }
        }
    }
}

@Composable
fun TrainingItem(title: String, details: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = details,
                style = MaterialTheme.typography.body2,
                color = Color.Gray
            )
        }

        TextButton(onClick = { /* Handle GO! button click */ }) {
            Text("Done",
                color = FontBlack)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewDetailsScreen() {
//    DetailsScreen()
//}