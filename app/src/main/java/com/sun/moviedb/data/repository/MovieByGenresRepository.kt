package com.sun.moviedb.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.sun.moviedb.base.BaseResponse
import com.sun.moviedb.base.ContextProviders
import com.sun.moviedb.base.NetworkBoundResource
import com.sun.moviedb.data.dao.MovieDao
import com.sun.moviedb.data.dao.MovieGenresJoinDao
import com.sun.moviedb.data.dto.CategoryDTO
import com.sun.moviedb.data.dto.MovieDto
import com.sun.moviedb.data.entity.Movie
import com.sun.moviedb.data.entity.MovieGenresJoin
import com.sun.moviedb.data.remote.RemoteDataSource
import com.sun.moviedb.utils.CategoryQuery
import io.reactivex.Single

/**
 * Created by nguyenxuanhoi on 2019-08-20.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class MovieByGenresRepository(private val remoteDataSource: RemoteDataSource) {
    private val _isLoading = MediatorLiveData<Boolean>()
    val isloading: LiveData<Boolean>
        get() = _isLoading

    fun getMovieByGenres(idGenres: Int, page: Int): Single<MovieDto> {
        return Single.create { emmiter ->
            remoteDataSource.getMoviesByGenre(idGenres, page).subscribe({
                emmiter.onSuccess(it)
                _isLoading.postValue(true)
            }, {
                emmiter.onError(it)
            })
        }
    }

}
