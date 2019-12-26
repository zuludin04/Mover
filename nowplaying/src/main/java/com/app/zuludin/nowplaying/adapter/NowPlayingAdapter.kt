package com.app.zuludin.nowplaying.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.common.MovieAdapter
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.nowplaying.R
import com.app.zuludin.nowplaying.databinding.ItemNowPlayingBinding

class NowPlayingAdapter(private val movies: MutableList<MovieResult>) :
    MovieAdapter<NowPlayingViewHolder>() {
    override fun setupView(holder: RecyclerView.ViewHolder, movie: MovieResult) {
        if (holder is NowPlayingViewHolder) {
            holder.bind(movie)
        }
    }

    override fun movieItems(): List<MovieResult> = movies

    override fun buildViewHolder(parent: ViewGroup): NowPlayingViewHolder {
        val binding: ItemNowPlayingBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_now_playing,
            parent,
            false
        )
        return NowPlayingViewHolder(binding)
    }

    fun addMovies(list: List<MovieResult>) {
        movies.addAll(list)
        notifyDataSetChanged()
    }

}

class NowPlayingViewHolder(private val binding: ItemNowPlayingBinding) :
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