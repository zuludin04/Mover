package com.app.zuludin.popular.view

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agrawalsuneet.squareloaderspack.loaders.WaveLoader
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.data.utils.Resource
import com.app.zuludin.popular.adapter.PopularAdapter

object ListBinding {
    @BindingAdapter("app:showLoadingProgress")
    @JvmStatic
    fun showLoadingProgress(view: WaveLoader, status: Resource.Status?) {
        status?.let {
            view.visibility = if (it == Resource.Status.LOADING) View.VISIBLE else View.GONE
        }
    }

    @BindingAdapter("app:popularItems")
    @JvmStatic
    fun setPopularItems(recyclerView: RecyclerView, results: Resource<List<MovieResult>>?) {
        with(recyclerView.adapter as PopularAdapter) {
            results?.data?.let { addMovies(it) }
        }
    }
}