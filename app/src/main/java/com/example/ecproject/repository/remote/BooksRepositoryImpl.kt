package com.example.ecproject.repository.remote

import com.example.ecproject.model.BooksResponse
import retrofit2.Response
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val booksService: BooksApi
): BooksRepository {
    override suspend fun getBooks(listName: String): Response<BooksResponse> {
        return booksService.getBooks(listName)
    }

    override suspend fun searchBooks(
        searchQuery: String?,
        limit: Int?,
        offset: Int?
    ): Response<BooksResponse> {
        TODO("Not yet implemented")
    }

}