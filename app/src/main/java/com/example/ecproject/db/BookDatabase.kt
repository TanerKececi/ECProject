package com.example.ecproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ecproject.model.BookDbModel
import com.example.ecproject.model.FavoriteBookDbModel

@Database(
    entities = [BookDbModel::class, FavoriteBookDbModel::class],
    version = 1
)
abstract class BookDatabase: RoomDatabase() {
    abstract fun getCachedBookDao(): BookDao

    abstract fun getFavoriteBookedDao(): FavoriteBookDao


    companion object {
        @Volatile
        private var instance: BookDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BookDatabase::class.java,
                "book_db.db"
            ).build()

    }
}