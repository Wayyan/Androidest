package com.devduo.androidcodetest.Model

import com.google.gson.annotations.SerializedName

class BaseResponseModel {
    @SerializedName("message")
    lateinit var message: String

    @SerializedName("data")
    lateinit var data: List<BankDataModel>
}

class BankDataModel {
    @SerializedName("id")
    var id:Int?=0

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("logo_url")
    var logo_url: String? =null

    @SerializedName("is_major")
    var is_major: Boolean = false

}