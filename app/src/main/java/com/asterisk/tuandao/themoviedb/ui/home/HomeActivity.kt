package com.asterisk.tuandao.themoviedb.ui.home

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.ui.base.BaseActivity
import com.asterisk.tuandao.themoviedb.util.MovieViewModelFactory
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    override val layoutId: Int
        get() = R.layout.activity_home

    @Inject
    lateinit var viewModelFactory: MovieViewModelFactory

    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initComponents() {
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        homeViewModel.getMovies()
    }
}
