package com.app.zuludin.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.common.MovieAdapter
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.search.R
import com.app.zuludin.search.databinding.ItemSearchResultBinding

class SearchResultAdapter(private val results: MutableList<MovieResult>) :
    MovieAdapter<SearchViewHolder>() {
    override fun setupView(holder: RecyclerView.ViewHolder, movie: MovieResult) {
        if (holder is SearchViewHolder) holder.bind(movie)
    }

    override fun movieItems(): List<MovieResult> = results

    override fun buildViewHolder(parent: ViewGroup): SearchViewHolder {
        val binding: ItemSearchResultBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_search_result,
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    fun addMovies(list: List<MovieResult>) {
        results.clear()
        results.addAll(list)
        notifyDataSetChanged()
    }
}

class SearchViewHolder(private val binding: ItemSearchResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(result: MovieResult) {
        binding.movie = result
    }
}