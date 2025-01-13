package com.example.ecproject.model

import com.google.gson.annotations.SerializedName

data class ListResult(
    @SerializedName("list_name") val listName: String?,
    @SerializedName("books") val books: List<Book>?
)
