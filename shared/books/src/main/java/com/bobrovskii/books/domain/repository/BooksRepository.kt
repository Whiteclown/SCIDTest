package com.bobrovskii.books.domain.repository

import androidx.paging.PagingData
import com.bobrovskii.books.domain.entity.Book
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

	suspend fun getBooks(): Flow<PagingData<Book>>
}