package com.sun.moviedb.view.adapter

import android.widget.BaseAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sun.moviedb.R
import com.sun.moviedb.base.BaseRecyclerAdapter
import com.sun.moviedb.data.entity.Movie
import com.sun.moviedb.databinding.ItemMovieBinding
import com.sun.moviedb.databinding.ItemRecyclerCategoryLayoutBinding

/**
 * Created by nguyenxuanhoi on 2019-08-25.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class MovieByGenreAdapter(val onItemClicked: (movie: Movie) -> Unit) :
        BaseRecyclerAdapter<Movie, ItemRecyclerCategoryLayoutBinding>(object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem

        }) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_recycler_category_layout
    override fun bindFirstTime(binding: ItemRecyclerCategoryLayoutBinding) {
        binding.apply {
            cardView.setOnClickListener {
                item.apply {
                    this?.let {
                        onItemClicked(it)
                    }
                }
            }
        }
    }
}