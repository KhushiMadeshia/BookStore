package com.example.bookstore.data.api

import com.example.bookstore.data.model.BookResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface BookApi {

    @GET("subjects/fiction.json")
    fun getPopularBooks(): Single<BookResponse>

    @GET("subjects/programming.json")
    fun getNewBooks(): Single<BookResponse>

}

