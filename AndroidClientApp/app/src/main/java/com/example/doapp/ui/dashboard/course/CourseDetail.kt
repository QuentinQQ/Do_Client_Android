package com.example.doapp.ui.dashboard.course

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.doapp.db.ViewModel
import com.example.doapp.ui.theme.DoAppTheme
import com.example.doapp.ui.theme.ButtonBlue
import com.example.doapp.ui.theme.FontBlack
import com.example.doapp.ui.theme.LightBackground

@Composable
fun CourseDetailsScreen(
    navController: NavHostController,
    viewModel: ViewModel,
    courseTitle: String
) {
    // 获取数据
//    val courseName = viewModel.getOfficialCourseByName(courseTitle)



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = LightBackground)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "BACK",
                modifier = Modifier
                    .size(48.dp) // Increased size
                    .padding(12.dp) // Adequate padding for clickable area
                    .clickable {
                    navController.popBackStack()
                    },
                tint = Color.Black
            )
            Text(
                text = "Course Details",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(start = 8.dp),
                color = FontBlack
            )
        }


//        LazyColumn {
//            items(courseDetails) { courseDetail ->
//                CourseCard(
//                    title = courseDetail.courseName,
//                    trainingList = courseDetail.schedule.map {
//                        TrainingItem(it.description, "${it.sets} sets", "${it.weight} kg")
//                    },
//                    description = courseDetail.courseDesc
//                )
//            }
//        }

        CourseCard(
            title = "Beginner Fitness",
            trainingList = listOf(
                TrainingItem("Warm-up", "5 minutes", "50 kcal"),
                TrainingItem("Jogging", "15 minutes", "150 kcal"),
                TrainingItem("Stretching", "5 minutes", "30 kcal")
            ),
            description = "This course is designed for beginners who want to start their fitness journey. It includes basic exercises and gradually increases intensity.",
        )
    }
}

@Composable
fun CourseCard(
    title: String,
    trainingList: List<TrainingItem>,
    description: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable {  },
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            trainingList.forEach { trainingItem ->
                TrainingItemRow(trainingItem)
            }

            Button(
                onClick = { /* Handle add to my training plan click */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = ButtonBlue, contentColor = Color.White)
            ) {
                Text("Add to My Training Plan")
            }

            Text(
                text = description,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun TrainingItemRow(trainingItem: TrainingItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(trainingItem.content)
        Text("${trainingItem.duration} | ${trainingItem.calories}")
    }
}

data class TrainingItem(
    val content: String,
    val duration: String,
    val calories: String
)
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewCourseDetailsScreen() {
//    DoAppTheme {
//        CourseDetailsScreen()
//    }
//}