package com.example.ecproject.di.module

import android.app.Application
import androidx.room.Room
import com.example.ecproject.db.BookDatabase
import com.example.ecproject.repository.local.CachedBookRepositoryImpl
import com.example.ecproject.repository.local.CachedBooksRepository
import com.example.ecproject.repository.local.FavoriteBooksRepository
import com.example.ecproject.repository.local.FavoriteBooksRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideBookDatabase(app: Application): BookDatabase {
        return Room.databaseBuilder(
            app,
            BookDatabase::class.java,
            "book_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCachedBookDao(db: BookDatabase) = db.getCachedBookDao()

    @Provides
    @Singleton
    fun provideFavoriteBookDao(db: BookDatabase) = db.getFavoriteBookedDao()


    @Provides
    @Singleton
    fun provideCachedBookLocalRepository(db: BookDatabase): CachedBooksRepository {
        return CachedBookRepositoryImpl(db)
    }

    @Provides
    @Singleton
    fun provideCachedNewsLocalRepository(db: BookDatabase): FavoriteBooksRepository {
        return FavoriteBooksRepositoryImpl(db)
    }
}