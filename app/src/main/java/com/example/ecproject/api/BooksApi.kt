package com.example.ecproject.api

import com.example.ecproject.model.BooksResponse
import com.example.ecproject.util.Constants
import com.example.ecproject.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApi {
    @GET("lists/current/{listName}.json?api-key=${API_KEY}")
    suspend fun getBooks(@Path("listName") type: String): Response<BooksResponse>
}