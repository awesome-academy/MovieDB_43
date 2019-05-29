package com.asterisk.tuandao.themoviedb.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    open var emptyLiveData = MutableLiveData<Boolean>()
    protected open val compositeDisposable by lazy { CompositeDisposable() }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
