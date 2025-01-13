package com.example.ecproject.model

import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("num_results") val numResults: Int?,
    @SerializedName("results") val results: ListResult?
)


