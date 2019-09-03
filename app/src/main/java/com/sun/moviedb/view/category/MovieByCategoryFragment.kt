package com.sun.moviedb.view.category

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sun.moviedb.R
import com.sun.moviedb.base.ViewModelBaseFragment
import com.sun.moviedb.databinding.FragmentMovieByCategoryBinding
import com.sun.moviedb.view.adapter.MovieAdapter
import com.sun.moviedb.view.home.HomeFragmentDirections
import com.sun.moviedb.view.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_movie_by_category.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by nguyenxuanhoi on 2019-08-22.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class MovieByCategoryFragment :
    ViewModelBaseFragment<MovieByCategoryViewModel, FragmentMovieByCategoryBinding>() {

    private val queryType by lazy {
        arguments?.let {
            MovieByCategoryFragmentArgs.fromBundle(it).query
        }
    }
    override val viewModel: MovieByCategoryViewModel by viewModel()

    override val getContentViewId = R.layout.fragment_movie_by_category

    override fun initializeView(savedInstanceState: Bundle?) {
        viewDataBinding.query = queryType
        initTooBar(viewDataBinding.toolBar)

    }

    override fun initializeComponents() {
        val movieAdapter = MovieAdapter { movie ->
            val directions = MovieByCategoryFragmentDirections.actMovieByCategoryToDetail(movie)
            findNavController().navigate(directions)

        }
        recyclerViewMovie.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_4)))
            this.adapter = movieAdapter
        }
        viewModel.initQueryType(queryType)
        viewModel.movie.observe(this, Observer {
            handlerError(it)
            it.result?.let { movie ->
                movieAdapter.submitList(movie)
            }
        })
    }
}
