package com.example.ecproject.repository.local

import com.example.ecproject.model.BookDbModel

interface CachedBooksRepository{
    suspend fun insert(book: BookDbModel)
    suspend fun getBooks(): List<BookDbModel>
    suspend fun setBooks(list: List<BookDbModel>)
}