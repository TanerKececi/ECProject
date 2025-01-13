package com.example.ecproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Books")
data class BookDbModel(
    @PrimaryKey(autoGenerate = false)
    var bookId: String,
    val title: String? = "",
    val rank: Int? = 0,
    val author: String? = "",
    val description: String? = "",
    val bookImage: String? = "",
    val publisher: String? = "",
    var isFavorite: Boolean? = false
)

fun BookDbModel.toBookModel(): Book{
    return Book(
        bookId = bookId,
        title = title,
        rank = rank,
        author = author,
        description = description,
        bookImage = bookImage,
        publisher = publisher,
        isFavorite = isFavorite
    )
}
