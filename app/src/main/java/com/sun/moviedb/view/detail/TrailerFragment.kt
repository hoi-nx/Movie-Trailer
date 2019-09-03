package com.sun.moviedb.view.detail

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sun.moviedb.R
import com.sun.moviedb.base.ViewModelBaseFragment
import com.sun.moviedb.constants.Constants
import com.sun.moviedb.data.dto.TrailerDTO
import com.sun.moviedb.data.dto.Videos
import com.sun.moviedb.databinding.FragmentProductBinding
import com.sun.moviedb.view.adapter.TrailerAdapter
import com.sun.moviedb.view.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_trailer.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by nguyenxuanhoi on 2019-08-25.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class TrailerFragment : ViewModelBaseFragment<DetailMovieViewModel, FragmentProductBinding>(){
    override val viewModel: DetailMovieViewModel by sharedViewModel(from ={ parentFragment!! })

    private lateinit var onTrailerClicked: (trailer: TrailerDTO) -> Unit

    fun setonTrailerClickListener(onButtonClicked: (trailer: TrailerDTO) -> Unit) {
        this.onTrailerClicked = onButtonClicked
    }
    private lateinit var onFirstTrailer: (trailer: String) -> Unit

    fun setOnFirstTrailerListener(onButtonClicked: (trailer: String) -> Unit) {
        this.onFirstTrailer = onButtonClicked
    }
    override val getContentViewId = R.layout.fragment_trailer

    override fun initializeView(savedInstanceState: Bundle?) {
    }

    override fun initializeComponents() {
        val castAdapter = TrailerAdapter{
            if(::onTrailerClicked.isInitialized){
                onTrailerClicked.invoke(it)
            }
        }
        recyclerTrailer.apply {
            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_4)))
            this.adapter = castAdapter
        }
        viewModel.detailMovie.observe(this, Observer {
            Log.d("TAG","Obser")
            it?.videos?.let {videos->
                castAdapter.submitList(videos.results)
                if(::onFirstTrailer.isInitialized) onFirstTrailer.invoke(videos.results[0].key)
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance()=TrailerFragment()
    }
    }



