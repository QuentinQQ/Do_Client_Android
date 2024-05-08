package com.example.doapp.ui.dashboard.new_subscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.time.LocalDate
import android.app.DatePickerDialog
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.doapp.ui.theme.ButtonBlue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun NewTrainScreen(navController: NavHostController) {
    var task by remember { mutableStateOf("") }
    var sets by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(48.dp)
                            .padding(12.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Add Workout",
                        style = MaterialTheme.typography.h6
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.surface
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "TIME：", style = MaterialTheme.typography.subtitle1,fontWeight = FontWeight.Bold)
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "From",fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
//                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "To", fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
//                    .align(Alignment.CenterVertically)
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = task,
            onValueChange = { task = it },
            label = { Text(text = "TASK:",fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = sets,
            onValueChange = { sets = it },
            label = { Text(text = "How many sets:",fontWeight = FontWeight.Bold) },
            trailingIcon = { Text("sets") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Save the workout details
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ButtonBlue,
                contentColor = Color.White
            ),
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NewTrainScreenPreview() {
    val navController = rememberNavController()
    NewTrainScreen(navController)
}