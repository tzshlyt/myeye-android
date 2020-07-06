package com.example.myeye.network

import com.example.myeye.model.HomeBean
import io.reactivex.Single

class Repository {

	fun getHome(): Single<HomeBean> {
		return RetrofitClient.create().getHomeData()
	}
}