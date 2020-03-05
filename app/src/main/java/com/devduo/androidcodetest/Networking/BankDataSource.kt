package com.devduo.androidcodetest.Networking

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.devduo.androidcodetest.Model.BaseResponseModel
import com.devduo.androidcodetest.Model.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BankDataSource {
    var bankRepository: BankDataSource? = null
    var bankApi = RetrofitService().createService(BankApi::class.java)

    fun getInstance(): BankDataSource {
        if (bankRepository == null)
            bankRepository = BankDataSource()
        return bankRepository!!
    }

    fun getBankData(): MutableLiveData<DataModel> {
        var data = MutableLiveData<DataModel>()
        bankApi.getBanks().enqueue(object : Callback<BaseResponseModel> {
            override fun onFailure(call: Call<BaseResponseModel>, t: Throwable) {
                var dataModel=DataModel()
                dataModel.baseResponse = null
                dataModel.success = false
                data.value = dataModel
            }

            override fun onResponse(
                call: Call<BaseResponseModel>, response: Response<BaseResponseModel>
            ) {
                if (response.isSuccessful) {
                    var dataModel=DataModel()
                    dataModel.baseResponse = response.body()
                    dataModel.success = true
                    data.value = dataModel
                } else {
                    var dataModel=DataModel()
                    dataModel.baseResponse = null
                    dataModel.success = false
                    data.value = dataModel
                }
            }

        })
        return data
    }
}