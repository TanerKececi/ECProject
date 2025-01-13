package com.example.ecproject.api

import com.example.ecproject.model.BooksResponse
import retrofit2.Response

interface BooksRepository {
    suspend fun getBooks(listName: String): Response<BooksResponse>

    suspend fun searchBooks(searchQuery: String?, limit: Int?, offset: Int?): Response<BooksResponse>
}