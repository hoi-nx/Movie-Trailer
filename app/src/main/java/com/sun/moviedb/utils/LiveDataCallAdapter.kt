package com.sun.moviedb.utils

import androidx.lifecycle.LiveData
import com.sun.moviedb.base.BaseResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapter<R>(private val responseType: Type) : CallAdapter<R, LiveData<BaseResponse<R>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): LiveData<BaseResponse<R>> {
        return object : LiveData<BaseResponse<R>>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            response.body()?.let {
                                postValue(BaseResponse.success(it))
                            }

                        }
                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(BaseResponse.error(throwable as Exception,null))
                        }
                    })
                }
            }
        }
    }
}
