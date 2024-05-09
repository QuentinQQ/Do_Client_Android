package com.example.doapp.ui.dashboard.course

import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.doapp.db.exercise.official_course.OfficialCourse
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.doapp.db.exercise.official_course.EachActionDetail
import com.example.doapp.db.exercise.official_course.OfficialCourseSchedule
import com.example.doapp.ui.dashboard.home.TrainingItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

@Composable
fun CourseDetailsScreen(
    navController: NavHostController,
    viewModel: ViewModel,
    courseTitle: String
){
//    var courseDetailsState by remember { mutableStateOf<OfficialCourse?>(null) }
//    var courseSchedule by remember { mutableStateOf<List<OfficialCourseSchedule>?>(null) }
//
//    // LaunchedEffect观察课程标题的变化
//    LaunchedEffect(key1 = courseTitle) {
//        // 在协程中调用suspend函数获取课程详情
//        courseDetailsState = viewModel.getOfficialCourseByName(courseTitle)
//    }
//
//    // 当课程详情状态更新时，获取相关的课程日程
//    LaunchedEffect(key1 = courseDetailsState) {
//        courseDetailsState?.courseId?.let { courseId ->
//            // 在协程中调用suspend函数获取课程日程
//            courseSchedule = viewModel.getOfficialCourseScheduleByCourseId(courseId)
//        }
//    }
//    var courseDetailsState by remember { mutableStateOf<OfficialCourse?>(null) }
//    var courseSchedule by remember { mutableStateOf<List<OfficialCourseSchedule>?>(null) }
//
//    // 使用LaunchedEffect来观察和更新课程详细信息
//    LaunchedEffect(key1 = courseTitle) {
//        viewModel.getOfficialCourseByName(courseTitle).collect { course ->
//            courseDetailsState = course
//        }
//    }
//
//    // 使用LaunchedEffect来加载和观察课程日程
//    LaunchedEffect(key1 = courseDetailsState) {
//        courseDetailsState?.courseId?.let { courseId ->
//            viewModel.getOfficialCourseScheduleByCourseId(courseId).collect { schedules ->
//                courseSchedule = schedules
//            }
//        }
//    }
    //第一种
//    val officialCourseLiveData = viewModel.getOfficialCourseByName(courseTitle).observeAsState()
//    val courseDetailsState = officialCourseLiveData.value
//
//    val courseSchedule = courseDetailsState?.let { officialCourse ->
//        viewModel.getOfficialCourseScheduleByCourseId(officialCourse.courseId).observeAsState().value
//    }

    var courseDetailsState by remember { mutableStateOf<OfficialCourse?>(null) }
    var courseSchedule by remember { mutableStateOf<List<OfficialCourseSchedule>?>(null) }

    LaunchedEffect(key1 = courseTitle) {
        // --------------------test----------------------
        println("courseTitle: $courseTitle") // 打印传递进来的 courseTitle
        Log.d("CourseDetailsScreen", "courseTitle: $courseTitle") // 在 Logcat 中打印 courseTitle
        // --------------------test----------------------
        val officialCourseLiveData = viewModel.getOfficialCourseByName(courseTitle)
        officialCourseLiveData.observeForever { officialCourse ->
            courseDetailsState = officialCourse
            // --------------------test-----------------------
            println("courseDetailsState: $courseDetailsState")
            Log.d("CourseDetailsScreen", "courseDetailsState: $courseDetailsState")
            // --------------------test-----------------------
        }
    }

    LaunchedEffect(key1 = courseDetailsState) {
        courseDetailsState?.let { officialCourse ->
            val scheduleList = withContext(Dispatchers.IO) {
                viewModel.getOfficialCourseScheduleByCourseId(officialCourse.courseId).value
            } //确定了officialCourse.courseId不为空, courseSchedule即scheduleList应该是为null的
            // --------------------test-----------------------
            println("scheduleList: $scheduleList")
            Log.d("scheduleList", "scheduleList: $scheduleList")
            println("officialCourse: ${officialCourse.courseId}")
            Log.d("officialCourse", "officialCourse: ${officialCourse.courseId}")
            // --------------------test-----------------------
            courseSchedule = scheduleList
            // --------------------test-----------------------
            println("courseSchedule: $courseSchedule")
            Log.d("courseSchedule", "courseSchedule: $courseSchedule")
            // --------------------test-----------------------
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightBackground)
            .padding(16.dp)
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
            courseDetailsState?.let {
                Text(
                    text = it.courseName,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(start = 8.dp),
                    color = FontBlack
                )
                Text(
                    text = it.courseDesc,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 8.dp),
                    color = FontBlack
                )
            }
        }

        LazyColumn {
            courseSchedule?.let { schedules ->
                val sortedSchedules = schedules.sortedBy { it.dayNum }
                // --------------------test----------------------
                println("Sorted Schedules: $sortedSchedules")
                Log.d("Sorted Schedules", "Sorted Schedules: $sortedSchedules")
                // --------------------test----------------------
                items(sortedSchedules.size) { index ->
                    val schedule = sortedSchedules[index]
                    CourseScheduleCard(schedule = schedule, viewModel = viewModel)
                }
            }
        }

        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF3757FF),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Add to My Plan",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}


@Composable
fun CourseScheduleCard(
    schedule: OfficialCourseSchedule,
    viewModel: ViewModel
) {
    var actionDetails by remember { mutableStateOf<List<EachActionDetail>>(emptyList()) }

    LaunchedEffect(schedule.scheduleId) {
        val liveData = viewModel.getActionDetailsByScheduleId(schedule.scheduleId)
        actionDetails = liveData.value ?: emptyList()
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Day ${schedule.dayNum}",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = schedule.scheduleDesc,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            actionDetails.forEach { detail ->
                ActionDetailRow(detail)
            }
        }
    }
}

@Composable
fun ActionDetailRow(detail: EachActionDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(detail.movementName)
        Text("${detail.sets} sets | ${detail.weight} kg")
    }
}
//@Composable
//fun TrainingItemRow(trainingItem: TrainingItem) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp),
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Text(trainingItem.content)
//        Text("${trainingItem.duration} | ${trainingItem.calories}")
//    }
//}


//data class TrainingItem(
//    val content: String,
//    val duration: String,
//    val calories: String
//)
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewCourseDetailsScreen() {
//    DoAppTheme {
//        CourseDetailsScreen()
//    }
//}