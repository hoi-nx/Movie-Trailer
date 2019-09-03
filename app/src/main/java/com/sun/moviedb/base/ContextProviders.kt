package com.sun.moviedb.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

class ContextProviders {
    private val viewModelJob: Job? by lazy { Job() }

    val viewModelScopeMain: CoroutineScope? by lazy {
        CoroutineScope(Dispatchers.Main + viewModelJob as Job)
    }

    val viewModelScopeIO: CoroutineScope? by lazy {
        CoroutineScope(Dispatchers.IO + viewModelJob as Job)
    }

    fun onClear() {
        viewModelScopeMain?.cancel()
        viewModelScopeIO?.cancel()
    }
}
