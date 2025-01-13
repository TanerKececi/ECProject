package com.example.ecproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecproject.databinding.FavoriteBookListItemBinding
import com.example.ecproject.model.Book
import com.example.ecproject.ui.adapter.FavoriteBooksAdapter.FavoriteBookViewHolder

class FavoriteBooksAdapter(val itemClickListener: ItemClickListener) : ListAdapter<Book, FavoriteBookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteBookViewHolder {
        val binding = FavoriteBookListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteBookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteBookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }


    inner class FavoriteBookViewHolder(private val binding: FavoriteBookListItemBinding) : RecyclerView.ViewHolder(binding.root) {

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