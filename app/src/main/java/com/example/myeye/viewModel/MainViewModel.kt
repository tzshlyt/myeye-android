package com.example.myeye.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myeye.base.BaseViewModel
import com.example.myeye.model.HomeBean
import com.example.myeye.network.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel: ViewModel() {
	var homeData: MutableLiveData<HomeBean> = MutableLiveData()

	fun requestData() {
		val disposable = Repository()
			.getHome()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe ({ it ->
				homeData.postValue(it)
			}, {err ->

			})
	}
}