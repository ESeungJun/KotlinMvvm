package com.project.network

import com.project.network.response.BaseApiResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NetworkBinder {

    private lateinit var disposable: CompositeDisposable
    private lateinit var nextResponse: NetworkResponse.OnNext
    private lateinit var errorResponse: NetworkError.OnError


    fun setDisposable(disposable: CompositeDisposable) {
        this.disposable = disposable
    }

    fun setOnError(error: (Throwable) -> Unit) {
        this.errorResponse = object : NetworkError.OnError {
            override fun onError(throwable: Throwable) {
                error(throwable)
            }
        }
    }

    fun setOnNext(next: (T: BaseApiResponse) -> Unit) {
        this.nextResponse = object : NetworkResponse.OnNext {
            override fun onNext(apiResponse: BaseApiResponse) {
                next(apiResponse)
            }
        }
    }


    fun <T: BaseApiResponse> execute(request: Single<T>) {
        val callApiRequest = request.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        disposable.add(
            callApiRequest.subscribe(NetworkResponse(nextResponse), NetworkError(errorResponse))
        )

    }
}