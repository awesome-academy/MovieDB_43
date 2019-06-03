package com.asterisk.tuandao.themoviedb.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.ui.base.BaseActivity
import com.asterisk.tuandao.themoviedb.ui.home.HomeFragment
import com.asterisk.tuandao.themoviedb.util.switch
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


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
        var homeFragment = HomeFragment.newInstance() as Fragment
        navigation.setOnNavigationItemSelectedListener(this)
        supportFragmentManager.switch(
            container,
            homeFragment,
            MOVIES_FRAGMENT_TAG
        )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var homeFragment = HomeFragment.newInstance() as Fragment
        when (item.itemId) {
            R.id.navigation_movies -> {
                item.setIcon(R.drawable.ic_movies_blue)
                supportFragmentManager.switch(
                    container,
                    homeFragment,
                    MOVIES_FRAGMENT_TAG
                )
                return true
            }
        }
        return false
    }

    companion object {
        const val MOVIES_FRAGMENT_TAG = "movies_fragment"
    }
}
