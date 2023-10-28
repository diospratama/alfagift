package com.example.alfagift.module.home

import androidx.appcompat.app.AppCompatActivity
import com.example.alfagift.databinding.ActivityHomeBinding
import com.example.alfagift.model.ResponseDiscoverMovies
import com.example.alfagift.model.ResultDiscoverMovies
import retrofit2.Response

interface HomeInterface {
    
    interface View{
        fun setListMovie(data:List<ResultDiscoverMovies>)
        
    }

    interface ViewModel{
        fun onResume()
        fun onCreate(activity: AppCompatActivity,binding: ActivityHomeBinding)
        fun onDestroy()

        fun getListMovies(
            token: String,
            adult: Boolean,
            video: Boolean,
            languange: String,
            page: Int,
            sort: String,
            onSuccess: (ResponseDiscoverMovies) -> Unit,
            onError: (Throwable) -> Unit
        )
        fun getData(page: Int,binding: ActivityHomeBinding,activity: AppCompatActivity)
        fun goToDetailMovies(activity: AppCompatActivity,detailMovies:ResultDiscoverMovies)

    }
    
}
