package com.app.zuludin.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.common.MovieAdapter
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.search.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_search_result.view.*

class SearchResultAdapter(private val results: MutableList<MovieResult>) : MovieAdapter<SearchViewHolder>() {
    override fun setupView(holder: RecyclerView.ViewHolder, movie: MovieResult) {
        if (holder is SearchViewHolder) holder.bind(movie)
    }

    override fun movieItems(): List<MovieResult> = results

    override fun buildViewHolder(view: View): SearchViewHolder =
        SearchViewHolder(view)

    override fun layoutResource(): Int =
        R.layout.item_search_result

    fun addMovies(list: List<MovieResult>) {
        results.clear()
        results.addAll(list)
        notifyDataSetChanged()
    }
}

class SearchViewHolder(view: View): RecyclerView.ViewHolder(view) {
    fun bind(result: MovieResult) {
        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500${result.posterPath}")
            .into(itemView.search_image)
        itemView.search_title.text = result.title
        itemView.search_date.text = result.releaseDate
        itemView.search_overview.text = result.overview
    }
}