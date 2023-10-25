package com.example.alfagift.module.home

import androidx.appcompat.app.AppCompatActivity

class HomeViewModel(
private var view: HomeInterface.View? = null
): HomeInterface.ViewModel {
   

    override fun onResume() {}

    override fun onCreate(activity: AppCompatActivity) {
        view = activity as HomeActivity
       
    }

    override fun onDestroy() {
        view = null
    }
}