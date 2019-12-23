package com.app.zuludin.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.zuludin.common.Event
import com.app.zuludin.data.MoverRepository

open class BaseViewModel : ViewModel() {
    private val loadingProgress = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = loadingProgress

    protected val _snackBarError = MutableLiveData<Event<String>>()
    val snakBarError: LiveData<Event<String>> get() = _snackBarError

    fun showLoading(show: Boolean) {
        loadingProgress.value = show
    }
}