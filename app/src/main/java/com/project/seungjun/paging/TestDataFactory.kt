package com.project.seungjun.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import com.project.network.NetworkBinder
import com.project.seungjun.model.vo.TestVo

class TestDataFactory(
    private val params: Map<String, String>,
    private val networkBinder: NetworkBinder
) : DataSource.Factory<Int, TestVo>() {

    private val innerDataSourceLiveData = MutableLiveData<TestDataSource>()
    val dataSourceLiveData: LiveData<TestDataSource>
        get() = innerDataSourceLiveData


    private lateinit var testDataSource: TestDataSource

    override fun create(): DataSource<Int, TestVo> {

        testDataSource = TestDataSource(params, networkBinder)

        innerDataSourceLiveData.postValue(testDataSource)


        return testDataSource
    }


}