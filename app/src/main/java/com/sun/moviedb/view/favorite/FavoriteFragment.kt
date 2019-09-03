package com.sun.moviedb.view.favorite

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sun.moviedb.R
import com.sun.moviedb.base.ViewModelBaseFragment
import com.sun.moviedb.databinding.FragmentFavoriteBinding
import com.sun.moviedb.view.adapter.MovieByGenreAdapter
import com.sun.moviedb.view.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by nguyenxuanhoi on 2019-08-26.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class FavoriteFragment : ViewModelBaseFragment<FavoriteViewModel, FragmentFavoriteBinding>() {
    override val viewModel: FavoriteViewModel by viewModel()
    override val getContentViewId = R.layout.fragment_favorite

    override fun initializeView(savedInstanceState: Bundle?) {
    }

    override fun initializeComponents() {
        val movieByGenreAdapter = MovieByGenreAdapter {
            val directions = FavoriteFragmentDirections.actionFavoriteToDetail(it)
            findNavController().navigate(directions)

        }
        recyclerFavorite.apply {
            addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_4)))
            this.adapter = movieByGenreAdapter
        }
        viewModel.movies.observe(this, Observer { result ->
            result?.let {
                it.result?.let {
                    movieByGenreAdapter.submitList(it)
                }
            }

        })

    }

}