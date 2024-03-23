package com.example.doapp.ui.dashboard

import android.app.ActionBar
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
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


@Composable
fun History(
//    navController: NavHostController
) {
    // Default to showing the "History" view
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(HistoryTopNav.Calendar) }

    Column {
        TopTabs(selectedTab, setSelectedTab)
        when (selectedTab) {
            HistoryTopNav.Calendar -> CalendarView()
            HistoryTopNav.Statistics -> StatisticsView()
        }
    }
}


@Composable
fun TopTabs(selectedTab: HistoryTopNav, setSelectedTab: (HistoryTopNav) -> Unit) {
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

// Used to update the content of single day
@Composable
fun MyDay(dayState: DayState<*>) {
    Text(dayState.date.dayOfMonth.toString())
}


@Composable
fun StatisticsView() {

}


@Preview(showBackground = true)
@Composable
fun PreviewHistory() {
    History()
}