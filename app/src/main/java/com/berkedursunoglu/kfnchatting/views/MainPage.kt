package com.berkedursunoglu.kfnchatting.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.berkedursunoglu.kfnchatting.R
import com.berkedursunoglu.kfnchatting.databinding.ActivityMainPageBinding
import com.berkedursunoglu.kfnchatting.utils.Utils

class MainPage : AppCompatActivity() {

    private lateinit var dataBinding:ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main_page)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_page_fragment_container) as NavHostFragment
        dataBinding.bottomNavigationView2.setupWithNavController(navHostFragment.navController)
    }
}