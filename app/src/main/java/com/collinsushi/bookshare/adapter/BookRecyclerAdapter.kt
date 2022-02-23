package com.collinsushi.bookshare.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.collinsushi.bookshare.R
import com.collinsushi.bookshare.model.Book

class BookRecyclerAdapter (
    private val context: Context,
    private val books: List<Book>,
    private val itemListener: BookItemListener):
    RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val bookTitle: TextView = itemView.findViewById(R.id.tv_bookTitle)
        val bookImage: ImageView = itemView.findViewById(R.id.iv_bookImage)
        val bookRating: RatingBar = itemView.findViewById(R.id.rb_bookRating)

    }


    //Counts the numbers of items in the data collection
    override fun getItemCount() = books.size

    //Create a Layout view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_feed_grid_item, parent, false)
        return ViewHolder(view)
    }

    //Bind data to the view holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val book = books[position]  //binding a single data (i.e book).

        //Assign values to each of the view objects in the holder.
        with(holder){
            bookTitle?.let {
                it.text = book.title
                it.contentDescription = book.title
            }

            bookRating?.rating = book.rating.toFloat()
            Glide.with(context)
                .load(book.thumbnailUrl)
                .into(bookImage)

            holder.itemView.setOnClickListener {
                itemListener.onBookItemClick(book)
            }
        }
    }

    //Handle Recyclerview click event
    interface BookItemListener{
        fun onBookItemClick(book: Book)
    }

}