package com.app.zuludin.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.detail.R
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_recycler.view.*

class DetailInfoAdapter(private val items: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_HEADER -> HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false))
            ITEM_COMPANY -> CompanyInfoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent ,false))
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_HEADER -> {
                val viewHolder = holder as HeaderViewHolder
                viewHolder.bind(items[position] as String)
            }
            ITEM_COMPANY -> {
                val viewHolder = holder as CompanyInfoViewHolder
                viewHolder.bind(items[position] as DetailInfo)
            }
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is String -> ITEM_HEADER
            is DetailInfo -> ITEM_COMPANY
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    companion object {
        private const val ITEM_HEADER = 0
        private const val ITEM_COMPANY = 1
    }
}

class CompanyInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(company: DetailInfo) {
        val companyAdapter = MoreInfoAdapter(company.items)
        itemView.recycler_detail.apply {
            layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = companyAdapter
        }
    }
}

class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(title: String) {
        itemView.header_text.text = title
    }
}