package com.app.zuludin.common

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.data.model.MovieResult

abstract class MovieAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return buildViewHolder(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        setupView(holder, movieItems()[position])
    }

    override fun getItemCount(): Int {
        return movieItems().size
    }

    abstract fun setupView(holder: RecyclerView.ViewHolder, movie: MovieResult)

    abstract fun movieItems(): List<MovieResult>

    abstract fun buildViewHolder(parent: ViewGroup): VH

}
