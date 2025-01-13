package com.example.ecproject.repository.remote

import com.example.ecproject.model.BooksResponse
import retrofit2.Response

interface BooksRepository {
    suspend fun getBooks(listName: String): Response<BooksResponse>

    suspend fun searchBooks(searchQuery: String?, limit: Int?, offset: Int?): Response<BooksResponse>
}