package com.project.seungjun.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.network.NetworkBinder
import com.project.network.response.BaseApiResponse
import com.project.seungjun.BaseViewModel
import com.project.seungjun.repository.TestRepository

class MainViewModel : BaseViewModel() {


    private val TAG = MainViewModel::class.java.name

    private val repository = TestRepository()

    private val innerResponseLiveData = MutableLiveData<Any>()
    val responseLiveData: LiveData<Any>
        get() = innerResponseLiveData

    fun getTestData() {

        val binder = NetworkBinder().apply {
            setDisposable(baseDisposable)
            setOnNext(::onResponseTestGet)
            setOnError(::onErrorTestGet)
        }

        repository.requestTestGet(binder)
    }


    private fun onResponseTestGet(response: BaseApiResponse) {

    }

    private fun onErrorTestGet(throwable: Throwable) {

    }
}