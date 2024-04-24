package com.example.doapp.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.doapp.R
import com.example.doapp.ui.theme.FontBlack
import com.example.doapp.ui.theme.FontGray
import com.example.doapp.ui.theme.LightBackground

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Course(
    navController: NavHostController,
    showNewPageOverlay: MutableState<Boolean>,
)
{
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Official Plan", "Customization")
    var selectedTag by remember { mutableStateOf(0) }
    val tags = listOf(
        "Reduce fat and shape",
        "Increase muscle for male",
        "Increase muscle for female",
        "Shape",
        "Relax",
        "Other")
    val tagPlans = mapOf(
        0 to listOf(
            Pair("Shortcut for male fat loss and shaping", R.drawable.reverse_pyramid),
            Pair("Shortcut for female fat loss and shaping",R.drawable.upper_lower_body_split)
        ),
        1 to listOf(
            Pair("Reverse pyramid", R.drawable.reverse_pyramid),
            Pair("Upper-lower body split", R.drawable.upper_lower_body_split),
            Pair("Basic full-body workout for male", R.drawable.basic_full_body_workout_for_male),
            Pair("High-frequency 5-day split", R.drawable.high_frequency_5_day_split),
            Pair("Regular three-phase differentiation", R.drawable.regular_three_phase_differentiation),
            Pair("6 exercises for legs push-pull", R.drawable.six_exercises_for_legs_push_pull),
            Pair("Advanced 5-day split", R.drawable.advanced_5_day_split),
            Pair("Advanced four-phase differentiation", R.drawable.advanced_four_phase_differentiation)

        ),
        2 to listOf(
            Pair("One workout one rest", R.drawable.client_logo),
            Pair("Basic full-body workout for female", R.drawable.client_logo),
            Pair("6 exercises for legs push-pull", R.drawable.client_logo),
            Pair("High-frequency 5-day split for female", R.drawable.client_logo),
            Pair("Hourglass figure split", R.drawable.client_logo)
        ),
        3 to listOf(
            Pair("One workout one rest", R.drawable.client_logo),
            Pair("Basic full-body workout for female", R.drawable.client_logo),
            Pair("6 exercises for legs push-pull", R.drawable.client_logo),
            Pair("High-frequency 5-day split for female", R.drawable.client_logo),
            Pair("Hourglass figure split", R.drawable.client_logo)
        ),
        4 to listOf(
            Pair("One workout one rest", R.drawable.client_logo),
            Pair("Basic full-body workout for female", R.drawable.client_logo),
            Pair("6 exercises for legs push-pull", R.drawable.client_logo),
            Pair("High-frequency 5-day split for female", R.drawable.client_logo),
            Pair("Hourglass figure split", R.drawable.client_logo)
        ),
        5 to listOf(
            Pair("One workout one rest", R.drawable.client_logo),
            Pair("Basic full-body workout for female", R.drawable.client_logo),
            Pair("6 exercises for legs push-pull", R.drawable.client_logo),
            Pair("High-frequency 5-day split for female", R.drawable.client_logo),
            Pair("Hourglass figure split", R.drawable.client_logo)
        ),
    )
    val selectedPlans = tagPlans[selectedTag] ?: listOf()
    val column1Plans = selectedPlans.filterIndexed { index, _ -> index % 2 == 0 }
    val column2Plans = selectedPlans.filterIndexed { index, _ -> index % 2 != 0 }
    val indicatorHeight = 4.dp
    val indicatorColor = FontBlack

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightBackground)
    ) {
        Row(
            modifier = Modifier
                .background(LightBackground)
                .padding(4.dp),
            horizontalArrangement = Arrangement.Start
        ){
            // Tabs for official plans and personal templates
            TabRow(
                selectedTabIndex = selectedTab,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        color = indicatorColor,
                        height = indicatorHeight,
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedTab])
//                    .align(Alignment.BottomCenter)
                    )
                },
                modifier = Modifier
                    .background(LightBackground)
                    .padding(4.dp)
                    .width(280.dp),
                containerColor = LightBackground
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = {
                            Text(
                                title,
                                style = TextStyle(fontSize = if (selectedTab == index) 16.sp else 12.sp)
                            )
                        },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        selectedContentColor = FontBlack,
                        unselectedContentColor = FontGray,
                    )
                }
            }
//            Spacer(modifier = Modifier.(1f))
        }

        // Tags for filtering plans and courses
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            tags.forEachIndexed { index, tag ->
                tags.forEachIndexed { index, tag ->
                    Chip(
                        text = tag,
                        selected = selectedTag == index,
                        onSelected = { selectedTag = index })
                }
            }
        }


        // Plans and courses list
        Row(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f).padding(end = 4.dp)) {
                items(column1Plans) { plan ->
                    PlanItem(title = plan.first, imageRes = plan.second)
                }
            }

            LazyColumn(modifier = Modifier.weight(1f).padding(start = 4.dp)) {
                items(column2Plans) { plan ->
                    PlanItem(title = plan.first, imageRes = plan.second)
                }
            }
        }
    }
    //  When clicked on "New" button in CourseScreen
    if (showNewPageOverlay.value) {
//        Box(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            // Cover a scrim
//            Box(modifier = Modifier.fillMaxSize().background(Color.Gray.copy(0.5f)))
//
//            // add content in this scrim
//            New(navController)
//        }
        Box(modifier = Modifier.fillMaxSize()) {
            // 覆盖一个遮罩层
            Box(modifier = Modifier.fillMaxSize().background(Color.Gray.copy(0.5f)))

            // 在这个遮罩层中添加内容
            New(navController, onDismiss = { showNewPageOverlay.value = false }) // 提供 onDismiss 参数
        }
    }
}

@Composable
fun Chip(text: String, selected: Boolean, onSelected: () -> Unit) {
    val backgroundColor = if (selected) Color.Black else FontGray
    val textColor = if (selected) Color.White else FontBlack

    Box(
        modifier = Modifier
            .clickable { onSelected() }
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = backgroundColor)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor
        )
    }
}


@Composable
fun PlanItem(title: String, imageRes: Int, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
//            .aspectRatio(1f)
            .clickable {
                // Handle plan item click here
            },
//        colors = CardDefaults.cardColors(containerColor = CardWhite),
//        shape = RoundedCornerShape(50.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.DarkGray)
//                .padding(16.dp)
        ) {
            // Place image as background of card
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillWidth

            )
            Text(
                text = title,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewCourse(){
    val navController = rememberNavController()
    val showOverlay = remember { mutableStateOf(false) }
    Course(navController, showOverlay)
}