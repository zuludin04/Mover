package com.app.zuludin.detail

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

object CustomBinding {

    @BindingAdapter("app:imageFromUrl")
    @JvmStatic
    fun setImageFromUrl(image: ImageView, url: String?) {
        val circularProgressDrawable = CircularProgressDrawable(image.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        if (url != null) {
            Glide.with(image.context)
                .load("https://image.tmdb.org/t/p/w500$url")
                .placeholder(circularProgressDrawable)
                .into(image)
        }
    }
}