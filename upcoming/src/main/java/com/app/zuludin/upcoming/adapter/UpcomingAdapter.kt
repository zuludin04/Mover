package com.app.zuludin.upcoming.adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.common.MovieAdapter
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.upcoming.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_upcoming.view.*

class UpcomingAdapter(private val movies: MutableList<MovieResult>) : MovieAdapter<UpcomingViewHolder>() {
    override fun setupView(holder: RecyclerView.ViewHolder, movie: MovieResult) {
        if (holder is UpcomingViewHolder) {
            holder.bind(movie)
        }
    }

    override fun buildViewHolder(view: View): UpcomingViewHolder {
        return UpcomingViewHolder(view)
    }

    override fun layoutResource(): Int {
        return R.layout.item_upcoming
    }

    override fun movieItems(): List<MovieResult> {
        return movies
    }

    fun addMovies(list: List<MovieResult>?) {
        list?.let { movies.addAll(it) }
        notifyDataSetChanged()
    }
}

class UpcomingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(movie: MovieResult) {
        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(itemView.movie_poster)
        itemView.movie_title.text = movie.title
        itemView.movie_release.text = movie.releaseDate

        itemView.setOnClickListener {
            var intent: Intent? = null
            try {
                intent = Intent(
                    itemView.context,
                    Class.forName("com.app.zuludin.detail.MovieDetailActivity")
                )
                intent.putExtra("MOVIE_ID", movie.id)
                itemView.context.startActivity(intent)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}