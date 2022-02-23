package com.collinsushi.bookshare.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import com.collinsushi.bookshare.model.Book
import com.collinsushi.bookshare.service.BookService
import com.collinsushi.bookshare.utility.WEB_SERVICE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * The Book Repository acquires data from the data source and publishes it via a LiveData object; so that other
 * components (ViewModels) in this application can subscribe to it using an observer pattern.
 */

class BookRepository(val app:Application) {

    val bookData = MutableLiveData<List<Book>>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            callBookService()
        }
    }

    @WorkerThread
    suspend fun callBookService() {
        if(networkAvailable()) {  //Verify network availability
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(BookService::class.java)
            val serviceData = service.getBookData().body() ?: emptyList()
            bookData.postValue(serviceData)
        }
    }

    fun refreshBookData() {
            CoroutineScope(Dispatchers.IO).launch {
            callBookService()
         }
    }


    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }


}