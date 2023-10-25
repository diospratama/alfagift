package com.example.alfagift.module.splash

import androidx.appcompat.app.AppCompatActivity

class SplashViewModel(
private var view: SplashInterface.View? = null
): SplashInterface.ViewModel{
   

    override fun onResume() {}

    override fun onCreate(activity: AppCompatActivity) {
        view = activity as SplashActivity
       
    }

    override fun onDestroy() {
        view = null
    }
}