package com.sun.moviedb.view.adapter

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.sun.moviedb.R
import com.sun.moviedb.base.BaseRecyclerAdapter
import com.sun.moviedb.data.dto.CategoryDTO
import com.sun.moviedb.data.entity.Movie
import com.sun.moviedb.databinding.ItemHomeMovieBinding
import com.sun.moviedb.view.widget.SpaceItemDecoration

/**
 * Created by nguyenxuanhoi on 2019-08-16.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class HomeAdapter(
        val onMoreClicked: (category: CategoryDTO) -> Unit,
        val onItemClicked: (movie: Movie) -> Unit
) :
        BaseRecyclerAdapter<CategoryDTO, ItemHomeMovieBinding>(object :
                DiffUtil.ItemCallback<CategoryDTO>() {
            override fun areContentsTheSame(oldItem: CategoryDTO, newItem: CategoryDTO) =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: CategoryDTO, newItem: CategoryDTO) =
                    oldItem.queryType == newItem.queryType

        }) {

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_home_movie

    override fun bindFirstTime(binding: ItemHomeMovieBinding) {
        val movieHomeAdapter = MovieAdapter { movie ->
            onItemClicked(movie)
        }
        binding.apply {
            recyclerMovie.apply {
                addItemDecoration(SpaceItemDecoration(context.resources.getDimensionPixelSize(R.dimen.dp_4)))
                setHasFixedSize(true)
                adapter = movieHomeAdapter
            }
            textMore.setOnClickListener {
                item.apply {
                    this?.let {
                        onMoreClicked(it)
                    }
                }
            }
        }
    }
}
