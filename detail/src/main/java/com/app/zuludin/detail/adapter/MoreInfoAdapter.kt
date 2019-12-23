package com.app.zuludin.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.data.model.CastItem
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.data.model.ProductionCompaniesItem
import com.app.zuludin.detail.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_cast_movie.view.*
import kotlinx.android.synthetic.main.item_company.view.*
import kotlinx.android.synthetic.main.item_similar_movie.view.*

class MoreInfoAdapter(private val items: List<Any>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_COMPANY -> CompanyViewHolder(inflateLayout(R.layout.item_company, parent))
            ITEM_CAST -> CastViewHolder(inflateLayout(R.layout.item_cast_movie, parent))
            ITEM_SIMILAR -> SimilarViewHolder(inflateLayout(R.layout.item_similar_movie, parent))
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemCount(): Int = infoSize()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_COMPANY -> {
                val viewHolder = holder as CompanyViewHolder
                viewHolder.bind(items?.get(position) as ProductionCompaniesItem)
            }
            ITEM_CAST -> {
                val viewHolder = holder as CastViewHolder
                viewHolder.bind(items?.get(position) as CastItem)
            }
            ITEM_SIMILAR -> {
                val viewHolder = holder as SimilarViewHolder
                viewHolder.bind(items?.get(position) as MovieResult)
            }
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items?.get(position)) {
            is ProductionCompaniesItem -> ITEM_COMPANY
            is CastItem -> ITEM_CAST
            is MovieResult -> ITEM_SIMILAR
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    private fun infoSize(): Int {
        return items?.size ?: 0
    }

    private fun inflateLayout(@LayoutRes layoutId: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    }

    companion object {
        private const val ITEM_COMPANY = 0
        private const val ITEM_CAST = 1
        private const val ITEM_SIMILAR = 2
    }
}

class CompanyViewHolder(view: View): RecyclerView.ViewHolder(view) {
    fun bind(company: ProductionCompaniesItem) {
        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500${company.logoPath}")
            .into(itemView.company_image)
        itemView.company_name.text = company.name
    }
}

class CastViewHolder(view: View): RecyclerView.ViewHolder(view) {
    fun bind(cast: CastItem) {
        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500${cast.profilePath}")
            .into(itemView.cast_image)
        itemView.cast_name.text = cast.name
    }
}

class SimilarViewHolder(view: View): RecyclerView.ViewHolder(view) {
    fun bind(similar: MovieResult) {
        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500${similar.posterPath}")
            .into(itemView.similar_image)
        itemView.similar_title.text = similar.title
        itemView.similar_rate.text = "${similar.voteAverage}"
    }
}