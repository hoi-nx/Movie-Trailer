package com.sun.moviedb.base

import android.os.Bundle

interface IBaseViewMain {
    val getContentViewId: Int
    fun initializeView(savedInstanceState: Bundle?)
    fun initializeComponents()
}
