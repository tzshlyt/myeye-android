package com.example.myeye.network

import android.util.Log
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
	private const val BASE_URL = "http://baobab.kaiyanapp.com/api/"

	fun create(): ApiService {
		val logger = HttpLoggingInterceptor()
		logger.level = HttpLoggingInterceptor.Level.BODY

		val client = OkHttpClient.Builder()
			.addInterceptor(logger)
			.build()

		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.build()
			.create(ApiService::class.java)
	}
}