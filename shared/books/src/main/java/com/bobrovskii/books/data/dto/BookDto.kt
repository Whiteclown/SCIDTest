package com.bobrovskii.books.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "books")
data class BookDto(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val name: String,
	val description: String,
	val date: String,
)