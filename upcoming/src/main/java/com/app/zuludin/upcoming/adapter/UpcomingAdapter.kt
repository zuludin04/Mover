package com.app.zuludin.upcoming.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.common.MovieAdapter
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.upcoming.R
import com.app.zuludin.upcoming.databinding.ItemUpcomingBinding

class UpcomingAdapter(private val movies: MutableList<MovieResult>) :
    MovieAdapter<UpcomingViewHolder>() {
    override fun setupView(holder: RecyclerView.ViewHolder, movie: MovieResult) {
        if (holder is UpcomingViewHolder) {
            holder.bind(movie)
        }
    }

    override fun buildViewHolder(parent: ViewGroup): UpcomingViewHolder {
        val binding: ItemUpcomingBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_upcoming,
            parent,
            false
        )
        return UpcomingViewHolder(binding)
    }

    override fun movieItems(): List<MovieResult> {
        return movies
    }

    fun addMovies(list: List<MovieResult>) {
        movies.addAll(list)
        notifyDataSetChanged()
    }
}

class UpcomingViewHolder(private val binding: ItemUpcomingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieResult) {
        binding.movie = movie

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