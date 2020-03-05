package com.devduo.androidcodetest.Networking

import com.devduo.androidcodetest.Constants
import com.devduo.androidcodetest.Model.BaseResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface BankApi {

    @Headers(Constants.app_id,Constants.key)
    @GET("api/banks")
    fun getBanks():Call<BaseResponseModel>

}