package com.sun.moviedb.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sun.moviedb.R
import com.sun.moviedb.base.BaseRecyclerAdapter
import com.sun.moviedb.data.dto.CastDTO
import com.sun.moviedb.data.dto.TrailerDTO
import com.sun.moviedb.data.entity.Movie
import com.sun.moviedb.databinding.ItemCastBinding
import com.sun.moviedb.databinding.ItemMovieBinding
import com.sun.moviedb.databinding.ItemTrailerBinding

/**
 * Created by nguyenxuanhoi on 2019-08-16.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class CastAdapter(val onItemClicked: (cast: CastDTO) -> Unit) :
    BaseRecyclerAdapter<CastDTO, ItemCastBinding>(object : DiffUtil.ItemCallback<CastDTO>() {
        override fun areItemsTheSame(oldItem: CastDTO, newItem: CastDTO): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CastDTO, newItem: CastDTO): Boolean =
            oldItem == newItem

    }) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_cast
    override fun bindFirstTime(binding: ItemCastBinding) {
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
