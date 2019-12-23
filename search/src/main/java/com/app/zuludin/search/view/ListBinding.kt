package com.app.zuludin.search.view

import android.view.View
import androidx.databinding.BindingAdapter
import com.agrawalsuneet.squareloaderspack.loaders.WaveLoader
import com.app.zuludin.data.utils.Resource

object ListBinding {
    @BindingAdapter("app:showLoadingProgress")
    @JvmStatic
    fun showLoadingProgress(view: WaveLoader, status: Resource.Status?) {
        status?.let {
            view.visibility = if (it == Resource.Status.LOADING) View.VISIBLE else View.GONE
        }
    }
}