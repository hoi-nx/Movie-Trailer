package com.sun.moviedb.view.search

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sun.moviedb.R
import com.sun.moviedb.base.ViewModelBaseFragment
import com.sun.moviedb.databinding.FragmentSearchBinding
import com.sun.moviedb.view.adapter.MovieByGenreAdapter
import com.sun.moviedb.view.widget.SpaceItemDecoration
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit


/**
 * Created by nguyenxuanhoi on 2019-08-22.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class SearchFragment : ViewModelBaseFragment<SearchViewModel, FragmentSearchBinding>() {
    private lateinit var movieByGenreAdapter: MovieByGenreAdapter
    override val viewModel: SearchViewModel by viewModel()

    override val getContentViewId = R.layout.fragment_search

    override fun initializeView(savedInstanceState: Bundle?) {
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.viewModel = viewModel
        initTooBar(viewDataBinding.toolBar)

    }

    override fun initializeComponents() {
        movieByGenreAdapter = MovieByGenreAdapter {
            val directions = SearchFragmentDirections.actSearchToDetail(it)
            findNavController().navigate(directions)
        }
        recyclerViewMovie.apply {
            setHasFixedSize(true)
            addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(com.sun.moviedb.R.dimen.dp_4)))
            this.adapter = movieByGenreAdapter
        }
        viewModel.error.observe(this, Observer {
            handleBusinessException(it)
        })
    }

    @SuppressLint("CheckResult")
    override fun registerListeners() {
        RxSearch.fromSearchView(searchViewMovie)
            .debounce(300, TimeUnit.MILLISECONDS)
            .filter { it.length > 2 }
            .observeOn(AndroidSchedulers.mainThread()).subscribe { query ->
                query?.let {
                    viewModel.searchMovie(it)
                }
            }
    }

    object RxSearch {
        fun fromSearchView(searchView: SearchView): Observable<String> {
            val subject = PublishSubject.create<String>()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    subject.onComplete()
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isNotEmpty()) subject.onNext(newText)
                    return true
                }
            })
            return subject
        }
    }
}
