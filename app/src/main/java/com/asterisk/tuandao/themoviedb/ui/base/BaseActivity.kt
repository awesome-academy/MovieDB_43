package com.asterisk.tuandao.themoviedb.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.asterisk.tuandao.themoviedb.R
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }

    abstract fun initComponents()
}
