package com.asterisk.tuandao.themoviedb.ui.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.ui.base.BaseFragment
import com.asterisk.tuandao.themoviedb.util.MovieViewModelFactory
import javax.inject.Inject

class GenreFragment : BaseFragment(){
    override val layoutId: Int
        get() = R.layout.fragment_genre

    @Inject
    lateinit var viewModelFactory: MovieViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initComponents() {
        //do observer
    }
}