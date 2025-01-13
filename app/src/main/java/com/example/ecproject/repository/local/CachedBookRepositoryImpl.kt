package com.example.ecproject.repository.local

import com.example.ecproject.db.BookDatabase
import com.example.ecproject.model.BookDbModel

class CachedBookRepositoryImpl(private val db: BookDatabase): CachedBooksRepository {
    override suspend fun insert(book: BookDbModel) {
        db.getCachedBookDao().insert(book)
    }
    override suspend fun getBooks(): List<BookDbModel> = db.getCachedBookDao().getAllBooks()
    override suspend fun setBooks(list: List<BookDbModel>) {
        list.forEach {
            db.getCachedBookDao().insert(it)
        }
    }
}