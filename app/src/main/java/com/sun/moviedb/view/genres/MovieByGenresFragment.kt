package com.sun.moviedb.view.genres

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sun.moviedb.R
import com.sun.moviedb.base.ViewModelBaseFragment
import com.sun.moviedb.databinding.FragmentMovieByGenresBinding
import com.sun.moviedb.view.adapter.MovieByGenreAdapter
import com.sun.moviedb.view.category.MovieByCategoryFragmentDirections
import com.sun.moviedb.view.widget.SpaceItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movie_by_genres.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by nguyenxuanhoi on 2019-08-22.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class MovieByGenresFragment :
    ViewModelBaseFragment<MovieByGenresViewModel, FragmentMovieByGenresBinding>() {
    private val genres by lazy {
        arguments?.let {
            MovieByGenresFragmentArgs.fromBundle(it).genres
        }
    }
    private lateinit var movieByGenreAdapter: MovieByGenreAdapter

    override val viewModel: MovieByGenresViewModel by viewModel()
    override val getContentViewId = R.layout.fragment_movie_by_genres

    override fun initializeView(savedInstanceState: Bundle?) {
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.viewModel = viewModel
        initTooBar(viewDataBinding.toolBar)


    }

    override fun initializeComponents() {
        movieByGenreAdapter = MovieByGenreAdapter {
            val directions = MovieByGenresFragmentDirections.actMovieByGenresToDetail(it)
            findNavController().navigate(directions)
        }
        genres?.let {
            viewDataBinding.toolBar.title = it.name
            viewDataBinding.genres = it.id
            viewModel.getMovieByGenres(it.id)
        }

        recyclerViewMovieByGenre.apply {
            addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_4)))
            this.adapter = movieByGenreAdapter
        }

    }
}
