package com.example.alfagift.module.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alfagift.databinding.ActivityHomeBinding        

class HomeActivity  : AppCompatActivity(), HomeInterface.View {

    private var viewModel = HomeViewModel(this)
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
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