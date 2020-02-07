package com.project.seungjun.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.seungjun.BaseActivity
import com.project.seungjun.R
import com.project.seungjun.viewmodel.MainViewModel
import com.project.seungjun.databinding.ActivityMainBinding
import com.project.seungjun.viewmodel.factory.BaseViewModelFactory

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, BaseViewModelFactory()).get(MainViewModel::class.java)
    }

    override val layoutId: Int by lazy {
        R.layout.activity_main
    }


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

