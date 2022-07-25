package com.bobrovskii.books.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bobrovskii.books.data.dto.BookRemoteKeys

@Dao
interface BookRemoteKeysDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addAllBookRemoteKeys(bookRemoteKeys: List<BookRemoteKeys>)

	@Query("SELECT * FROM book_remote_keys WHERE id = :bookId")
	suspend fun getBookRemoteKeys(bookId: Int): BookRemoteKeys?

	@Query("DELETE FROM book_remote_keys")
	suspend fun deleteAllBookRemoteKeys()
}