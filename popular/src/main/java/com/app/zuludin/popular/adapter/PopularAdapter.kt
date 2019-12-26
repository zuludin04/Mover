package com.app.zuludin.popular.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.common.MovieAdapter
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.popular.R
import com.app.zuludin.popular.databinding.ItemPopularBinding

class PopularAdapter(private val movies: MutableList<MovieResult>) :
    MovieAdapter<PopularViewHolder>() {
    override fun setupView(holder: RecyclerView.ViewHolder, movie: MovieResult) {
        if (holder is PopularViewHolder) holder.bind(movie)
    }

    override fun movieItems(): List<MovieResult> = movies

    override fun buildViewHolder(parent: ViewGroup): PopularViewHolder {
        val binding: ItemPopularBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_popular,
            parent,
            false
        )
        return PopularViewHolder(binding)
    }

    fun addMovies(list: List<MovieResult>) {
        movies.addAll(list)
        notifyDataSetChanged()
    }
}

class PopularViewHolder(private val binding: ItemPopularBinding) :
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