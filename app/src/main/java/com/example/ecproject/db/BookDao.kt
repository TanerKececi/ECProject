package com.example.ecproject.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecproject.model.BookDbModel

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: BookDbModel): Long

    @Query("SELECT * FROM books")
    fun getAllBooks(): List<BookDbModel>

    @Query("DELETE FROM books")
    suspend fun deleteAllBooks()

    @Query("DELETE FROM books WHERE bookId = :bookId")
    suspend fun deleteBook(bookId: String)

    @Query("SELECT COUNT(*) > 0 FROM books WHERE bookId = :bookId")
    suspend fun doesBookExist(bookId: String): Boolean
}