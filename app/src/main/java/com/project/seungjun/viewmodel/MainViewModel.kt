package com.project.seungjun.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.network.response.BaseApiResponse
import com.project.seungjun.BaseViewModel
import com.project.seungjun.model.usecase.TestUsecase

class MainViewModel : BaseViewModel() {


    private val TAG = MainViewModel::class.java.name

    private val testUsecase = TestUsecase()

    private val innerResponseLiveData = MutableLiveData<Any>()
    val responseLiveData: LiveData<Any>
        get() = innerResponseLiveData



    fun getTestData() {
        testUsecase.requestTestGet({ onResponseTestGet(it) }, { onErrorTestGet(it) }, Unit, baseDisposable)
    }


    private fun onResponseTestGet(response: BaseApiResponse) {

    }

    private fun onErrorTestGet(throwable: Throwable) {

    }
}