package com.collinsushi.bookshare.model

import com.collinsushi.bookshare.utility.IMAGE_BASE_URL

data class Book (
    val id: Int,
    val category: String,
    val title: String,
    val author: String,
    val author_biography: String,
    val rating: Int,
    val image: String,
    val post_id: String,
    val availability: String,
    val availability_date: String,
    val details: String,
    val purchase_url: String
){
    //Using explicit getter function for book image and its thumbnail
    val imageUrl
        get() = "$IMAGE_BASE_URL/books/$image.jpeg"

    val thumbnailUrl
        get() = "$IMAGE_BASE_URL/thumb/${image}_tn.jpeg"

}