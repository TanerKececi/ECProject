package com.example.ecproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecproject.domain.usecase.local.FavoriteBooksPersistUseCase
import com.example.ecproject.model.Book
import com.example.ecproject.model.toBookModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteBooksViewModel
@Inject
constructor(
    private val favoriteBooksPersistUseCase: FavoriteBooksPersistUseCase
) : ViewModel() {
    private val _booksData: MutableStateFlow<List<Book>?> = MutableStateFlow(null)
    val booksData: StateFlow<List<Book>?> = _booksData

    fun getBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            val books = favoriteBooksPersistUseCase.getAllFavoriteBooks().map { it.toBookModel() }
            _booksData.emit(books)
        }
    }
}