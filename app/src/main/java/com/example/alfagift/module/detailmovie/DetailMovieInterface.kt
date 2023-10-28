package com.example.alfagift.module.detailmovie

import androidx.appcompat.app.AppCompatActivity
import com.example.alfagift.model.ResultReviewMovies

interface DetailMovieInterface {
    
    interface View{
        fun playVideo(idVideo:String)
        fun setListMovie(data: List<ResultReviewMovies>)
        
    }

    interface ViewModel{
        fun onResume()
        fun onCreate(activity: AppCompatActivity)
        fun onDestroy()
        fun getDataKeyMovie(idMovie: Int)
        fun getReviewMovie(idMovie: Int,page:Int)
    }
    
}
