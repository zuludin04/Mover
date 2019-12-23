package com.app.zuludin.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.app.zuludin.detail.adapter.DetailInfoAdapter
import com.app.zuludin.detail.databinding.ActivityMovieDetailBinding
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_movie_detail.*
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
        viewModel.poster.observe(this, Observer {url ->
            val circularProgressDrawable = CircularProgressDrawable(this)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            if (url != null) {
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500$url")
                    .placeholder(circularProgressDrawable)
                    .into(detail_backdrop)
            }
        })

        viewModel.more.observe(this, Observer {
            it?.let {more ->
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
