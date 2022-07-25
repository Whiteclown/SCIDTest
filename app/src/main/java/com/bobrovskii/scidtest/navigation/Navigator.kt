package com.bobrovskii.scidtest.navigation

import androidx.navigation.NavController
import com.bobrovskii.books.domain.entity.Book
import com.bobrovskii.detail.presentation.DetailRouter
import com.bobrovskii.detail.ui.DetailFragment
import com.bobrovskii.list.presentation.ListRouter
import com.bobrovskii.scidtest.R

class Navigator : ListRouter, DetailRouter {

	private var navController: NavController? = null

	fun bind(navController: NavController) {
		this.navController = navController
	}

	fun unbind() {
		navController = null
	}

	override fun routeToDetail(book: Book) {
		navController?.navigate(
			R.id.action_listFragment_to_detailFragment,
			DetailFragment.createBundle(book),
		)
	}

	override fun routeBack() {
		navController?.popBackStack()
	}
}