package com.bobrovskii.books.data.api

import com.bobrovskii.books.data.dto.CustomResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {

	@GET("api/books")
	suspend fun getBooks(@Query("page") page: Int = 1): Response<CustomResponseDto>
}