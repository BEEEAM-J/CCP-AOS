package com.example.ccp_aos.Network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface RetrofitService {

    @GET("jokes/categories")
    fun get_categories(): Call<categoryList>

    @GET("jokes/random")
    fun get_jokes(
        @Query("category") category:String?
    ): Call<jokes>


}