package com.bobrovskii.list.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bobrovskii.list.R
import com.bobrovskii.list.databinding.FragmentListBinding
import com.bobrovskii.list.presentation.ListState
import com.bobrovskii.list.presentation.ListViewModel
import com.bobrovskii.list.ui.adapter.BooksAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

	private var _binding: FragmentListBinding? = null
	private val binding get() = _binding!!

	private val viewModel: ListViewModel by viewModels()

	private val booksAdapter = BooksAdapter(
		onItemClicked = { viewModel.routeToDetail(it) }
	)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		_binding = FragmentListBinding.bind(view)
		initRV()
		viewModel.state.onEach(::render).launchIn(viewModel.viewModelScope)
	}

	private fun initRV() {
		binding.rvBooks.adapter = booksAdapter
		binding.rvBooks.addItemDecoration(
			DividerItemDecoration(
				context,
				LinearLayoutManager.VERTICAL,
			)
		)
	}

	private fun render(state: ListState) {
		when (state) {
			is ListState.Loading -> {
				binding.viewLoading.root.visibility = View.VISIBLE
			}

			is ListState.Content -> {
				lifecycleScope.launch {
					booksAdapter.submitData(state.data)
				}
				binding.viewLoading.root.visibility = View.GONE
			}
		}
	}
}