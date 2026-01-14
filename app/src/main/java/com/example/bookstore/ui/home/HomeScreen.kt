package com.example.bookstore.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is HomeViewModel.UiState.Loading -> {
            Text("Loading...")
        }

        is HomeViewModel.UiState.Success -> {
            val books =
                (state as HomeViewModel.UiState.Success).books

            LazyColumn {
                items(books) { book ->
                    Text(text = book.title)
                }
            }
        }

        is HomeViewModel.UiState.Error -> {
            Text("Error loading books")
        }
    }
}
