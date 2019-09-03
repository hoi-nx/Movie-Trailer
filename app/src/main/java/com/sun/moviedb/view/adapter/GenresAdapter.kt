package com.sun.moviedb.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sun.moviedb.R
import com.sun.moviedb.base.BaseRecyclerAdapter
import com.sun.moviedb.data.entity.Genres
import com.sun.moviedb.databinding.ItemGenresBinding

/**
 * Created by nguyenxuanhoi on 2019-08-16.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class GenresAdapter(val onItemClicked: (genres: Genres) -> Unit) :
        BaseRecyclerAdapter<Genres, ItemGenresBinding>(object : DiffUtil.ItemCallback<Genres>() {
            override fun areItemsTheSame(oldItem: Genres, newItem: Genres): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Genres, newItem: Genres): Boolean = oldItem == newItem

        }) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_genres

    override fun bindFirstTime(binding: ItemGenresBinding) {
        binding.apply {
            buttonName.setOnClickListener {
                item.apply {
                    this?.let {
                        onItemClicked(it)
                    }
                }
            }
        }
    }
}
