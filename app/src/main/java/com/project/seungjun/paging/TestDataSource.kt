package com.project.seungjun.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.project.network.NetworkBinder
import com.project.network.RetrofitClient
import com.project.network.response.BaseApiResponse
import com.project.seungjun.model.network.api.TestApiService
import com.project.seungjun.model.vo.TestVo

class TestDataSource(
    private val netParams: Map<String, String>,
    private val networkBinder: NetworkBinder
) : PageKeyedDataSource<Int, TestVo>() {


    private val TAG = TestDataSource::class.java.simpleName


    private val innerEtcLiveData = MutableLiveData<Int>()
    val etcLiveData: LiveData<Int>
        get() = innerEtcLiveData


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, TestVo>) {
        networkBinder.setOnNext {
            callback.onResult(onResponse(it), 0, 0)
        }
        networkBinder.execute(RetrofitClient.build().create(TestApiService::class.java).testGet())
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TestVo>) {
        networkBinder.setOnNext {
            callback.onResult(onResponse(it), params.key + 1)
        }
        networkBinder.execute(RetrofitClient.build().create(TestApiService::class.java).testGet())
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TestVo>) {

    }


    private fun onResponse(response: BaseApiResponse): List<TestVo> {

        val response = response
        val testList = mutableListOf<TestVo>()

        return testList

    }

}