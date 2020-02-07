package com.project.seungjun.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.project.seungjun.BaseActivity
import com.project.seungjun.R
import com.project.seungjun.viewmodel.MainViewModel
import com.project.seungjun.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel
        get() = MainViewModel()

    override val layoutId: Int
        get() = R.layout.activity_main



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        dataBinding.lifecycleOwner = this

        createObserveData()

        viewModel.getTestData()
    }


    override fun createObserveData() {
        viewModel.responseLiveData.observe(this, Observer {

        })
    }
}

