package com.example.ecproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecproject.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailFragmentViewModel
@Inject
constructor(

) : ViewModel() {

    private val _bookData: MutableStateFlow<Book?> = MutableStateFlow(null)
    val bookData: StateFlow<Book?> = _bookData

    fun setBook(book: Book) {
        viewModelScope.launch {
            _bookData.emit(book)
        }
    }

}