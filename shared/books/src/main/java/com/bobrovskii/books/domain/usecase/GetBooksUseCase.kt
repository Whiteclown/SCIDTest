package com.bobrovskii.books.domain.usecase

import com.bobrovskii.books.domain.repository.BooksRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
	private val repository: BooksRepository,
) {

	suspend operator fun invoke() =
		repository.getBooks()
}