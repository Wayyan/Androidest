package com.devduo.androidcodetest.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devduo.androidcodetest.Model.DataModel
import com.devduo.androidcodetest.Networking.BankDataSource

class BankApiViewModel : ViewModel() {
    var liveData: MutableLiveData<DataModel>? = null
    var bankDataSource: BankDataSource? = null

    fun collectBankLiveData() {

        if (liveData != null)
            return
        bankDataSource = BankDataSource().getInstance()
        liveData = bankDataSource!!.getBankData()

        Log.e("test","V M 1")
    }
}