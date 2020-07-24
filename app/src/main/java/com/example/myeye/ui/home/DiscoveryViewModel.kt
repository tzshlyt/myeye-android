package com.example.myeye.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myeye.base.BaseViewModel
import com.example.myeye.model.Discovery
import com.example.myeye.network.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DiscoveryViewModel : BaseViewModel() {
    var dataList = ArrayList<Discovery.Item>()

    val dataListLiveData = MutableLiveData<Result<Discovery>>()

    fun requestData() {
        disposable = Repository.getDiscovery()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                dataListLiveData.value = Result.success(it)
            }, {
                dataListLiveData.value = Result.failure(it)
            })
    }
}