package com.collinsushi.bookshare.view.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.collinsushi.bookshare.R
import com.collinsushi.bookshare.adapter.BookRecyclerAdapter
import com.collinsushi.bookshare.model.Book
import com.collinsushi.bookshare.viewModel.BookViewModel

class BookFeedsFragment : Fragment (),
    BookRecyclerAdapter.BookItemListener{

    private lateinit var viewModel: BookViewModel
    private lateinit var recyclerViewBookFeeds: RecyclerView
    private lateinit var swipelayout : SwipeRefreshLayout
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Undo back button when returning from BookDetailsFragment
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        val view = inflater.inflate(R.layout.fragment_book_feeds, container, false)
        recyclerViewBookFeeds = view.findViewById(R.id.rv_bookFeeds)

        navController = Navigation.findNavController( //Initialising navigation
            requireActivity(), R.id.booksNavHostFragment
        )

        //Handle swipe gesture
        swipelayout = view.findViewById(R.id.sl_bookFeeds)
        swipelayout.setOnRefreshListener {
            viewModel.refreshBookData()
            swipelayout.isRefreshing = false
        }

        //Referencing BookViewModel for Initialization
        viewModel = ViewModelProvider(requireActivity()).get(BookViewModel::class.java)
        viewModel.bookData.observe(this.viewLifecycleOwner, Observer {
            val adapter = BookRecyclerAdapter(requireContext(), it, this)
            recyclerViewBookFeeds.adapter = adapter
        })


        return view
    }


    //Handle click event
    override fun onBookItemClick(book: Book) {
        viewModel.selectedBookData.value = book
        navController.navigate(R.id.action_bookFeedsFragment_to_bookDetailsFragment)
    }


}