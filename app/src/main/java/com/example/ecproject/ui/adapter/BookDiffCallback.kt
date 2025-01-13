package com.example.ecproject.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.ecproject.model.Book

class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.rank == newItem.rank && oldItem.title == newItem.title // Compare unique identifier
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem // Check all fields
    }
}