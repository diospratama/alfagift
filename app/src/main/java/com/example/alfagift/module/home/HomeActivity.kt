package com.example.alfagift.module.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alfagift.databinding.ActivityHomeBinding
import com.example.alfagift.model.ResultDiscoverMovies
import com.example.alfagift.module.home.adapter.AdapterListMovies
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


class HomeActivity : AppCompatActivity(), HomeInterface.View {
    private var viewModel = HomeViewModel(this)
    private lateinit var dataResultMovie: List<ResultDiscoverMovies>
    private var pages:Int=1
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val listMovieAdapter: AdapterListMovies by lazy {
        AdapterListMovies(this,viewModel)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.onCreate(this,binding)
        initScrollListener()
        initAdapter()

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setListMovie(data: List<ResultDiscoverMovies>) {
        listMovieAdapter.setData(data)
        dataResultMovie = data
        listMovieAdapter.notifyDataSetChanged()
    }

    private fun initAdapter(){
        with(binding) {
            rvHome.apply {
                layoutManager =
                    LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                adapter = listMovieAdapter
            }

        }
    }

    private fun initScrollListener() {
        with(binding) {
            rvHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        binding.progressBar.visibility= View.VISIBLE
                        pages++
                        runLoadMore(pages)
                    }
                }
            })
        }

    }


    private suspend fun looadMore(page: Int) {
        delay(2000)
        viewModel.getData(page,binding,this)

    }

     fun runLoadMore(page: Int) = runBlocking {
        val load = async { looadMore(page) }
        load.await()
    }


}