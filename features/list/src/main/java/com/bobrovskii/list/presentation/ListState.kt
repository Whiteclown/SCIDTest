package com.bobrovskii.list.presentation

import androidx.paging.PagingData
import com.bobrovskii.books.domain.entity.Book

sealed interface ListState {

	object Loading : ListState

	data class Content(
		val data: PagingData<Book>,
	) : ListState
}