package com.app.zuludin.nowplaying.view

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agrawalsuneet.squareloaderspack.loaders.WaveLoader
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.data.utils.Resource
import com.app.zuludin.nowplaying.adapter.NowPlayingAdapter

object ListBinding {
    @BindingAdapter("app:showLoadingProgress")
    @JvmStatic
    fun showLoadingProgress(view: WaveLoader, status: Resource.Status?) {
        status?.let {
            view.visibility = if (it == Resource.Status.LOADING) View.VISIBLE else View.GONE
        }
    }

    @BindingAdapter("app:nowPlayingItems")
    @JvmStatic
    fun setNowPlayingItems(recyclerView: RecyclerView, results: Resource<List<MovieResult>>?) {
        with(recyclerView.adapter as NowPlayingAdapter) {
            results?.data?.let { movies -> addMovies(movies) }
        }
    }
}