package com.app.zuludin.detail

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.zuludin.data.model.MovieDetailResponse
import com.bumptech.glide.Glide
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object CustomBinding {

    @BindingAdapter("app:imageFromUrl")
    @JvmStatic
    fun setImageFromUrl(image: ImageView, url: String?) {
        if (url != null) {
            Glide.with(image.context)
                .load("https://image.tmdb.org/t/p/w500$url")
                .into(image)
        }
    }

    @SuppressLint("SetTextI18n")
    @BindingAdapter("app:movieDate")
    @JvmStatic
    fun setMovieDate(text: TextView, detail: MovieDetailResponse?) {
        detail?.let {
            text.text = "${detail.runtime} min - ${parseDate(it.releaseDate)}"
        }
    }

    private fun parseDate(movieDate: String?): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val newFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        var outputDate = ""

        try {
            val date: Date = originalFormat.parse(movieDate!!)!!
            outputDate = newFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return outputDate
    }
}