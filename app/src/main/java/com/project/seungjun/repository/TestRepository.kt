package com.project.seungjun.repository

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.project.network.NetworkBinder
import com.project.seungjun.etc.ConstValue
import com.project.seungjun.model.vo.TestLiveDataVo
import com.project.seungjun.paging.TestDataFactory
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors

class TestRepository {


    fun requestTest(error: (Throwable) -> Unit, params: Map<String, String>, disposable: CompositeDisposable): TestLiveDataVo {

        val binder = NetworkBinder().apply {
            setDisposable(disposable)
            setOnError(error)
        }

        val testDataFactory = TestDataFactory(params, binder)

        val pagedListConfig = PagedList.Config.Builder().apply {
            setPageSize(ConstValue.PAGING_SIZE)
            setInitialLoadSizeHint(ConstValue.LOAD_HINT_COUNT)
            setPrefetchDistance(ConstValue.PREFETCH_DISTANCE)
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
}