package com.bobrovskii.detail.presentation

import com.bobrovskii.books.domain.entity.Book

sealed interface DetailState {

	object Loading : DetailState

	data class Content(
		val book: Book,
	) : DetailState
}