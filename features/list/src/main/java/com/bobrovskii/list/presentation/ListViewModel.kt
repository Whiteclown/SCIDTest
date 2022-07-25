package com.bobrovskii.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bobrovskii.books.domain.entity.Book
import com.bobrovskii.books.domain.usecase.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
	private val getBooksUseCase: GetBooksUseCase,
	private val router: ListRouter,
) : ViewModel() {

	private val _state = MutableStateFlow<ListState>(ListState.Loading)
	val state get() = _state.asStateFlow()

	init {
		viewModelScope.launch {
			getBooksUseCase().cachedIn(viewModelScope).collect {
				_state.value = ListState.Content(
					data = it,
				)
			}
		}
	}

	fun routeToDetail(book: Book) =
		router.routeToDetail(book)
}