package com.bobrovskii.books.data.mapper

import com.bobrovskii.books.data.dto.BookDto
import com.bobrovskii.books.data.dto.ResultDto
import com.bobrovskii.books.data.dto.CustomResponseDto
import com.bobrovskii.books.domain.entity.Book
import com.bobrovskii.books.domain.entity.Result
import com.bobrovskii.books.domain.entity.CustomResponse

fun CustomResponseDto.toEntity() =
	CustomResponse(
		success = success,
		result = result.toEntity()
	)

fun ResultDto.toEntity() =
	Result(
		data = data.map { it.toEntity() }
	)

fun BookDto.toEntity() =
	Book(
		id = id,
		name = name,
		description = description,
		date = date,
	)