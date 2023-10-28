package com.example.alfagift.module.detailmovie

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alfagift.R
import com.example.alfagift.commons.Constant.Movies.extraData
import com.example.alfagift.commons.Constant.Movies.extraDataIdMovie
import com.example.alfagift.databinding.ActivityDetailMovieBinding
import com.example.alfagift.model.ResultReviewMovies
import com.example.alfagift.module.detailmovie.adapter.AdapterReviewMovies
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


class DetailMovieActivity : AppCompatActivity(), DetailMovieInterface.View {

    private var viewModel = DetailMovieViewModel(this)
    private lateinit var dataResultMovie: List<ResultReviewMovies>
    private var idMovie: Int = 0
    private var pages: Int = 1
    private val listReviewAdapter: AdapterReviewMovies by lazy {
        AdapterReviewMovies(this)
    }
    private val binding: ActivityDetailMovieBinding by lazy {
        ActivityDetailMovieBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.onCreate(this)
        initScrollListener()
        initAdapter()
        getPutExtraData()

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun playVideo(idVideo: String) {
        with(binding) {
            val frameVideo =
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/$idVideo\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"

            webview.webViewClient = mwebView
            val webSettings = webview.settings
            webSettings.javaScriptEnabled = true
            webview.loadData(frameVideo, "text/html", "utf-8")

        }

    }

    private val mwebView = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return true
        }

    }

    private fun initAdapter() {
        with(binding) {
            rvComment.apply {
                layoutManager =
                    LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                adapter = listReviewAdapter
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setListMovie(data: List<ResultReviewMovies>) {
        listReviewAdapter.setData(data)
        dataResultMovie = data
        listReviewAdapter.notifyDataSetChanged()
    }

    private fun initScrollListener() {
        with(binding) {
            rvComment.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        pages++
                        runLoadMore(pages)
                    }
                }
            })
        }

    }

    private fun getPutExtraData() {
        val description = intent.getStringExtra(extraData).toString()
        idMovie = intent.getIntExtra(extraDataIdMovie, 0)
        when (description) {
            "" -> {
                binding.txDescHome.text = this.applicationContext.getString(R.string.lorem)
            }

            "null" -> {
                binding.txDescHome.text = this.applicationContext.getString(R.string.lorem)
            }

            else -> {
                binding.txDescHome.text = description
                viewModel.getDataKeyMovie(idMovie)
                viewModel.getReviewMovie(idMovie, pages)
            }

        }

    }

    private suspend fun looadMore(page: Int) {
        delay(2000)
        viewModel.getReviewMovie(idMovie, page)

    }

    fun runLoadMore(page: Int) = runBlocking {
        val load = async { looadMore(page) }
        load.await()
    }

}