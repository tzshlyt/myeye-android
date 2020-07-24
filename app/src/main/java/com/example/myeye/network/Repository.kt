package com.example.myeye.network

import com.example.myeye.model.Daily
import com.example.myeye.model.Discovery
import com.example.myeye.model.HomeBean
import com.example.myeye.model.HomePageRecommend
import io.reactivex.Single

object Repository {

	fun getHome(): Single<HomeBean> {
		return RetrofitClient.create().getHomeData()
	}

	fun getDiscovery(): Single<Discovery> {
		return RetrofitClient.create().getDiscovery()
	}

	fun getRecommend(): Single<HomePageRecommend> {
		return RetrofitClient.create().getHomePageRecommend()
	}

	fun getDaily(): Single<Daily> {
		return RetrofitClient.create().getDaily()
	}
}