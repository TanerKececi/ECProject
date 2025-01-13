package com.example.ecproject.ui.adapter

import com.example.ecproject.model.Book

interface ItemClickListener {
    fun onItemClicked(book: Book)
}