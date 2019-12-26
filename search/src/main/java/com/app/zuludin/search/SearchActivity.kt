package com.app.zuludin.search

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zuludin.common.EqualSpacingItemDecoration
import com.app.zuludin.search.adapter.SearchResultAdapter
import com.app.zuludin.search.databinding.ActivitySearchBinding
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val viewModel: SearchMovieViewModel by viewModel()
    private lateinit var dataBinding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this

        search.setIconifiedByDefault(false)
        search.queryHint = "Search"

        setupSearchRecycler()
        populateSearchResult()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun setupSearchRecycler() {
        val searchAdapter = SearchResultAdapter(ArrayList())

        dataBinding.recyclerSearch.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            setHasFixedSize(true)
            addItemDecoration(EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.VERTICAL))
            adapter = searchAdapter
        }
    }

    private fun populateSearchResult() {
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean = false

            override fun onQueryTextChange(query: String): Boolean {
                viewModel.searchMovie(query)
                return true
            }
        })
    }
}