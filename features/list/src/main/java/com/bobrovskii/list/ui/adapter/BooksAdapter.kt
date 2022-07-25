package com.bobrovskii.list.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bobrovskii.books.domain.entity.Book
import com.bobrovskii.list.ui.adapter.viewholder.BooksViewHolder

class BooksAdapter(
	private val onItemClicked: (Book) -> Unit,
) : PagingDataAdapter<Book, BooksViewHolder>(BookComparator) {

	/*var books: List<Book>? = null
		set(value) {
			field = value
			notifyDataSetChanged()
		}*/

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder =
		BooksViewHolder.from(parent)

	override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
		val item = getItem(position)
		item?.let {
			holder.bind(
				item = item,
				onItemClicked = onItemClicked,
			)
		}
	}
}

object BookComparator : DiffUtil.ItemCallback<Book>() {
	override fun areItemsTheSame(oldItem: Book, newItem: Book) =
		oldItem.id == newItem.id

	override fun areContentsTheSame(oldItem: Book, newItem: Book) =
		oldItem == newItem
}