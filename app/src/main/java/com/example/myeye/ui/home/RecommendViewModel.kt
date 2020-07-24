package com.example.myeye.ui.home

import androidx.lifecycle.ViewModel
import com.example.myeye.network.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RecommendViewModel : ViewModel() {

    fun requestData() {
        Repository.getRecommend()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            })
    }
}