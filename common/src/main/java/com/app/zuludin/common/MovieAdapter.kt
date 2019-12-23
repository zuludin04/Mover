package com.app.zuludin.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.data.model.MovieResult

abstract class MovieAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(layoutResource(), parent, false)
        return buildViewHolder(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        setupView(holder, movieItems()[position])
    }

    override fun getItemCount(): Int {
        return movieItems().size
    }

    abstract fun setupView(holder: RecyclerView.ViewHolder, movie: MovieResult)

    abstract fun movieItems(): List<MovieResult>

    abstract fun buildViewHolder(view: View): VH

    abstract fun layoutResource(): Int

}
