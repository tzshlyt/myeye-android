package com.example.myeye.network

import com.example.myeye.model.Daily
import com.example.myeye.model.Discovery
import com.example.myeye.model.HomeBean
import com.example.myeye.model.HomePageRecommend
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
  /**
   * 获取首页第一页数据
   */
  @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
  fun getHomeData(): Single<HomeBean>

  /**
   * 首页-发现列表
   */
  @GET("api/v7/index/tab/discovery")
  fun getDiscovery(): Single<Discovery>

  /**
   * 首页-推荐列表
   */
  @GET("api/v5/index/tab/allRec?page=0")
  fun getHomePageRecommend(): Single<HomePageRecommend>

  /**
   * 首页-日报列表
   */
  @GET("api/v5/index/tab/feed")
  fun getDaily(): Single<Daily>

}