package com.example.doapp.ui.dashboard.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.doapp.R
import com.example.doapp.ui.dashboard.New
import com.example.doapp.ui.theme.CardWhite
import com.example.doapp.ui.theme.LightBackground
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import java.time.LocalDate

/**
 * Layout of Statistics screen, sub screen of History Screen
 */
@Composable
fun StatisticsView(navController: NavHostController) {
    // default to show the "Statistics" view
    var selectedTab by remember { mutableStateOf(0)
    }
    var lastWeekWorkouts = mapOf(
        LocalDate.now() to 60,
        LocalDate.now().minusDays(1) to 90,
        LocalDate.now().minusDays(2) to 45,
        LocalDate.now().minusDays(3) to 70,
        LocalDate.now().minusDays(4) to 80,
        LocalDate.now().minusDays(5) to 55,
        LocalDate.now().minusDays(6) to 100
    )
    var thisWeekWorkouts = mapOf(
        LocalDate.now() to 90,
        LocalDate.now().minusDays(1) to 120,
        LocalDate.now().minusDays(2) to 75,
        LocalDate.now().minusDays(3) to 110,
        LocalDate.now().minusDays(4) to 85,
        LocalDate.now().minusDays(5) to 60,
        LocalDate.now().minusDays(6) to 130
    )
    var diffMinutes = 30

    Column (
        Modifier.padding(start = 8.dp, end = 8.dp)
    ){
        // Sub navigation bar, including "Week", "Month", "Year"
        Column (
            modifier = Modifier.padding(vertical = 8.dp)
        ){
            TabRow(
                selectedTabIndex = selectedTab,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        CardWhite,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(5.dp, CardWhite),
//                colors = CardDefaults.cardColors(
//                    containerColor = CardWhite,
//                ),
                indicator = {}
            ) {
                // Week Tab
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
//                        .then(if (selectedTab == 0) Modifier.clip(RoundedCornerShape(20.dp)) else Modifier)
                        .background(if (selectedTab == 0) Color.Gray else CardWhite)
                ) {
                    Text(
                        "Week",
                        color = Color.Black
                    )
                }
                // Month Tab
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
//                        .then(if (selectedTab == 1) Modifier.clip(RoundedCornerShape(20.dp)) else Modifier)
                        .background(if (selectedTab == 1) Color.Gray else CardWhite)
                ) {
                    Text(
                        "Month",
                        color = Color.Black
                    )
                }
                // Year Tab
                Tab(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    modifier = Modifier
//                        .then(if (selectedTab == 2) Modifier.clip(RoundedCornerShape(20.dp)) else Modifier)
                        .background(if (selectedTab == 2) Color.Gray else CardWhite)
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        "Year",
                        color = Color.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // The specific page of the  selected tab in sub navigation bar
        LazyColumn (
            modifier = Modifier
                .background(LightBackground)
                .padding(start = 8.dp, end = 8.dp)
        ){
            when (selectedTab) {
                // Week
                0 -> item {
                    // Week choosing element
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Arrow",
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .size(40.dp)
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = "March 25 to March 31, 2024",
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.CenterVertically),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Arrow",
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .size(40.dp)
                        )

                    }
                    // Weekly workout duration part
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = CardWhite,
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Workout Duration",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            BarChart(
                                lastWeekWorkouts = lastWeekWorkouts,
                                thisWeekWorkouts = thisWeekWorkouts,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            Divider()
                            Spacer(modifier = Modifier.height(8.dp))


                            Spacer(modifier = Modifier.height(16.dp))

                            // Describe of weekly workout duration analysis
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Box(modifier = Modifier.weight(0.5f)) {
                                    Text(
                                        text = buildAnnotatedString {
                                            append("This week's total workout time is ")
                                            withStyle(
                                                style = SpanStyle(
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 20.sp,
                                                    color = Color(0xFF3757FF)
                                                )
                                            ) {
                                                append("295")
                                            }
                                            append(" minutes. \nCompared to the last week:")
                                        }
                                    )
                                }
                                Text(text = "-65 mins  \uD83D\uDE2B",
                                    modifier = Modifier
                                        .weight(0.5f)
                                        .align(Alignment.CenterVertically),
                                    textAlign = TextAlign.Right,
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        color = Color(0xFF3757FF)
                                    )
                                )
                            }
                        }
                    }

                    // Capacity Curve part
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = CardWhite,
                        ),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Capacity Curve",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Image(
                                painter = painterResource(id = R.drawable.capacity_curve),
                                contentDescription = "Statistics weekly record"
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }

                }
                // Month
                1 -> item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(text = "Example Month")
                    }
                }
                // Year
                2 -> item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(text = "Example Year")
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun StatisticViewPreview() {
    val navController = rememberNavController()
    StatisticsView(navController)
}

@Composable
private fun BarChart(
    lastWeekWorkouts: Map<LocalDate, Int>,
    thisWeekWorkouts: Map<LocalDate, Int>,
    modifier: Modifier = Modifier
) {
    val days = lastWeekWorkouts.keys.toList()
    val maxValue = (lastWeekWorkouts.values.maxOrNull() ?: 0).coerceAtLeast(thisWeekWorkouts.values.maxOrNull() ?: 0)

    Canvas(modifier = modifier.fillMaxWidth()) {
//        val barWidth = size.width / (days.size * 2f)
        val barWidth = 20.dp.toPx()
        val barMaxHeight = size.height
        val barPadding = barWidth / 2

        days.forEachIndexed { index, day ->
            val lastWeekValue = lastWeekWorkouts[day] ?: 0
            val thisWeekValue = thisWeekWorkouts[day] ?: 0

            val lastWeekBarHeight = (lastWeekValue * barMaxHeight) / maxValue
            val thisWeekBarHeight = (thisWeekValue * barMaxHeight) / maxValue

            val startX = index * 2 * barWidth + barPadding

            drawRoundRect(
                color = Color.Blue,
                topLeft = Offset(startX, size.height - lastWeekBarHeight),
                size = Size(barWidth, lastWeekBarHeight),
                cornerRadius = CornerRadius(4.dp.toPx())
            )

            drawRoundRect(
                color = Color.Red,
                topLeft = Offset(startX + barWidth, size.height - thisWeekBarHeight),
                size = Size(barWidth, thisWeekBarHeight),
                cornerRadius = CornerRadius(4.dp.toPx())
            )
        }
    }
}
