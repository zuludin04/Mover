package com.app.zuludin.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zuludin.detail.adapter.DetailInfoAdapter
import com.app.zuludin.detail.databinding.ActivityMovieDetailBinding
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.content_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        coordinateMotion()

        val viewModel: MovieDetailViewModel by viewModel()

        dataBinding.viewModel = viewModel
        dataBinding.information.viewModel = viewModel
        dataBinding.lifecycleOwner = this

        val movieId = intent.getIntExtra("MOVIE_ID", 0)
        viewModel.loadDetailData(movieId)

        setupMoreInfo(viewModel)

        dataBinding.backButton.setOnClickListener { finish() }
    }

    private fun setupMoreInfo(viewModel: MovieDetailViewModel) {
        viewModel.more.observe(this, Observer {
            it?.let { more ->
                recycler_info.apply {
                    adapter = DetailInfoAdapter(more)
                    layoutManager = LinearLayoutManager(this@MovieDetailActivity)
                }
            }
        })
    }

    private fun coordinateMotion() {
        val appBarLayout: AppBarLayout = findViewById(R.id.appbar_layout)
        val motionLayout: MotionLayout = findViewById(R.id.motion_layout)

        val listener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()
            motionLayout.progress = seekPosition
        }

        appBarLayout.addOnOffsetChangedListener(listener)
    }
}
