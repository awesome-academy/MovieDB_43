package com.asterisk.tuandao.themoviedb.ui.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.databinding.FragmentGenreBinding
import com.asterisk.tuandao.themoviedb.ui.base.BaseFragment

class GenreFragment : BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_genre
    private lateinit var viewDataBinding: FragmentGenreBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentGenreBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun initComponents() {
        //do observer
    }
}
