package com.bobrovskii.detail.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.bobrovskii.books.domain.entity.Book
import com.bobrovskii.detail.R
import com.bobrovskii.detail.databinding.FragmentDetailBinding
import com.bobrovskii.detail.presentation.DetailState
import com.bobrovskii.detail.presentation.DetailViewModel
import com.bobrovskii.detail.presentation.Keys
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

	private var _binding: FragmentDetailBinding? = null
	private val binding get() = _binding!!

	private val viewModel: DetailViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		_binding = FragmentDetailBinding.bind(view)
		initListeners()
		viewModel.state.onEach(::render).launchIn(viewModel.viewModelScope)
		viewModel.loadData()
	}

	private fun initListeners() {
		binding.ibtnBack.setOnClickListener { viewModel.routeBack() }
	}

	private fun render(state: DetailState) {
		when (state) {
			is DetailState.Loading -> {
				binding.viewLoading.root.visibility = View.VISIBLE
			}

			is DetailState.Content -> {
				with(binding) {
					tvName.text = state.book.name
					tvDate.text = state.book.date
					tvDescription.text = state.book.description
					tvScreenName.text = state.book.name
					viewLoading.root.visibility = View.GONE
				}
			}
		}
	}

	companion object {

		fun createBundle(book: Book) =
			bundleOf(
				Keys.BOOK_DETAIL to book,
			)
	}
}