package com.bobrovskii.books.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
	val id: Int,
	val name: String,
	val description: String,
	val date: String,
) : Parcelable