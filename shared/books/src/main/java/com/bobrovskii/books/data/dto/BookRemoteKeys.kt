package com.bobrovskii.books.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_remote_keys")
data class BookRemoteKeys(
	@PrimaryKey(autoGenerate = false)
	val id: Int,
	val prevPage: Int?,
	val nextPage: Int?,
	val lastUpdated: Long?,
)