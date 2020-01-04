package com.app.zuludin.popular.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agrawalsuneet.squareloaderspack.loaders.WaveLoader
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.data.utils.Resource
import com.app.zuludin.popular.adapter.PopularAdapter
import com.bumptech.glide.Glide
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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

    @BindingAdapter("app:loadImageUrl")
    @JvmStatic
    fun setLoadImageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load("https://image.tmdb.org/t/p/w500$url")
            .into(imageView)
    }

    @BindingAdapter("app:parseMovieDate")
    @JvmStatic
    fun setParseMovieDate(textView: TextView, date: String?) {
        date?.let {
            textView.text = parseDate(it)
        }
    }

    private fun parseDate(movieDate: String): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val newFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        var outputDate = ""

        try {
            val date: Date = originalFormat.parse(movieDate)!!
            outputDate = newFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return outputDate
    }
}