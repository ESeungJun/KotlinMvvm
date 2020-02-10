package com.project.seungjun.model.vo

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class TestLiveDataVo(
    var testAny: LiveData<Int>,

    var testPagedList: LiveData<PagedList<TestVo>>
)