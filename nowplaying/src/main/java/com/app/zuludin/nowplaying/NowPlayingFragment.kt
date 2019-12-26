package com.app.zuludin.nowplaying

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.app.zuludin.common.base.BaseFragment
import com.app.zuludin.common.base.BaseViewModel
import com.app.zuludin.nowplaying.adapter.NowPlayingAdapter
import com.app.zuludin.nowplaying.databinding.FragmentNowPlayingBinding
import kotlinx.android.synthetic.main.fragment_now_playing.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : BaseFragment() {

    private lateinit var dataBinding: FragmentNowPlayingBinding
    private val viewModel: NowPlayingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_now_playing, container, false)

        dataBinding.viewModel = viewModel
        dataBinding.errorLayout.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showMovieInRecycler()
    }

    override fun getViewModel(): BaseViewModel = viewModel

    private fun showMovieInRecycler() {
        val movieAdapter = NowPlayingAdapter(ArrayList())

        dataBinding.recyclerView.apply {
            recycler_view.setHasFixedSize(true)
            recycler_view.layoutManager = GridLayoutManager(requireContext(), 2)
            recycler_view.adapter = movieAdapter
        }

        viewModel.movies.observe(this, Observer {
            it?.let { movie ->
                movieAdapter.addMovies(movie.data)
            }
        })
    }
}
