package com.example.bookstore.data.repository

import com.example.bookstore.data.api.BookApi
import com.example.bookstore.data.model.Book
import io.reactivex.rxjava3.core.Single

class BookRepository(
    private val api: BookApi
) {

    fun fetchBooks(): Single<Pair<List<Book>, List<Book>>> {
        return Single.zip(
            api.getPopularBooks(),
            api.getNewBooks()
        ) { popular, new ->
            Pair(popular.works, new.works)
        }
    }
}
