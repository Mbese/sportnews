package com.example.sportnews.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SportNewsApiClient {
    companion object {

        val base_url = "http://ipadfeed.supersport.com/"

        fun getRetrofitInstance(): Retrofit.Builder {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(base_url)
        }

        fun getService(): SportNewsApiService {
            return getRetrofitInstance().build().create(SportNewsApiService::class.java)
        }
    }
}