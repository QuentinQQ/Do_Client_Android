package com.example.retrofitgooglelab

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RetrofitViewModel: ViewModel() {
    private val repository = ItemsRepository()
    val retrofitResponse: MutableState <SearchResponse> = mutableStateOf(SearchResponse())

    fun getResponse(keyword:String) {
        viewModelScope.launch {
            try {
                val responseReturned = repository.getResponse(keyword)
                retrofitResponse.value = responseReturned
            } catch (e: Exception) {
                Log.i("Error ", "Response failed")
            }
        }
    }
}


@Composable
fun SearchResults(viewModel:RetrofitViewModel) {
    val itemsReturned by viewModel.retrofitResponse
    var searchResult=""
    var keyword by remember { mutableStateOf ("") }

    Column {
        OutlinedTextField(
            value = keyword ,
            onValueChange = { keyword = it },
            label = { Text("Keyword") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        Button(
            onClick = { viewModel.getResponse(keyword)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Search")
        }

        val list = itemsReturned.items
        if(list.isNotEmpty()) {
            val result: String = list[0].snippet
            searchResult = result
        }
        Text(text = "Results: $searchResult")
    }
}