package com.asterisk.tuandao.themoviedb.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var viewDataBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        initAdapter()
        initComponent()
    }

    private fun initAdapter() {

    }

    private fun initComponent() {

    }
}
