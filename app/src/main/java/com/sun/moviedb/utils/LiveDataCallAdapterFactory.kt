package com.sun.moviedb.utils

import androidx.lifecycle.LiveData
import com.sun.moviedb.base.BaseResponse
import retrofit2.CallAdapter
import retrofit2.CallAdapter.Factory
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory : Factory() {
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = getRawType(observableType)
        when {
            rawObservableType != BaseResponse::class.java -> throw IllegalArgumentException("type must be a resource")
            observableType !is ParameterizedType -> throw IllegalArgumentException("resource must be parameterized")
            else -> {
                val bodyType = getParameterUpperBound(0, observableType)
                return LiveDataCallAdapter<Any>(bodyType)
            }
        }
    }
}
