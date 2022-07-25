package com.bobrovskii.list.presentation

import com.bobrovskii.books.domain.entity.Book

interface ListRouter {

	fun routeToDetail(book: Book)
}