package com.example.ecproject.domain.usecase.local

import com.example.ecproject.model.BookDbModel
import com.example.ecproject.repository.local.CachedBooksRepository
import javax.inject.Inject

class CachedBooksPersistUseCase
@Inject
constructor(
    private val cachedBooksRepository: CachedBooksRepository
) {
    suspend fun insert(article: BookDbModel) {
        cachedBooksRepository.insert(article)
    }

    suspend fun getAllCachedArticles(): List<BookDbModel> {
        return cachedBooksRepository.getBooks()
    }
}