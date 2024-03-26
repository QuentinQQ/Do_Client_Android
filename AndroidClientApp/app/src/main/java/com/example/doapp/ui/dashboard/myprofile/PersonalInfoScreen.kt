package com.example.doapp.ui.dashboard.myprofile

import android.content.ClipData.Item
import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.doapp.ui.theme.ButtonBlue
import com.example.doapp.ui.theme.FontWhite
import com.example.doapp.ui.theme.LightBackground
import com.example.doapp.ui.theme.LightBackgroundDarkGray
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale

/**
 * sub screen of Me screen, when click on the personal info button , it will be switch to this screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfoScreen() {

    val gender = listOf("Male", "Female")
    var isExpanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf(gender[0]) }

    val calendar = Calendar.getInstance()
    calendar.set(2024, 0, 1)
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )
    var showDatePicker by remember {
        mutableStateOf(false)
    }
    var selectedDate by remember {
        mutableStateOf(calendar.timeInMillis)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightBackground)
    ) {
        // Top bar and back buttom
        TopAppBar(
            title = { Text("Personal Information") },
            navigationIcon = {
                IconButton(onClick = { /* Handle back press */ }) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Back")
                }
            }
        )
        // Hint
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightBackgroundDarkGray)
                .padding(
                    start = 16.dp,
                    top = 8.dp,
                    end = 16.dp,
                    bottom = 8.dp
                )
        ) {
            Text(
                text = "The following information is used to obtain accurate recommendations, calculate exercise expenditure",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left
            )
        }

        // form info
        LazyColumn (
            modifier = Modifier
                .weight(1f)
                .background(color = LightBackground)
                .padding(0.dp)
        ){
            item {
                Column(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                ) {

                    // Gender
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 0.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Gender",
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .background(Color.White),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        ExposedDropdownMenuBox(
                            expanded = isExpanded,
                            onExpandedChange = { isExpanded = it }
                        ) {
                            TextField(
                                modifier = Modifier
                                    .menuAnchor()
                                    .focusProperties {
                                        canFocus = false
                                    }
                                    .padding(bottom = 8.dp),
                                textStyle = TextStyle(textAlign = TextAlign.End),
                                value = selectedGender,
                                onValueChange = {},
                                readOnly = true,
                                //                        textAlignment = TextAlign.End,
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.KeyboardArrowDown,
                                        contentDescription = "KeyboardArrowDown",
                                        modifier = Modifier.padding(end = 0.dp)
                                    )
//                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent
                                )

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

                    Divider()

                    // Birth Date
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Date of Birth",
                            modifier = Modifier
                                .padding(start = 8.dp),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
                        Text(
                            text = "${formatter.format(Date(selectedDate))}",
                            modifier = Modifier
                                .clickable { showDatePicker = true }
                                .padding(end = 8.dp)
                        )
                        if (showDatePicker) {
                            DatePickerDialog(
                                onDismissRequest = { showDatePicker = false },
                                confirmButton = {
                                    TextButton(onClick = {
                                        showDatePicker = false
                                        selectedDate = datePickerState.selectedDateMillis!!
                                    }) {
                                        Text(text = "OK")
                                    }
                                },
                                dismissButton = {
                                    TextButton(onClick = {
                                        showDatePicker = false
                                    }) {
                                        Text(text = "Cancel")
                                    }
                                }
                            )
                            {
                                DatePicker(
                                    state = datePickerState
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "KeyboardArrowDown",
                            modifier = Modifier
                                .padding(end = 8.dp)
                        )
                    }

                    Divider()

                    // Height
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Height",
                            modifier = Modifier
                                .padding(start = 8.dp),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = "180 CM",
                            modifier = Modifier.padding(end = 8.dp)
                        )

                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "KeyboardArrowDown",
                            modifier = Modifier
                                .padding(end = 8.dp)
                        )
                    }

                    Divider()

                    // Weight
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically)
                    {
                        Text(
                            text = "Weight",
                            modifier = Modifier
                                .padding(start = 8.dp),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "80 KG",
                            modifier = Modifier
                                .padding(end = 8.dp))
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "KeyboardArrowDown",
                            modifier = Modifier
                                .padding(end = 8.dp)
                        )
                    }
                }
            }
        }

        // Save button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(
                onClick = { /* Handle save action */ },
                modifier = Modifier
                    .height(80.dp)
                    .width(160.dp)
                    .padding(bottom = 32.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = ButtonBlue,
                    contentColor = FontWhite
                )
            ) {
                Text("Save", style = TextStyle(fontWeight = FontWeight.Bold))
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPersonalInfoScreen() {
    PersonalInfoScreen()
}