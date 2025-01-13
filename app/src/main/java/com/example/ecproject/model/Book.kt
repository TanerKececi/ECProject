package com.example.ecproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    @SerializedName("title") val title: String? = "",
    @SerializedName("rank") val rank: Int? = 0,
    @SerializedName("author") val author: String? = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("book_image") val bookImage: String? = "",
    @SerializedName("publisher") val publisher: String? = "",
    @SerializedName("is_favorite") var isFavorite: Boolean? = false
): Parcelable
