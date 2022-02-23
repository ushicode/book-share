package com.collinsushi.bookshare.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("bookImageUrl")
fun loadBookImage(view: ImageView, bookImageUrl: String){
    Glide.with(view.context)
        .load(bookImageUrl)
        .into(view)
}