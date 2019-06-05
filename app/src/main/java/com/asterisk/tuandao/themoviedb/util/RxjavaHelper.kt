package com.asterisk.tuandao.themoviedb.util

import androidx.lifecycle.MutableLiveData
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.handleData(mutableLiveData: MutableLiveData<Resources<T>>): Disposable {
    var disposable: Disposable?
    mutableLiveData.value = Resources.loading(true)
    disposable = this@handleData.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            mutableLiveData.value = Resources.success(it)
        }, {
            mutableLiveData.value = Resources.failure(it)
        })
    return disposable
}
