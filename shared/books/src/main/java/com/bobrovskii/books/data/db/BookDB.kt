package com.bobrovskii.books.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bobrovskii.books.data.dto.BookDto
import com.bobrovskii.books.data.dto.BookRemoteKeys
import com.bobrovskii.books.domain.entity.Book

@Database(
	entities = [BookDto::class, BookRemoteKeys::class],
	version = 1,
	exportSchema = false,
)
abstract class BookDB : RoomDatabase() {

	abstract fun bookDao(): BookDao
	abstract fun bookRemoteKeysDao(): BookRemoteKeysDao
}