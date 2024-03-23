package com.example.doapp.ui.dashboard

import android.app.ActionBar
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
//    navController: NavHostController
) {
    // Default to showing the "History" view
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(HistoryTopNav.Calendar) }

    Column {
        TopNavBar(selectedTab, setSelectedTab)
        when (selectedTab) {
            HistoryTopNav.Calendar -> CalendarView()
            HistoryTopNav.Statistics -> StatisticsView()
        }
    }
}


@Composable
fun TopNavBar(selectedTab: HistoryTopNav, setSelectedTab: (HistoryTopNav) -> Unit) {
    TabRow(selectedTabIndex = selectedTab.index) {
        Tab(
            text = { Text("Calendar") },
            selected = selectedTab == HistoryTopNav.Calendar,
            onClick = { setSelectedTab(HistoryTopNav.Calendar) }
        )
        Tab(
            text = { Text("Statistics") },
            selected = selectedTab == HistoryTopNav.Statistics,
            onClick = { setSelectedTab(HistoryTopNav.Statistics) }
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

    // Sub navigation bar, including "Week", "Month", "Year"
    Column {
        TabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(20.dp)
                    )
                .height(32.dp)
        ) {
            // Week Tab
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                modifier = Modifier
//                    .clip(RoundedCornerShape(20.dp))
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
                    .background(if (selectedTab == 1) Color.White else Color.Gray)
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
                    .background(if (selectedTab == 2) Color.White else Color.Gray)
            ) {
                Text(
                    "Year",
                    color = Color.Black
                )
            }
        }
    }

    // The specific page of the  selected tab in sub navigation bar
    LazyColumn {
        when (selectedTab) {
            // Week
            0 -> item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                }
            }
            // Month
            1 -> item { /* Month view */ }
            // Year
            2 -> item { /* Year view */ }
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