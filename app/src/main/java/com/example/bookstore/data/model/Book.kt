package com.example.bookstore.data.model

data class Book(
    val title: String,
    val authors: List<Author>?,
    val cover_id: Int?
)

data class Author(
    val name: String
)
