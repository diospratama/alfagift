package com.example.alfagift.module.home

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alfagift.BuildConfig
import com.example.alfagift.api.ConnectionBuilder
import com.example.alfagift.commons.Constant
import com.example.alfagift.commons.Constant.Movies.extraData
import com.example.alfagift.commons.Constant.Movies.extraDataIdMovie
import com.example.alfagift.databinding.ActivityHomeBinding
import com.example.alfagift.model.ResponseDiscoverMovies
import com.example.alfagift.model.ResultDiscoverMovies
import com.example.alfagift.module.detailmovie.DetailMovieActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(
    private var view: HomeInterface.View? = null
) : HomeInterface.ViewModel {
    private val connectionService = ConnectionBuilder.connectionService
    private val disposable = CompositeDisposable()

    override fun onResume() {}

    override fun onCreate(activity: AppCompatActivity,binding: ActivityHomeBinding) {
        view = activity as HomeActivity
        getData(Constant.Movies.pageDefault,binding,activity)

    }

    override fun onDestroy() {
        view = null
    }

    override fun getListMovies(
        token: String,
        adult: Boolean,
        video: Boolean,
        languange: String,
        page: Int,
        sort: String,
        onSuccess: (ResponseDiscoverMovies) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        connectionService.getListMovie(token, adult, video, languange, page, sort)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError)
            .let(disposable::add)
    }

    override fun getData(page: Int,binding: ActivityHomeBinding,activity: AppCompatActivity) {
        getListMovies(
            BuildConfig.tokenKeyAuth,
            adult = false,
            video = false,
            languange = Constant.Header.language,
            page = page,
            sort = Constant.Header.sort,
            onSuccess = {
                view?.setListMovie(it.results)
                binding.progressBar.visibility= View.GONE
                Toast.makeText(activity.applicationContext, "Connection successful", Toast.LENGTH_LONG).show()

            },
            onError = {
                binding.progressBar.visibility= View.GONE
                binding.consConnection.visibility=View.VISIBLE
                binding.titleNoConnection.text=it.message
                binding.titleHome.visibility=View.GONE
            })
    }

    override fun goToDetailMovies(activity: AppCompatActivity,detailMovies:ResultDiscoverMovies) {
        val intent = Intent(activity.applicationContext, DetailMovieActivity::class.java)
        intent.putExtra(extraData, detailMovies.overview)
        intent.putExtra(extraDataIdMovie, detailMovies.id?.toInt())
        activity.startActivity(intent)
    }

}