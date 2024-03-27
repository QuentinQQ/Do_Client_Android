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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.doapp.R
import com.example.doapp.ui.theme.FontBlack
import com.example.doapp.ui.theme.FontGray
import com.example.doapp.ui.theme.LightBackground

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Course(
    navController: NavHostController
)
{
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Official Plan", "Customization")
    var selectedTag by remember { mutableStateOf(0) }
    val tags = listOf("Reduce fat and shape", "Increase muscle for male", "Increase muscle for female", "Shape", "Relax", "Other")
    val tagPlans = mapOf(
        0 to listOf(
            Pair("Shortcut for male fat loss and shaping", R.drawable.reverse_pyramid),
            Pair("Shortcut for female fat loss and shaping",R.drawable.weekly_record_thisweek_bule)
        ),
        1 to listOf(
            Pair("Reverse pyramid", R.drawable.reverse_pyramid),
            Pair("Basic full-body workout for male", R.drawable.basic_full_body_workout_for_male),
//            Pair("6 exercises for legs push-pull", R.drawable.six_exercises_for_legs_push_pull),
//            Pair("Regular three-phase differentiation", R.drawable.regular_three_phase_differentiation),
//            Pair("High-frequency 5-day split", R.drawable.high_frequency_5_day_split),
//            Pair("Upper-lower body split", R.drawable.upper_lower_body_split),
//            Pair("Advanced 5-day split", R.drawable.advanced_5_day_split),
//            Pair("Advanced four-phase differentiation", R.drawable.advanced_four_phase_differentiation)
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
        LazyColumn(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            val selectedPlans = tagPlans[selectedTag] ?: listOf()
            val rows = (selectedPlans.size + 1) / 2
            items(rows) {rowIndex ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // The first card of each row
                    PlanItem(
                        title = selectedPlans[rowIndex * 2].first,
                        imageRes = selectedPlans[rowIndex * 2].second,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(8.dp))
                    // Check does the second card exist in this row
                    if (rowIndex * 2 + 1 < selectedPlans.size) {
                        PlanItem(
                            title = selectedPlans[rowIndex * 2 + 1].first,
                            imageRes = selectedPlans[rowIndex * 2 + 1].second,
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
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
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
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
            // Place the text description on the card
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewCourse(){
//    Course()
//}