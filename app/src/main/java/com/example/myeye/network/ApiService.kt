package com.example.myeye.network

import com.example.myeye.model.HomeBean
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
  /**
   * 获取首页第一页数据
   */
  @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
  fun getHomeData(): Single<HomeBean>
}