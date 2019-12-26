package com.app.zuludin.upcoming.view

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agrawalsuneet.squareloaderspack.loaders.WaveLoader
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.data.utils.Resource
import com.app.zuludin.upcoming.adapter.UpcomingAdapter

object ListBinding {
    @BindingAdapter("app:showLoadingProgress")
    @JvmStatic
    fun showLoadingProgress(view: WaveLoader, status: Resource.Status?) {
        status?.let {
            view.visibility = if (it == Resource.Status.LOADING) View.VISIBLE else View.GONE
        }
    }

    @BindingAdapter("app:upcomingItems")
    @JvmStatic
    fun setUpcomingItems(recyclerView: RecyclerView, result: Resource<List<MovieResult>>?) {
        with(recyclerView.adapter as UpcomingAdapter) {
            result?.data?.let { addMovies(it) }
        }
    }
}