package com.app.zuludin.popular.adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.common.MovieAdapter
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.popular.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_popular.view.*

class PopularAdapter(private val movies: MutableList<MovieResult>) :
    MovieAdapter<PopularViewHolder>() {
    override fun setupView(holder: RecyclerView.ViewHolder, movie: MovieResult) {
        if (holder is PopularViewHolder) holder.bind(movie)
    }

    override fun movieItems(): List<MovieResult> = movies

    override fun buildViewHolder(view: View): PopularViewHolder =
        PopularViewHolder(view)

    override fun layoutResource(): Int =
        R.layout.item_popular

    fun addMovies(list: List<MovieResult>) {
        movies.addAll(list)
        notifyDataSetChanged()
    }
}

class PopularViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(movie: MovieResult) {
        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(itemView.movie_poster)
        itemView.movie_title.text = movie.title
        itemView.movie_rating.text = "${movie.voteAverage}"
        itemView.movie_release.text = movie.releaseDate

        itemView.setOnClickListener {
            val intent: Intent?
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