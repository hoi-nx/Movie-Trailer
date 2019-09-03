package com.sun.moviedb.view.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sun.moviedb.base.BaseResponse
import com.sun.moviedb.data.dto.CategoryDTO
import com.sun.moviedb.data.entity.Genres
import com.sun.moviedb.data.entity.Movie
import com.sun.moviedb.data.repository.HomeRepository
import com.sun.moviedb.data.repository.MovieByCategoryRepository
import com.sun.moviedb.utils.CategoryQuery

/**
 * Created by nguyenxuanhoi on 2019-08-07.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class HomeViewModel(
        application: Application,
        private val homeRepository: HomeRepository,
        private val reponsitory: MovieByCategoryRepository)
    : AndroidViewModel(application) {

    private val _genres = homeRepository.getAllGenres()
    val genres: LiveData<BaseResponse<List<Genres>>>
        get() = _genres

    companion object {
        private val FIRST_PAGE = 1
    }

    private val _categories by lazy {
        reponsitory.getQueryTypeCategory()
    }

    fun getCategories(): List<CategoryDTO> {
        return _categories
    }

    private val _moviesPopular: LiveData<BaseResponse<List<Movie>>> by lazy {
        reponsitory.getMoviesByCategory(CategoryQuery.POPULAR, FIRST_PAGE)
    }
    val moviesPopular: LiveData<BaseResponse<List<Movie>>>
        get() = _moviesPopular

    private val _moviesUpComing: LiveData<BaseResponse<List<Movie>>> by lazy {
        reponsitory.getMoviesByCategory(CategoryQuery.UP_COMING, FIRST_PAGE)
    }
    val moviesUpComming: LiveData<BaseResponse<List<Movie>>>
        get() = _moviesUpComing

    private val _moviesNowPlaying: LiveData<BaseResponse<List<Movie>>> by lazy {
        reponsitory.getMoviesByCategory(CategoryQuery.NOW_PLAYING, FIRST_PAGE)
    }
    val moviesNowPlaying: LiveData<BaseResponse<List<Movie>>>
        get() = _moviesNowPlaying

    private val _moviesTopRate: LiveData<BaseResponse<List<Movie>>> by lazy {
        reponsitory.getMoviesByCategory(CategoryQuery.TOP_RATE, FIRST_PAGE)
    }
    val movieTopRate: LiveData<BaseResponse<List<Movie>>>
        get() = _moviesTopRate

    override fun onCleared() {
        super.onCleared()
        homeRepository.coroutines.onClear()
    }
}
