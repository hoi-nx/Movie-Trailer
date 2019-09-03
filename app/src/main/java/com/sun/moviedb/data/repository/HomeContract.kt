package com.sun.moviedb.data.repository

import androidx.lifecycle.LiveData
import com.sun.moviedb.base.BaseResponse
import com.sun.moviedb.data.entity.Genres

/**
 * Created by nguyenxuanhoi on 2019-08-15.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
interface HomeContract {
    fun getAllGenres(): LiveData<BaseResponse<List<Genres>>>

}