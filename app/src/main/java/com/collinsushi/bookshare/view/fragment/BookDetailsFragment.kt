package com.collinsushi.bookshare.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.collinsushi.bookshare.R
import com.collinsushi.bookshare.databinding.FragmentBookDetailsBinding
import com.collinsushi.bookshare.viewModel.BookViewModel

class BookDetailsFragment : Fragment (){
    private lateinit var viewModel: BookViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)
        navController = Navigation.findNavController(
            requireActivity(), R.id.booksNavHostFragment
        )

        viewModel = ViewModelProvider(requireActivity()).get(BookViewModel::class.java)


        /*
        Inflating the generated FragmentBookDetailsBinding class
         :generated after using data binding in fragment_book_details layout
         */
        val binding = FragmentBookDetailsBinding.inflate( inflater, container, false)

        //update the binding as needed using lifecycleOwner
        binding.lifecycleOwner = this
        binding.bookViewModel = viewModel

        return binding.root

        //return inflater.inflate(R.layout.fragment_book_details, container, false)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == android.R.id.home){
                navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

}