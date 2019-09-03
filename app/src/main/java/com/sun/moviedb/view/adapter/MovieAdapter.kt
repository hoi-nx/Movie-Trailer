package com.sun.moviedb.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sun.moviedb.R
import com.sun.moviedb.base.BaseRecyclerAdapter
import com.sun.moviedb.data.entity.Movie
import com.sun.moviedb.databinding.ItemMovieBinding

/**
 * Created by nguyenxuanhoi on 2019-08-16.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class MovieAdapter(val onItemClicked: (movie: Movie) -> Unit) : BaseRecyclerAdapter<Movie, ItemMovieBinding>(object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem

}) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_movie

    override fun bindFirstTime(binding: ItemMovieBinding) {
        //  ViewCompat.setTransitionName(binding.cardView, "poster")
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
