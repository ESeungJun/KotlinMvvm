package com.project.seungjun

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivityWithVM<LAYOUT: ViewDataBinding, VM: ViewModel> : AppCompatActivity() {

    lateinit var dataBinding: LAYOUT

    abstract val viewModel: VM

    abstract val layoutId: Int

    abstract fun createObserveData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, layoutId)

        createObserveData()
    }

}
