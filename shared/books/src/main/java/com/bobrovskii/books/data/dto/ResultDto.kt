package com.bobrovskii.books.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultDto(
	@SerialName("current_page")
	val currentPage: Int,
	val data: List<BookDto>,
)