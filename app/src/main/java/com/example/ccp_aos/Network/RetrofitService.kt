package com.example.ccp_aos.Network

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("jokes/categories")
    fun get_categories(): Call<categoryList>

}