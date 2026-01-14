package com.example.bookstore.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

object RetrofitProvider {

    private const val BASE_URL = "https://openlibrary.org/"

    fun provideBookApi(): BookApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(BookApi::class.java)
    }
}
