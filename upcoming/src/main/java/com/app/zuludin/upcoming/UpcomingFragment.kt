package com.app.zuludin.upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zuludin.upcoming.adapter.UpcomingAdapter
import com.app.zuludin.upcoming.databinding.FragmentUpcomingBinding
import kotlinx.android.synthetic.main.fragment_upcoming.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class UpcomingFragment : Fragment() {

    private lateinit var dataBinding: FragmentUpcomingBinding
    private val viewModel: UpcomingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming, container, false)

        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showMovieInRecycler()
    }

    private fun showMovieInRecycler() {
        val movieAdapter =
            UpcomingAdapter(ArrayList())

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }

        viewModel.movies.observe(this, Observer {
            it?.let { movie ->
                movieAdapter.addMovies(movie.data)
            }
        })
    }
}
