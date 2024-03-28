package com.example.doapp.ui.dashboard.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.SelectableCalendar

/**
 * Layout of Calendar screen, sub screen of History Screen
 */
@Composable
fun CalendarView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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