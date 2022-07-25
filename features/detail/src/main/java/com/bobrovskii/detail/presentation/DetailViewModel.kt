package com.bobrovskii.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bobrovskii.books.domain.entity.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
	private val args: SavedStateHandle,
	private val router: DetailRouter,
) : ViewModel() {

	private val _state = MutableStateFlow<DetailState>(DetailState.Loading)
	val state get() = _state.asStateFlow()

	fun loadData() {
		if (_state.value is DetailState.Loading) {
			args.get<Book>(Keys.BOOK_DETAIL)?.let {
				_state.value = DetailState.Content(
					book = it,
				)
			}
		}
	}

	fun routeBack() =
		router.routeBack()
}