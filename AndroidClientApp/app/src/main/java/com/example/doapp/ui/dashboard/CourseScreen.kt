package com.example.doapp.ui.dashboard

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
    val tabs = listOf("Official plan", "Personal template")
    var selectedTag by remember { mutableStateOf(0) }
    val tags = listOf("Reduce fat and shape", "Increase muscle", "Shape", "Relax", "Other")

    Column {
        // Tabs for official plans and personal templates
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTab == index,
                    onClick = { selectedTab = index }
                )
            }
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
        LazyColumn {
            items(count = 10) { // Replace this count with the actual number of plans
                PlanItem(title = "Plan title $it", description = "Plan description $it")
            }
        }

    }
}

@Composable
fun Chip(text: String, selected: Boolean, onSelected: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onSelected() }
            .padding(8.dp)
            .background(color = LightBackground)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = FontBlack
//            color = if (selected) FontBlack else FontGray,
//            TextUnit = if (selected) 18.dp else 12.dp
        )
    }
}


@Composable
fun PlanItem(title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Handle plan item click here
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = description)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewCourse(){
//    Course()
//}