package com.bobrovskii.books.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.bobrovskii.books.data.api.BooksApi
import com.bobrovskii.books.data.db.BookDB
import com.bobrovskii.books.data.mapper.toEntity
import com.bobrovskii.books.data.paging.BookRemoteMediator
import com.bobrovskii.books.domain.entity.Book
import com.bobrovskii.books.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
	private val api: BooksApi,
	private val db: BookDB,
) : BooksRepository {

	private val bookDao = db.bookDao()

	@OptIn(ExperimentalPagingApi::class)
	override suspend fun getBooks(): Flow<PagingData<Book>> {
		val pagingSourceFactory = { bookDao.getAllBooks() }
		return Pager(
			config = PagingConfig(pageSize = 10),
			remoteMediator = BookRemoteMediator(
				api = api,
				db = db,
			),
			pagingSourceFactory = pagingSourceFactory,
		).flow.map { pag -> pag.map { it.toEntity() } }
	}
}