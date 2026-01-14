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
import androidx.compose.foundation.Image
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color






@Composable
fun HomeScreen(viewModel: HomeViewModel) {


    var showPopular by remember { mutableStateOf(true) }


    LaunchedEffect(showPopular) {
        viewModel.loadBooks(showPopular)
    }

    val state by viewModel.uiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = "BookStore",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start=2.dp,bottom = 16.dp)
            )




            // Tabs (Popular / New)
            Row {
                Button(
                    onClick = { showPopular = true },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text("Popular")
                }

                Button(
                    onClick = { showPopular = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text("New")
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            when (state) {

                is HomeViewModel.UiState.Loading -> {
                    Text("Loading...")
                }

                is HomeViewModel.UiState.Success -> {
                    val books =
                        (state as HomeViewModel.UiState.Success).books

                    LazyColumn {
                        items(books) { book ->
                            Row(
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                            ) {

                                book.cover_id?.let { coverId ->
                                    Image(
                                        painter = rememberAsyncImagePainter(
                                            "https://covers.openlibrary.org/b/id/$coverId-M.jpg"
                                        ),
                                        contentDescription = book.title,
                                        modifier = Modifier
                                            .height(80.dp)
                                            .padding(start= 3.dp,end = 12.dp)
                                    )
                                }
                            }
                                Column {

                                    Text(text = book.title)

                                    Text(
                                        text = book.authors
                                            ?.firstOrNull()
                                            ?.name
                                            ?: "Unknown author"
                                    )
                                }

                        }
                    }

                }

                is HomeViewModel.UiState.Error -> {
                    val message =
                        (state as HomeViewModel.UiState.Error).message

                    LaunchedEffect(message) {
                        scope.launch {
                            snackbarHostState.showSnackbar(message)
                        }
                    }
                }
            }
        }
    }
}