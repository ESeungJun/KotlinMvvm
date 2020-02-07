package com.project.seungjun.model.usecase

import com.project.network.NetworkBinder
import com.project.network.RetrofitClient
import com.project.seungjun.model.network.api.TestApiService
import com.project.network.response.BaseApiResponse
import io.reactivex.disposables.CompositeDisposable

class TestUsecase {


    fun requestTestGet(onNext: (BaseApiResponse) -> Unit, onError: (Throwable) -> Unit, request: Any, disposable: CompositeDisposable) {
        NetworkBinder().apply {
            setDisposable(disposable)
            setOnNext(onNext)
            setOnError(onError)
        }.execute(RetrofitClient.build().create(TestApiService::class.java).testGet())
    }
}