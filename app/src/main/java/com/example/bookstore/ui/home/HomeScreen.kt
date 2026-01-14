package com.example.bookstore.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState


@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    // 1️⃣ Track which list to show
    var showPopular by remember { mutableStateOf(true) }

    // 2️⃣ Trigger API call when screen opens OR tab changes
    LaunchedEffect(showPopular) {
        viewModel.loadBooks(showPopular)
    }

    // 3️⃣ Observe state from ViewModel
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {

        // 🔹 Tabs (Popular / New)
        Row {
            Button(
                onClick = { showPopular = true },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text("Popular")
            }

            Button(onClick = { showPopular = false }) {
                Text("New")
            }
        }

        // 🔹 UI based on state
        when (state) {
            is HomeViewModel.UiState.Loading -> {
                Text("Loading...")
            }

            is HomeViewModel.UiState.Success -> {
                val books =
                    (state as HomeViewModel.UiState.Success).books

                LazyColumn {
                    items(books) { book ->
                        Text(
                            text = book.title,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

            is HomeViewModel.UiState.Error -> {
                Text(
                    text = (state as HomeViewModel.UiState.Error).message
                )

            }
        }
    }
}
