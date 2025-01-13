package com.example.ecproject.domain.usecase.local

import com.example.ecproject.model.FavoriteBookDbModel
import com.example.ecproject.repository.local.FavoriteBooksRepository
import javax.inject.Inject

class FavoriteBooksPersistUseCase
@Inject
constructor(
    private val favoriteBooksRepository: FavoriteBooksRepository
) {
    suspend fun insert(article: FavoriteBookDbModel) {
        favoriteBooksRepository.insert(article)
    }

    suspend fun getAllFavoriteBooks(): List<FavoriteBookDbModel> {
        return favoriteBooksRepository.getAllFavoriteBooks()
    }

    suspend fun removeFavoriteBook(bookId: String) {
        favoriteBooksRepository.removeFavoriteBook(bookId)
    }

    suspend fun doesBookExistInFavoriteDb(book: FavoriteBookDbModel): Boolean {
        return favoriteBooksRepository.doesBookExistInFavoriteDb(book)
    }

    suspend fun doesBookExistInFavoriteDb(title: String?, author: String?): Boolean {
        return favoriteBooksRepository.doesBookExistInFavoriteDb(title, author)
    }
}