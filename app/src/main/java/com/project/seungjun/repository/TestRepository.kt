package com.project.seungjun.repository

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.project.network.NetworkBinder
import com.project.seungjun.model.network.ApiController
import com.project.seungjun.model.vo.TestLiveDataVo
import com.project.seungjun.repository.paging.TestDataFactory
import java.util.concurrent.Executors

class TestRepository {


    fun requestTest(binder: NetworkBinder, params: Map<String, String>): TestLiveDataVo {
        val testDataFactory = TestDataFactory(params, binder)

        val pagedListConfig = PagedList.Config.Builder().apply {
            setPageSize(PAGING_SIZE)
            setInitialLoadSizeHint(LOAD_HINT_COUNT)
            setPrefetchDistance(PREFETCH_DISTANCE)
            setEnablePlaceholders(false)
        }.build()


        val pagedData = LivePagedListBuilder(testDataFactory, pagedListConfig)
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .build()

        val test = Transformations.switchMap(testDataFactory.dataSourceLiveData, {
            it.etcLiveData
        })

        return TestLiveDataVo(test, pagedData)
    }


    fun requestTestGet(binder: NetworkBinder) {
        binder.execute(ApiController.testApiService.testGet())
    }

    companion object {
        const val PAGING_SIZE = 20
        const val LOAD_HINT_COUNT = 50
        const val PREFETCH_DISTANCE = 10
    }
}