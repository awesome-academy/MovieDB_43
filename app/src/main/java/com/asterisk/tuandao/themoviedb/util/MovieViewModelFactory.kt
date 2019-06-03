package com.asterisk.tuandao.themoviedb.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asterisk.tuandao.themoviedb.R
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class MovieViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("${R.string.message_throw_exception_viewmodel_factory} $modelClass")
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}
