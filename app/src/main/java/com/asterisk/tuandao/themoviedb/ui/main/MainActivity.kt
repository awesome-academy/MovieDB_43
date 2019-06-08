package com.asterisk.tuandao.themoviedb.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.ui.base.BaseActivity
import com.asterisk.tuandao.themoviedb.ui.favorite.FavoriteFragment
import com.asterisk.tuandao.themoviedb.ui.genre.GenreFragment
import com.asterisk.tuandao.themoviedb.ui.home.HomeFragment
import com.asterisk.tuandao.themoviedb.ui.home.HomeViewModel
import com.asterisk.tuandao.themoviedb.util.MovieViewModelFactory
import com.asterisk.tuandao.themoviedb.util.switch
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override val layoutId: Int
        get() = R.layout.activity_main
    private val fragmentManager = supportFragmentManager
    private val container = R.id.frameContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar?.hide()
    }

    override fun initComponents() {
        val homeFragment = HomeFragment.newInstance() as Fragment
        navigation.setOnNavigationItemSelectedListener(this)
        supportFragmentManager.switch(
            container,
            homeFragment,
            MOVIES_FRAGMENT_TAG
        )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_movies -> {
                val homeFragment = HomeFragment.newInstance() as Fragment
                supportFragmentManager.switch(
                    container,
                    homeFragment,
                    MOVIES_FRAGMENT_TAG
                )
                return true
            }
            R.id.navigation_genres -> {
                val genreFragment = GenreFragment.newInstance() as Fragment
                supportFragmentManager.switch(
                    container,
                    genreFragment,
                    GENRES_FRAGMENT_TAG
                )
                return true
            }
            R.id.navigation_favorite -> {
                val favoriteFragment = FavoriteFragment.newInstance() as Fragment
                supportFragmentManager.switch(
                    container,
                    favoriteFragment,
                    FAVORITE_FRAGMENT_TAG
                )
                return true
            }
        }
        return false
    }

    companion object {
        const val MOVIES_FRAGMENT_TAG = "movies_fragment"
        const val GENRES_FRAGMENT_TAG = "genres_fragment"
        const val FAVORITE_FRAGMENT_TAG ="favorite_fragment"
    }
}
