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
import androidx.compose.runtime.MutableState
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
import androidx.navigation.compose.rememberNavController
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
import com.example.doapp.ui.dashboard.history.CalendarView
import com.example.doapp.ui.dashboard.history.StatisticsView


enum class HistoryTopNav(val index: Int) {
    Calendar(0),
    Statistics(1)
}

/**
 * function of switch screen between calendar and statistics screen through navigation bar
 */
@Composable
fun History(
    navController: NavHostController,
    showNewPageOverlay: MutableState<Boolean>,
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
    //  When clicked on "New" button in HistoryScreen
    if (showNewPageOverlay.value) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Cover a scrim
            Box(modifier = Modifier.fillMaxSize().background(Color.Gray.copy(0.5f)))

            // add content in this scrim
            // 在这个遮罩层中添加内容
            New(navController, onDismiss = { showNewPageOverlay.value = false }) // 提供 onDismiss 参数
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

@Preview(showBackground = true)
@Composable
fun PreviewHistory() {
    val navController = rememberNavController()
    val showOverlay = remember { mutableStateOf(false) }
    History(navController, showOverlay)
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewHistory() {
//    StatisticsView()
//}