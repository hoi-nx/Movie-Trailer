package com.sun.moviedb.view.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sun.moviedb.base.BaseResponse
import com.sun.moviedb.data.dto.MovieDto
import com.sun.moviedb.data.repository.MovieByCategoryRepository
import com.sun.moviedb.utils.CategoryQuery
import io.reactivex.Single

/**
 * Created by nguyenxuanhoi on 2019-08-23.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class MovieByCategoryViewModel(
        application: Application,
        private val repository: MovieByCategoryRepository
) : AndroidViewModel(application) {

    private val _queryType = MutableLiveData<String>()
    val queryType: LiveData<String>
        get() = _queryType

    val movie = Transformations.switchMap(_queryType) {
        it?.let {
            repository.getMoviesByCategory(queryType = it, page = FIRST_PAGE)
        }
    }

    fun initQueryType(query: String?) {
        query?.let {
            _queryType.value = it
        }
    }

    companion object {
        private val FIRST_PAGE = 1
    }

}
