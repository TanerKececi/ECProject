package com.example.ecproject.repository.local

import com.example.ecproject.model.FavoriteBookDbModel

interface FavoriteBooksRepository {
    suspend fun insert(book: FavoriteBookDbModel): Long
    suspend fun getAllFavoriteBooks(): List<FavoriteBookDbModel>
    suspend fun removeFavoriteBook(bookId: String)
    suspend fun doesBookExistInFavoriteDb(book: FavoriteBookDbModel): Boolean
    suspend fun doesBookExistInFavoriteDb(title: String?, author: String?): Boolean
}