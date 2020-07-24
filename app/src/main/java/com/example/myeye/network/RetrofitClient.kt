package com.example.myeye.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object RetrofitClient {
	private const val BASE_URL = "http://baobab.kaiyanapp.com/"

	fun create(): ApiService {
		val logger = HttpLoggingInterceptor()
		logger.level = HttpLoggingInterceptor.Level.BODY

		val client = OkHttpClient.Builder()
			.addInterceptor(logger)
			.addInterceptor(HeaderInterceptor())
			.addInterceptor(BasicParamsInterceptor())
			.build()

		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.build()
			.create(ApiService::class.java)
	}

	class HeaderInterceptor: Interceptor{
		override fun intercept(chain: Interceptor.Chain): Response {
			val original = chain.request()
			val request = original.newBuilder().apply {
				header("model", "Android")
				header("If-Modified-Since", "${Date()}")
				header("User-Agent", System.getProperty("http.agent") ?: "unknown")
			}.build()
			return chain.proceed(request)
		}
	}

	class BasicParamsInterceptor : Interceptor {
		override fun intercept(chain: Interceptor.Chain): Response {
			val originalRequest = chain.request()
			val originalHttpUrl = originalRequest.url
			val url = originalHttpUrl.newBuilder().apply {
				addQueryParameter("udid", "26868b32e808498db32fd51fb422d00175e179df")
				addQueryParameter("vc", "83")
//				addQueryParameter("udid", GlobalUtil.getDeviceSerial())
//				addQueryParameter("vc", GlobalUtil.eyepetizerVersionCode.toString())
//				addQueryParameter("vn", GlobalUtil.eyepetizerVersionName)
//				addQueryParameter("size", screenPixel())
//				addQueryParameter("deviceModel", GlobalUtil.deviceModel)
//				addQueryParameter("first_channel", GlobalUtil.deviceBrand)
//				addQueryParameter("last_channel", GlobalUtil.deviceBrand)
//				addQueryParameter("system_version_code", "${Build.VERSION.SDK_INT}")
			}.build()
			val request = originalRequest.newBuilder().url(url).method(originalRequest.method, originalRequest.body).build()
			return chain.proceed(request)
		}
	}
}