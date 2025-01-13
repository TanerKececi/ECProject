package com.example.ecproject.repository.remote

import com.example.ecproject.model.BooksResponse
import com.example.ecproject.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApi {
    @GET("lists/current/{listName}.json?api-key=${API_KEY}")
    suspend fun getBooks(@Path("listName") type: String): Response<BooksResponse>
}