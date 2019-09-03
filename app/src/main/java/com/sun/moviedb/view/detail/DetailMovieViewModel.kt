package com.sun.moviedb.view.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sun.moviedb.data.dto.DetailMovieDTO
import com.sun.moviedb.data.repository.DetailMovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by nguyenxuanhoi on 2019-08-25.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class DetailMovieViewModel(
    application: Application,
    private val reponsitory: DetailMovieRepository
) : AndroidViewModel(application) {

    private val movieId = MutableLiveData<Int>()
    val detailMovie = MutableLiveData<DetailMovieDTO>()
    val error = MutableLiveData<Throwable>()

    fun initData(movieId: Int) {
        this.movieId.value = movieId
    }

    val isFavorite: LiveData<Boolean> = Transformations.switchMap(movieId) { id ->
        reponsitory.isFavoriteMovie(id)
    }

    fun toggleFavorite(movieId: Int) {
        isFavorite.value?.let {
            reponsitory.toggleFavoriteMovie(movieId, it)
        }
    }

    fun getMovieDetailDto() {
        movieId.value?.let {
            reponsitory.getDetailMovie(it).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ detailMovie.value = it }, { error.value = it })
        }
    }
}
