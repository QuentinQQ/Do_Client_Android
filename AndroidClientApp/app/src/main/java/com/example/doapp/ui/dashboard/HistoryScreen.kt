package com.example.doapp.ui.dashboard

import android.app.ActionBar
import android.graphics.drawable.Icon
import android.graphics.drawable.PaintDrawable
import android.widget.CalendarView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
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
import com.example.doapp.R
import com.example.doapp.ui.theme.ButtonBlue
import com.example.doapp.ui.theme.CardWhite
import com.example.doapp.ui.theme.FontBlack
import com.example.doapp.ui.theme.FontGray
import com.example.doapp.ui.theme.LightBackground
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import io.github.boguszpawlowski.composecalendar.StaticWeekCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState


enum class HistoryTopNav(val index: Int) {
    Calendar(0),
    Statistics(1)
}

/**
 * function of switch screen between calendar and statistics screen through navigation bar
 */
@Composable
fun History(
    navController: NavHostController
) {
    // Default to showing the "History" view
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(HistoryTopNav.Calendar) }

    Column {
        TopNavBar(selectedTab, setSelectedTab)
        Column(
            modifier = Modifier
                .background(color = LightBackground)//MaterialTheme.colorScheme.background
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxSize()
        ) {
            when (selectedTab) {
                HistoryTopNav.Calendar -> CalendarView()
                HistoryTopNav.Statistics -> StatisticsView()
            }
        }
    }
}


@Composable
fun TopNavBar(selectedTab: HistoryTopNav, setSelectedTab: (HistoryTopNav) -> Unit) {
    val indicatorHeight = 4.dp
    val indicatorColor = ButtonBlue

    TabRow(
        selectedTabIndex = selectedTab.index,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                color = indicatorColor,
                height = indicatorHeight,
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTab.index])
//                    .align(Alignment.BottomCenter)
            )
        },
        modifier = Modifier
            .background(LightBackground)
            .border(1.dp, Color.Transparent),
        containerColor = LightBackground
        ) {
        Tab(
            text = { Text("Calendar") },
            selected = selectedTab == HistoryTopNav.Calendar,
            onClick = { setSelectedTab(HistoryTopNav.Calendar) },
            selectedContentColor = FontBlack,
            unselectedContentColor = FontGray
        )
        Tab(
            text = { Text("Statistics") },
            selected = selectedTab == HistoryTopNav.Statistics,
            onClick = { setSelectedTab(HistoryTopNav.Statistics) },
            selectedContentColor = FontBlack,
            unselectedContentColor = FontGray,
        )
    }
}

/**
 * Layout of Calendar screen, sub screen of History Screen
 */
@Composable
fun CalendarView() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        SelectableCalendar(
            dayContent = { dayState ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(16.dp),
                    Arrangement.Center
                ) {
                    Text(
                        text = dayState.date.dayOfMonth.toString(),
                        textAlign = TextAlign.Center
                    )
                    Text("")
                }
            }
        )
    }
}

/**
 *
 */
//@Composable
//fun MyDay(dayState: DayState<*>) {
//    Text(dayState.date.dayOfMonth.toString())
//}

/**
 * Layout of Statistics screen, sub screen of History Screen
 */
@Composable
fun StatisticsView() {
    // default to show the "Statistics" view
    var selectedTab by remember { mutableStateOf(0) }

    Column (
        Modifier.padding(start = 8.dp, end = 8.dp)
    ){
        // Sub navigation bar, including "Week", "Month", "Year"
        Column (
            modifier = Modifier
                .padding(vertical = 8.dp)
        ){
            TabRow(
                selectedTabIndex = selectedTab,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        FontGray,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(5.dp, FontGray),
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
                        .then(if (selectedTab == 0) Modifier.clip(RoundedCornerShape(20.dp)) else Modifier)
                        .background(if (selectedTab == 0) Color.White else Color.Gray)
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
                        .then(if (selectedTab == 1) Modifier.clip(RoundedCornerShape(20.dp)) else Modifier)
                        .background(if (selectedTab == 1) Color.White else Color.Gray)
                        .height(40.dp)
                        .fillMaxWidth()
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
                        .then(if (selectedTab == 2) Modifier.clip(RoundedCornerShape(20.dp)) else Modifier)
                        .background(if (selectedTab == 2) Color.White else Color.Gray)
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
                            Image(
                                painter = painterResource(id = R.drawable.weekly_record_lastweek_bule),
                                contentDescription = "Statistics weekly record"
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            Divider()
                            Spacer(modifier = Modifier.height(8.dp))

                            Image(
                                painter = painterResource(id = R.drawable.weekly_record_thisweek_bule),
                                contentDescription = "Statistics weekly record"
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Describe of weekly workout duration analysis
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Box(modifier = Modifier.weight(0.5f)) {
                                    Text(text = buildAnnotatedString {
                                        append("This week's total workout time is ")
                                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF3757FF))) {
                                            append("0")
                                        }
                                        append(" minutes.\nThe number of workouts compared to the previous period:")
                                    })
                                }
                                Text(text = "-33 mins  \uD83D\uDE2B",
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
                            Image(
                                painter = painterResource(id = R.drawable.weekly_record_lastweek_bule),
                                contentDescription = "Statistics weekly record"
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            Divider()
                            Spacer(modifier = Modifier.height(8.dp))

                            Image(
                                painter = painterResource(id = R.drawable.weekly_record_thisweek_bule),
                                contentDescription = "Statistics weekly record"
                            )
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


//@Preview(showBackground = true)
//@Composable
//fun PreviewHistory() {
//    History()
//}

@Preview(showBackground = true)
@Composable
fun PreviewHistory() {
    StatisticsView()
}