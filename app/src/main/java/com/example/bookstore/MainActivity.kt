package com.example.bookstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bookstore.ui.home.HomeScreen
import com.example.bookstore.ui.home.HomeViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: HomeViewModel = getViewModel()
            HomeScreen(viewModel)
        }
    }
}
