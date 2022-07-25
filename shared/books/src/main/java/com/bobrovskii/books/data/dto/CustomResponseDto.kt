package com.bobrovskii.books.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CustomResponseDto(
	val success: Boolean,
	val result: ResultDto,
)
