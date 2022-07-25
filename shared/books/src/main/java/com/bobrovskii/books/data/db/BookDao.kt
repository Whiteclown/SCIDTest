package com.bobrovskii.books.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bobrovskii.books.data.dto.BookDto
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addBooks(books: List<BookDto>)

	@Query("SELECT * FROM books")
	fun getAllBooks(): PagingSource<Int, BookDto>

	@Query("SELECT * FROM books WHERE id = :bookId")
	fun getBook(bookId: Int): Flow<BookDto>

	@Query("DELETE FROM books")
	suspend fun deleteAllBooks()
}