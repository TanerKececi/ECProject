package com.example.ecproject.domain.bindingadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.ecproject.R

@BindingAdapter("setRank")
fun TextView.setRank(rank: Int) {
    text = String.format(context.getString(R.string.rank), rank)
}

@BindingAdapter("setRankHashtag")
fun TextView.setRankHashtag(rank: Int) {
    text = String.format(context.getString(R.string.rank_hashtag), rank)
}

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}

@BindingAdapter("setFavorite")
fun ImageView.setFavorite(isFavorite: Boolean) {
    if (isFavorite) {
        setImageResource(R.drawable.star_solid_filled)
    } else {
        setImageResource(R.drawable.star_solid_empty)
    }
}