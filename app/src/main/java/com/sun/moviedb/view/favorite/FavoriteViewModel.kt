package com.sun.moviedb.view.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.sun.moviedb.data.repository.DetailMovieRepository

/**
 * Created by nguyenxuanhoi on 2019-08-26.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class FavoriteViewModel(
    application: Application,
    private val reponsitory: DetailMovieRepository
) : AndroidViewModel(application) {
    val movies by lazy {
        reponsitory.getFavoriteMovies()
    }
}