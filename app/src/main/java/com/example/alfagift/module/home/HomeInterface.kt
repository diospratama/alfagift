package com.example.alfagift.module.home

import androidx.appcompat.app.AppCompatActivity

interface HomeInterface {
    
    interface View{
        
    }

    interface ViewModel{
        fun onResume()
        fun onCreate(activity: AppCompatActivity)
        fun onDestroy()
    }
    
}
