package com.app.zuludin.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zuludin.popular.adapter.PopularAdapter
import com.app.zuludin.popular.databinding.FragmentPopularBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class PopularFragment : Fragment() {

    private lateinit var dataBinding: FragmentPopularBinding
    private val viewModel: PopularViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false)

        dataBinding.viewModel = viewModel
        dataBinding.errorLayout.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showMovieInRecycler()
    }

    private fun showMovieInRecycler() {
        val movieAdapter =
            PopularAdapter(ArrayList())

        dataBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }

        viewModel.populars.observe(this, Observer {
            it.data.let { movie ->
                movieAdapter.addMovies(movie)
            }
        })
    }
}
