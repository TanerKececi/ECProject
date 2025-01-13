package com.example.ecproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecproject.domain.network.NetworkConnectionInterceptor
import com.example.ecproject.domain.usecase.local.CachedBooksPersistUseCase
import com.example.ecproject.domain.usecase.local.FavoriteBooksPersistUseCase
import com.example.ecproject.domain.usecase.remote.GetBooksParam
import com.example.ecproject.domain.usecase.remote.GetBooksUseCase
import com.example.ecproject.model.Book
import com.example.ecproject.model.BooksResponse
import com.example.ecproject.model.ListResult
import com.example.ecproject.model.toBookModel
import com.example.ecproject.model.toDbModel
import com.example.ecproject.util.Constants.HARDCOVER_FICTION
import com.example.ecproject.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel
@Inject
constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val cachedBooksPersistUseCase: CachedBooksPersistUseCase,
    private val favoriteBooksPersistUseCase: FavoriteBooksPersistUseCase,
    private val networkConnectionInterceptor: NetworkConnectionInterceptor
): ViewModel() {

    companion object {
        const val TAG = "MainPageViewModel"
    }

    private val _booksData: MutableStateFlow<Resource<BooksResponse>> = MutableStateFlow(Resource.NoConnection(null))
    val booksData: StateFlow<Resource<BooksResponse>> = _booksData

    fun getBooks() {
        viewModelScope.launch {
            if (!checkInternetConnection()) {
                useCachedData()
                return@launch
            }
            val listName = HARDCOVER_FICTION
            val param = GetBooksParam(listName)
            getBooksUseCase.invoke(param)
                .catch { error ->
                    _booksData.emit(Resource.Error(error.message))
                    Log.e(TAG, "error: ${error.stackTraceToString()}")
                }
                .collect { response ->
                    val list = response.body()?.results?.books
                    checkBooksExistInFavoriteDb(list)
                    _booksData.emit(Resource.Success(response.body()))
                    insertItemsToCache(list)
                    Log.d(TAG, "response: $response")
                }
        }
    }

    private fun useCachedData() {
        viewModelScope.launch(Dispatchers.IO) {
            val cachedList = cachedBooksPersistUseCase.getAllCachedArticles().map { it.toBookModel() }
            checkBooksExistInFavoriteDb(cachedList)
            val listResult = ListResult(
                HARDCOVER_FICTION,
                cachedList
            )
            val bookResponse = BooksResponse(
                status = "cached",
                numResults = cachedList.size,
                results = listResult
            )
            _booksData.emit(Resource.Success(bookResponse))
        }
    }

    private fun insertItemsToCache(books: List<Book>?) {
        viewModelScope.launch(Dispatchers.IO) {
            val dbList = books?.map { it.toDbModel() }
            dbList?.forEach {
                cachedBooksPersistUseCase.insert(it)
            }
        }
    }

    private fun checkBooksExistInFavoriteDb(list: List<Book>?) {
        viewModelScope.launch {
            val favoriteBookIds = withContext(Dispatchers.IO) {
                favoriteBooksPersistUseCase.getAllFavoriteBooks().map { it.bookId }
            }
            list?.forEach { book ->
                book.isFavorite = favoriteBookIds.contains(book.bookId)
            }
        }
    }

    fun checkInternetConnection(): Boolean {
        return networkConnectionInterceptor.isInternetAvailable()
    }
}