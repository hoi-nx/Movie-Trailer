package com.sun.moviedb.view.detail

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sun.moviedb.R
import com.sun.moviedb.base.BaseFragment
import com.sun.moviedb.base.ViewModelBaseFragment
import com.sun.moviedb.constants.Constants
import com.sun.moviedb.data.dto.DetailMovieDTO
import com.sun.moviedb.databinding.FragmentProductBinding
import com.sun.moviedb.view.adapter.ProducerAdapter
import com.sun.moviedb.view.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_product.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by nguyenxuanhoi on 2019-08-25.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class ProductFragment:ViewModelBaseFragment<DetailMovieViewModel,FragmentProductBinding>(){
    override val viewModel: DetailMovieViewModel by sharedViewModel(from ={ parentFragment!! })

    override val getContentViewId = R.layout.fragment_product

    override fun initializeView(savedInstanceState: Bundle?) {
    }

    override fun initializeComponents() {
        val producerAdapter = ProducerAdapter()
        recyclerProducer.apply {
            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_4)))
            this.adapter = producerAdapter
        }
        viewModel.detailMovie.observe(this, Observer {
            it?.let {detail->
                producerAdapter.submitList(detail.productionCompany)
            }
        })
    }
    companion object {
        @JvmStatic
        fun newInstance():ProductFragment=ProductFragment()

    }

}