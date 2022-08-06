package com.hessam.newsapiclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.hessam.newsapiclient.databinding.ActivityMainBinding
import com.hessam.newsapiclient.presentation.adapter.NewsAdapter
import com.hessam.newsapiclient.presentation.viewmodel.NewsViewModel
import com.hessam.newsapiclient.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: NewsViewModelFactory

    @Inject
    lateinit var newsAdapter: NewsAdapter

    lateinit var viewModel: NewsViewModel

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val vanController = navHostFragment.navController

        binding.bnvNews.setupWithNavController(vanController)


        viewModel = ViewModelProvider(this,factory)
            .get(NewsViewModel::class.java)
    }

}