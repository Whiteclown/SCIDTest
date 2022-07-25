package com.bobrovskii.list.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bobrovskii.books.domain.entity.Book
import com.bobrovskii.list.databinding.ItemBookBinding

class BooksViewHolder(
	private val binding: ItemBookBinding,
) : RecyclerView.ViewHolder(binding.root) {

	fun bind(
		item: Book,
		onItemClicked: (Book) -> Unit,
	) {
		binding.tvName.text = item.name
		binding.ibtnOpen.setOnClickListener { onItemClicked(item) }
	}

	companion object {

		fun from(parent: ViewGroup): BooksViewHolder {
			val layoutInflater = LayoutInflater.from(parent.context)
			val binding = ItemBookBinding.inflate(layoutInflater, parent, false)
			return BooksViewHolder(binding)
		}
	}
}