package com.project.network

import com.project.network.response.BaseApiResponse
import io.reactivex.functions.Consumer

class NetworkResponse(
    private var next: OnNext
) : Consumer<BaseApiResponse> {

    interface OnNext {
        fun onNext(apiResponse: BaseApiResponse)
    }


    override fun accept(response: BaseApiResponse?) {
        response?.let {
            next.onNext(it)
            return
        }
    }
}


class NetworkError(
    private var error: OnError
) : Consumer<Throwable> {

    interface OnError {
        fun onError(throwable: Throwable)
    }

    override fun accept(throwable: Throwable?) {
        throwable?.let {
            error.onError(throwable)
            return
        }
    }
}