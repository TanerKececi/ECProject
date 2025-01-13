package com.example.ecproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecproject.api.BooksRepository
import com.example.ecproject.domain.usecase.GetBooksParam
import com.example.ecproject.domain.usecase.GetBooksUseCase
import com.example.ecproject.model.BooksResponse
import com.example.ecproject.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel
@Inject
constructor(
    private val getBooksUseCase: GetBooksUseCase
): ViewModel() {

    companion object {
        const val TAG = "MainPageViewModel"
    }

    private val _booksData: MutableStateFlow<Resource<BooksResponse>> = MutableStateFlow(Resource.NoConnection(null))
    val booksData: StateFlow<Resource<BooksResponse>> = _booksData

    fun getBooks() {
        viewModelScope.launch {
            val listName = "hardcover-fiction"
            val param = GetBooksParam(listName)
            getBooksUseCase.invoke(param)
                .catch { error ->
                    _booksData.emit(Resource.Error(error.message))
                    Log.e(TAG, "error: ${error.stackTraceToString()}")
                }
                .collect { response ->
                    _booksData.emit(Resource.Success(response.body()))
                    Log.d(TAG, "response: $response")
                }
        }
    }
}