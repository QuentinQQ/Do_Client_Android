package com.example.doapp.ui.dashboard.myprofile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.focusProperties

/**
 * sub screen of Me screen, when click on the personal info button , it will be switch to this screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfoScreen() {

    val gender = listOf("Male", "Female")
    var isExpanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf(gender[0]) }

    Column (
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ){
            TopAppBar(
                title = { Text("Personal Info") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back press */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = it }
            ) {
                TextField(
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .focusProperties {
                            canFocus = false
                        }
                        .padding(bottom = 8.dp),
                    value = selectedGender,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Gender")},
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    }
                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }
                ) {
                    gender.forEach { label ->
                        DropdownMenuItem(
                            text = { Text(label) },
                            onClick = {
                                selectedGender = label
                                isExpanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPersonalInfoScreen() {
    PersonalInfoScreen()
}