package com.devduo.androidcodetest.Networking

import com.devduo.androidcodetest.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    var retrofit= Retrofit.Builder()
        .baseUrl(Constants.URL).addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }
}