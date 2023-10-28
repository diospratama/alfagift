package com.example.alfagift.module.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.alfagift.module.home.HomeActivity
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

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


    override  fun goToHome(activity: AppCompatActivity) {
        val intent = Intent(activity.applicationContext, HomeActivity::class.java)
        activity.startActivity(intent)
        activity.finish()

    }



}