package com.sun.moviedb.view.genres

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sun.moviedb.base.BaseResponse
import com.sun.moviedb.data.dto.MovieDto
import com.sun.moviedb.data.repository.MovieByCategoryRepository
import com.sun.moviedb.data.repository.MovieByGenresRepository
import com.sun.moviedb.utils.CategoryQuery
import com.sun.moviedb.view.category.MovieByCategoryViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by nguyenxuanhoi on 2019-08-23.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class MovieByGenresViewModel(
    application: Application,
    private val repository: MovieByGenresRepository
) : AndroidViewModel(application) {

    private val _movies = MutableLiveData<MovieDto>()
    val movie
        get() = _movies
    private var _error = MutableLiveData<Throwable>()
    val error
        get() = _error
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading
        get() = _isLoading
    private val _idGenen = MutableLiveData<Int>()

    @SuppressLint("CheckResult")
    fun getMovieByGenres(idGenen:Int) {
            _isLoading.value=true
            repository.getMovieByGenres(idGenen, FIRST_PAGE).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _movies.value = it
                    _isLoading.value = false
                }, {
                    _error.value = it
                    _isLoading.value = false
                })


    }
    companion object {
        private val FIRST_PAGE = 1
    }

}
