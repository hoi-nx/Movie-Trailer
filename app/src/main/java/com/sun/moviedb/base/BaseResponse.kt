package com.sun.moviedb.base

data class BaseResponse<T>(var result: T?, var error: Exception?,
                           val status: Status
) {
    companion object {
        fun <T> success(data: T): BaseResponse<T> {
            return BaseResponse(data, null, Status.SUCCESS)
        }

        fun <T> error(error: Exception, data: T?): BaseResponse<T> {
            return BaseResponse(data, error, Status.ERROR)
        }

        fun <T> loading(data: T?): BaseResponse<T> {
            return BaseResponse(data, null, Status.LOADING)
        }
    }
}
