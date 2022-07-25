package com.bobrovskii.books.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.bobrovskii.books.data.api.BooksApi
import com.bobrovskii.books.data.db.BookDB
import com.bobrovskii.books.data.dto.BookDto
import com.bobrovskii.books.data.dto.BookRemoteKeys

@OptIn(ExperimentalPagingApi::class)
class BookRemoteMediator(
	private val api: BooksApi,
	private val db: BookDB,
) : RemoteMediator<Int, BookDto>() {

	private val bookDao = db.bookDao()
	private val bookRemoteKeysDao = db.bookRemoteKeysDao()

	override suspend fun load(loadType: LoadType, state: PagingState<Int, BookDto>): MediatorResult {
		return try {
			val page = when (loadType) {
				LoadType.REFRESH -> {
					val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
					remoteKeys?.nextPage?.minus(1) ?: 1
				}

				LoadType.PREPEND -> {
					val remoteKeys = getRemoteKeyForFirstItem(state)
					val prevPage = remoteKeys?.prevPage
						?: return MediatorResult.Success(
							endOfPaginationReached = remoteKeys != null
						)
					prevPage
				}

				LoadType.APPEND  -> {
					val remoteKeys = getRemoteKeyForLastItem(state)
					val nextPage = remoteKeys?.nextPage
						?: return MediatorResult.Success(
							endOfPaginationReached = remoteKeys != null
						)
					nextPage
				}
			}
			val response = api.getBooks(page = page)
			var endOfPaginationReached = false
			if (response.isSuccessful) {
				val responseData = response.body()
				endOfPaginationReached = responseData == null
				responseData?.let {
					db.withTransaction {
						if (loadType == LoadType.REFRESH) {
							bookDao.deleteAllBooks()
							bookRemoteKeysDao.deleteAllBookRemoteKeys()
						}
						var prevPage: Int?
						var nextPage: Int

						responseData.result.currentPage.let { pageNumber ->
							nextPage = pageNumber + 1
							prevPage = if (pageNumber <= 1) null else pageNumber - 1
						}

						val keys = responseData.result.data.map { book ->
							BookRemoteKeys(
								id = book.id,
								prevPage = prevPage,
								nextPage = nextPage,
								lastUpdated = System.currentTimeMillis()
							)
						}
						bookRemoteKeysDao.addAllBookRemoteKeys(bookRemoteKeys = keys)
						bookDao.addBooks(books = responseData.result.data)
					}
				}

			}
			MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
		} catch (e: Exception) {
			return MediatorResult.Error(e)
		}
	}

	private suspend fun getRemoteKeyClosestToCurrentPosition(
		state: PagingState<Int, BookDto>,
	): BookRemoteKeys? {
		return state.anchorPosition?.let { position ->
			state.closestItemToPosition(position)?.id?.let { id ->
				bookRemoteKeysDao.getBookRemoteKeys(bookId = id)
			}
		}
	}

	private suspend fun getRemoteKeyForFirstItem(
		state: PagingState<Int, BookDto>,
	): BookRemoteKeys? {
		return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
			?.let { book ->
				bookRemoteKeysDao.getBookRemoteKeys(bookId = book.id)
			}
	}

	private suspend fun getRemoteKeyForLastItem(
		state: PagingState<Int, BookDto>,
	): BookRemoteKeys? {
		return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
			?.let { book ->
				bookRemoteKeysDao.getBookRemoteKeys(bookId = book.id)
			}
	}
}