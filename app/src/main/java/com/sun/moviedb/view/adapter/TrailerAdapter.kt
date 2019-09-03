package com.sun.moviedb.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sun.moviedb.R
import com.sun.moviedb.base.BaseRecyclerAdapter
import com.sun.moviedb.data.dto.CastDTO
import com.sun.moviedb.data.dto.TrailerDTO
import com.sun.moviedb.data.entity.Movie
import com.sun.moviedb.databinding.ItemGenresBinding
import com.sun.moviedb.databinding.ItemTrailerBinding

/**
 * Created by nguyenxuanhoi on 2019-08-16.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class TrailerAdapter(val onItemClicked: (trailer: TrailerDTO) -> Unit) :
    BaseRecyclerAdapter<TrailerDTO, ItemTrailerBinding>(object :
        DiffUtil.ItemCallback<TrailerDTO>() {
        override fun areItemsTheSame(oldItem: TrailerDTO, newItem: TrailerDTO): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TrailerDTO, newItem: TrailerDTO): Boolean =
            oldItem == newItem

    }) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_trailer
    override fun bindFirstTime(binding: ItemTrailerBinding) {
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
