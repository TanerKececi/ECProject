package com.example.ecproject.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecproject.model.FavoriteBookDbModel

@Dao
interface FavoriteBookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: FavoriteBookDbModel): Long

    @Query("SELECT * FROM FavoriteBooks")
    fun getAllFavoriteBooks(): List<FavoriteBookDbModel>

    @Query("DELETE FROM FavoriteBooks WHERE bookId = :bookId")
    suspend fun deleteBook(bookId: String)

    @Query("SELECT COUNT(*) > 0 FROM FavoriteBooks WHERE bookId = :bookId")
    suspend fun doesBookExist(bookId: String): Boolean

    @Query("SELECT COUNT(*) > 0 FROM FavoriteBooks WHERE title = :title AND author =:author")
    suspend fun doesBookExist(title: String?, author: String?): Boolean
}