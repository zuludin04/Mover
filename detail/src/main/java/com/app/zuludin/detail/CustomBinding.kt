package com.app.zuludin.detail

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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
}