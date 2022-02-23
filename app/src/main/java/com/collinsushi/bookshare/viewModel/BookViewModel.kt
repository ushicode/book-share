package com.collinsushi.bookshare.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.collinsushi.bookshare.model.Book
import com.collinsushi.bookshare.repository.BookRepository


class BookViewModel(app: Application) : AndroidViewModel(app) {

    private val dataRepo = BookRepository(app)
    val bookData = dataRepo.bookData

    val selectedBookData = MutableLiveData<Book>() //Mutable changes value at runtime

    fun refreshBookData() {
        dataRepo.refreshBookData()
    }

}