package com.example.doapp.ui.dashboard.HomeSubScreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.doapp.ui.theme.ButtonBlue
import com.example.doapp.ui.theme.LightBackground
import com.example.doapp.ui.theme.FontBlack
import com.example.doapp.ui.theme.FontWhite
import com.example.doapp.ui.theme.FontGray
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.map
import com.example.doapp.dataProcess.getUserId
import com.example.doapp.db.ViewModel
import com.example.doapp.ui.theme.CardWhite
import java.time.LocalDate
import kotlinx.coroutines.flow.collect


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavHostController,
    viewModel: ViewModel,
) {

    val context = LocalContext.current
    val currentDate = LocalDate.now().toString()
    val uid = getUserId(context) ?: ""

//    val trainingItems: List<TrainingItemData> = remember {
//        if (uid.isNotEmpty()) {
//            viewModel.getUserRecordByUidAndDate(uid, currentDate) // 确保使用正确的日期变量
//                .filter { !it.isCompleted }
//                .map { record ->
//                    TrainingItemData(
//                        title = record.movementName,
//                        sets = record.sets,
//                        weight = record.weight,
//                        duration = record.time
//                    )
//                }
//        } else {
//            emptyList()
//        }
//    }

//    val trainingItems = produceState(initialValue = emptyList<TrainingItemData>(), uid, currentDate) {
//        if (uid.isNotEmpty()) {
//            value = viewModel.getUserRecordByUidAndDate(uid, currentDate)
//                .filter { !it.isCompleted }
//                .map { record ->
//                    TrainingItemData(
//                        title = record.movementName,
//                        sets = record.sets,
//                        weight = record.weight,
//                        duration = record.time
//                    )
//                }
//        }
//    }.value
    val trainingItemsState = remember { mutableStateOf(listOf<TrainingItemData>()) }

    LaunchedEffect(key1 = uid, key2 = currentDate) {
        try {
            viewModel.getUserRecordByUidAndDate(uid, currentDate).collect { recordsList ->
                val filteredRecords = mutableListOf<TrainingItemData>()
                for (record in recordsList) { // 确保recordsList是一个列表
                    if (!record.isCompleted) {
                        filteredRecords.add(
                            TrainingItemData(
                                title = record.movementName,
                                sets = record.sets,
                                weight = record.weight,
                                duration = record.time
                            )
                        )
                    }
                }
                trainingItemsState.value = filteredRecords
            }
        } catch (e: Exception) {
            // Handle exceptions or log them
        }
    }






//
//    // 从数据库或其他数据源获取训练项目数据
//    val trainingItems = remember {
//        listOf(
//            TrainingItemData("Chest • Arm", 3, 20.0, 15),
//            TrainingItemData("Abdomen", 3, 15.0, 15),
//            TrainingItemData("Lower Body Workout", 3, 25.0, 15),
//            TrainingItemData("Targeted Weakness Training", 3, 10.0, 15)
//        )
//    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Today's Training", color = FontBlack) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = LightBackground),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = FontBlack)
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
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite, contentColor = FontBlack)
            ) {
                Column(Modifier.padding(16.dp)) {
                    trainingItemsState.value.forEachIndexed { index, item ->
                        val modifier = if (index < trainingItemsState.value.size - 1) {
                            Modifier.padding(bottom = 8.dp)
                        } else {
                            Modifier
                        }
                        TrainingItem(item, modifier)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { /* Handle complete button click */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue, contentColor = FontWhite)
            ) {
                Text("Complete")
            }
        }
    }
}

data class TrainingItemData(
    val title: String,
    val sets: Int,
    val weight: Double,
    val duration: Int
)

@Composable
fun TrainingItem(item: TrainingItemData, modifier: Modifier = Modifier) {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = "Sets ${item.sets} ${item.weight}kg ${item.duration}min",
                style = MaterialTheme.typography.bodySmall,
                color = FontGray
            )
        }
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewDetailsScreen() {
//    val navController = rememberNavController()
//    DetailsScreen(navController = navController)
//}

//package com.example.doapp.ui.dashboard.home
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import com.example.doapp.ui.theme.ButtonBlue
//import com.example.doapp.ui.theme.LightBackground
//import com.example.doapp.ui.theme.FontBlack
//
//@Composable
//fun DetailsScreen(navController: NavHostController) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Today's Training",
//                    color = FontBlack) },
//                backgroundColor = LightBackground,
//                actions = {
//                    TextButton(onClick = { navController.popBackStack() } ,
//                        colors = ButtonDefaults.textButtonColors(contentColor = FontBlack))
//                    {
//                        Text("Cancel")
//                    }
//                }
//            )
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//        ) {
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                elevation = 4.dp
//            ) {
//                Column(Modifier.padding(16.dp)) {
//                    TrainingItem("Chest • Arm", "Sets 3 180 Kcal 15min", Modifier.padding(bottom = 8.dp))
//                    TrainingItem("Abdomen", "Sets 3 180 Kcal 15min", Modifier.padding(bottom = 8.dp))
//                    TrainingItem("Lower Body Workout", "Sets 3 180 Kcal 15min", Modifier.padding(bottom = 8.dp))
//                    TrainingItem("Targeted Weakness Training", "Sets 3 180 Kcal 15min")
//                }
//            }
//
//            Spacer(modifier = Modifier.weight(1f))
//
//            Button(
//                onClick = { /* Handle complete button click */ },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                colors = ButtonDefaults.buttonColors(backgroundColor = ButtonBlue, contentColor = Color.White)
//            ) {
//                Text("Complete")
//            }
//        }
//    }
//}
//
//@Composable
//fun TrainingItem(title: String, details: String, modifier: Modifier = Modifier) {
//    Row(
//        modifier = modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Column(Modifier.weight(1f)) {
//            Text(
//                text = title,
//                style = MaterialTheme.typography.subtitle1
//            )
//            Text(
//                text = details,
//                style = MaterialTheme.typography.body2,
//                color = Color.Gray
//            )
//        }
//
//        TextButton(onClick = { /* Handle GO! button click */ }) {
//            Text("Done",
//                color = FontBlack)
//        }
//    }
//}
//
////@Preview(showBackground = true)
////@Composable
////fun PreviewDetailsScreen() {
////    DetailsScreen()
////}