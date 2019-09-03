package com.sun.moviedb.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sun.moviedb.R
import com.sun.moviedb.base.BaseRecyclerAdapter
import com.sun.moviedb.data.dto.CastDTO
import com.sun.moviedb.data.dto.ProductionCompany
import com.sun.moviedb.data.entity.Movie
import com.sun.moviedb.databinding.ItemCastBinding
import com.sun.moviedb.databinding.ItemMovieBinding
import com.sun.moviedb.databinding.ItemProducerBinding

/**
 * Created by nguyenxuanhoi on 2019-08-16.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class ProducerAdapter : BaseRecyclerAdapter<ProductionCompany, ItemProducerBinding>(object : DiffUtil.ItemCallback<ProductionCompany>() {
    override fun areItemsTheSame(oldItem: ProductionCompany, newItem: ProductionCompany): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ProductionCompany, newItem: ProductionCompany): Boolean = oldItem == newItem

}) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_producer

}
