package com.example.alfagift.module.splash

import androidx.appcompat.app.AppCompatActivity

interface SplashInterface {
    
    interface View{
        
    }

    interface ViewModel{
        fun onResume()
        fun onCreate(activity: AppCompatActivity)
        fun onDestroy()
        fun goToHome(activity: AppCompatActivity)
    }
    
}
