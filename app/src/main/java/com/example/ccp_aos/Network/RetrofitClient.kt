package com.example.ccp_aos.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 싱글톤 패턴으로 선언
object RetrofitClient {

    private const val BASEURL = "https://api.chucknorris.io/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val retrofitService = retrofit.create(RetrofitService::class.java)

}