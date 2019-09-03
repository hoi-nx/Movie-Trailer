package com.sun.moviedb.data.repository

import androidx.lifecycle.LiveData
import com.sun.moviedb.base.BaseResponse
import com.sun.moviedb.data.dto.CategoryDTO
import com.sun.moviedb.data.entity.Movie

/**
 * Created by nguyenxuanhoi on 2019-08-20.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
interface MovieDataSource {
    fun getQueryTypeCategory(): List<CategoryDTO>
    fun getMoviesByCategory(queryType: String, page: Int): LiveData<BaseResponse<List<Movie>>>
}
