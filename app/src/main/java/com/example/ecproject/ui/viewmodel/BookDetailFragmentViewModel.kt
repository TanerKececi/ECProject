package com.example.ecproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecproject.domain.usecase.local.FavoriteBooksPersistUseCase
import com.example.ecproject.model.Book
import com.example.ecproject.model.toFavoriteBookDbModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailFragmentViewModel
@Inject
constructor(
    private val favoriteBooksPersistUseCase: FavoriteBooksPersistUseCase
) : ViewModel() {

    private val _bookData: MutableStateFlow<Book?> = MutableStateFlow(null)
    val bookData: StateFlow<Book?> = _bookData

    fun setBook(book: Book) {
        viewModelScope.launch {
            _bookData.emit(book)
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            val book = _bookData.value ?: return@launch
            if (book.isFavorite == true) {
                val updatedBook = book.copy(isFavorite = false)
                favoriteBooksPersistUseCase.removeFavoriteBook(book.bookId)
                _bookData.emit(updatedBook)
            } else {
                val updatedBook = book.copy(isFavorite = true)
                favoriteBooksPersistUseCase.insert(book.toFavoriteBookDbModel())
                _bookData.emit(updatedBook)
            }
        }
    }

}