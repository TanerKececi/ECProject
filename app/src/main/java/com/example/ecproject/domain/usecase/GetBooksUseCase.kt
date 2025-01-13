package com.example.ecproject.domain.usecase

import com.example.ecproject.api.BooksRepository
import com.example.ecproject.model.BooksResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

data class GetBooksParam(
    val type: String?
)

class GetBooksUseCase
@Inject
constructor(
   private val booksRepository: BooksRepository
): FlowUseCase<GetBooksParam, Response<BooksResponse>> {
    override suspend fun invoke(param: GetBooksParam?): Flow<Response<BooksResponse>> = flow {
        if (param == null) {
            throw NullParamsException()
        }

        param.type?.let {
            val response = booksRepository.getBooks(it)
            emit(response)
        }
    }
}