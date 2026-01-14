package com.example.bookstore.di

import com.example.bookstore.data.api.RetrofitProvider
import com.example.bookstore.data.repository.BookRepository
import com.example.bookstore.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { RetrofitProvider.provideBookApi() }

    single { BookRepository(get()) }

    viewModel { HomeViewModel(get()) }
}
