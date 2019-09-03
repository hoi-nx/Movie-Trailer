package com.sun.moviedb.view.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sun.moviedb.R
import com.sun.moviedb.base.ViewModelBaseFragment
import com.sun.moviedb.data.dto.CastDTO
import com.sun.moviedb.databinding.FragmentProductBinding
import com.sun.moviedb.view.adapter.CastAdapter
import com.sun.moviedb.view.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_actor.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * Created by nguyenxuanhoi on 2019-08-25.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class CastFragment : ViewModelBaseFragment<DetailMovieViewModel, FragmentProductBinding>(){
    private lateinit var onCasterClick: (caster: CastDTO) -> Unit

    fun setOnCasterClickListener(casterClick: (caster: CastDTO) -> Unit) {
        this.onCasterClick = casterClick
    }
    override val viewModel: DetailMovieViewModel by sharedViewModel(from ={ parentFragment!! })

    override val getContentViewId = R.layout.fragment_actor

    override fun initializeView(savedInstanceState: Bundle?) {
        val castAdapter =CastAdapter{
            if(::onCasterClick.isInitialized) onCasterClick.invoke(it)
        }
        recyclerCast.apply {
            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_4)))
            this.adapter = castAdapter
        }
        viewModel.detailMovie.observe(this, Observer {
            it?.credits?.let {
                castAdapter.submitList(it.cast)
            }
        })
    }

    override fun initializeComponents() {

    }
    companion object {
        @JvmStatic
        fun newInstance()=CastFragment()

    }

}