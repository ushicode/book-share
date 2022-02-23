package com.collinsushi.bookshare.service

import com.collinsushi.bookshare.model.Book
import retrofit2.Response
import retrofit2.http.GET

interface BookService {
    @GET("books.json")
    suspend fun getBookData(): Response<List<Book>>
}