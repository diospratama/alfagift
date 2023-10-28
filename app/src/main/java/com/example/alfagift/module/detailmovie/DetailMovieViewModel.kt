package com.example.alfagift.module.detailmovie

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.alfagift.BuildConfig
import com.example.alfagift.api.ConnectionBuilder
import com.example.alfagift.commons.Constant
import com.example.alfagift.model.ResponseMoviesVideos
import com.example.alfagift.model.ResponseReviewMovies
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailMovieViewModel(
    private var view: DetailMovieInterface.View? = null
) : DetailMovieInterface.ViewModel {
    private val connectionService = ConnectionBuilder.connectionService
    private val disposable = CompositeDisposable()


    override fun onResume() {}

    override fun onCreate(activity: AppCompatActivity) {
        view = activity as DetailMovieActivity

    }
    override fun onDestroy() {
        view = null
    }

    private fun getListVideos(
        token: String,
        idMovie: Int,
        languange: String,
        onSuccess: (ResponseMoviesVideos) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        connectionService.getMovieTrailer(token, idMovie, languange)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError)
            .let(disposable::add)
    }

    private fun getReviewsMovie(
        token: String,
        idMovie: Int,
        page: Int,
        languange: String,
        onSuccess: (ResponseReviewMovies) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        connectionService.getMovieReviews(token, idMovie, languange,page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError)
            .let(disposable::add)
    }

    override fun getDataKeyMovie(idMovie: Int) {
        getListVideos(
            BuildConfig.tokenKeyAuth,
            idMovie = idMovie,
            languange = Constant.Header.language,
            onSuccess = {
                it.results[0].key?.let { it1 -> view?.playVideo(it1) }

            },
            onError = {
                Log.e("CEK_ID_VIDEO_ERROR", "getData: " + it.message)

            })
    }

    override fun getReviewMovie(idMovie: Int,page: Int) {
        getReviewsMovie(
            BuildConfig.tokenKeyAuth,
            idMovie = idMovie,
            languange = Constant.Header.language,
            page = page,
            onSuccess = {
                view?.setListMovie(it.results)

            },
            onError = {
                Log.e("CEK_ID_VIDEO_ERROR", "getData: " + it.message)

            })
    }
}