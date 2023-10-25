package com.example.alfagift.module.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alfagift.databinding.ActivitySplashBinding        

@SuppressLint("CustomSplashScreen")
class SplashActivity  : AppCompatActivity(),SplashInterface.View {

    private var viewModel = SplashViewModel(this)
    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }
    
    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.onCreate(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
    
}