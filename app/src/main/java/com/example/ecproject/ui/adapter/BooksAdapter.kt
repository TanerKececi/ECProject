package com.example.ecproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecproject.R
import com.example.ecproject.databinding.BookListItemBinding
import com.example.ecproject.model.Book
import com.example.ecproject.ui.adapter.BooksAdapter.BookViewHolder

class BooksAdapter(val itemClickListener: ItemClickListener) : ListAdapter<Book, BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }

    fun addItems(list: List<Book>) {
        // we have no pagination for this case :/
        val newList = currentList + list
        submitList(newList)
    }


    inner class BookViewHolder(private val binding: BookListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.apply {
                model = book
                root.setOnClickListener {
                    itemClickListener.onItemClicked(book)
                }
            }
        }
    }
}
