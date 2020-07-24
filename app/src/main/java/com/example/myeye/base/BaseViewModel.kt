package com.example.myeye.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

open class BaseViewModel: ViewModel() {

    var disposable: Disposable? = null

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}