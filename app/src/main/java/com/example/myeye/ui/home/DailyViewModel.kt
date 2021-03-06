package com.example.myeye.ui.home

import androidx.lifecycle.ViewModel
import com.example.myeye.network.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DailyViewModel : ViewModel() {
    fun requestData() {
        Repository.getDaily()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            })
    }
}