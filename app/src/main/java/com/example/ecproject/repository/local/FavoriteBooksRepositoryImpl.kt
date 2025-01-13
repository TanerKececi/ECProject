package com.example.ecproject.repository.local

import com.example.ecproject.db.BookDatabase
import com.example.ecproject.model.FavoriteBookDbModel

class FavoriteBooksRepositoryImpl(private val db: BookDatabase): FavoriteBooksRepository {

    override suspend fun insert(book: FavoriteBookDbModel) = db.getFavoriteBookedDao().insert(book)

    override suspend fun getAllFavoriteBooks(): List<FavoriteBookDbModel> = db.getFavoriteBookedDao().getAllFavoriteBooks()

    override suspend fun removeFavoriteBook(bookId: String) {
        db.getFavoriteBookedDao().deleteBook(bookId)
    }

    override suspend fun doesBookExistInFavoriteDb(book: FavoriteBookDbModel): Boolean {
        return db.getFavoriteBookedDao().doesBookExist(book.bookId)
    }

    override suspend fun doesBookExistInFavoriteDb(title: String?, author: String?): Boolean {
        return db.getFavoriteBookedDao().doesBookExist(title, author)
    }
}