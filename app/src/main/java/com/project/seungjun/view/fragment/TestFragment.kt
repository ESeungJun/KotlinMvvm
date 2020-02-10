package com.project.seungjun.view.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.project.seungjun.BaseFragment
import com.project.seungjun.R
import com.project.seungjun.databinding.FragmentTestBinding
import com.project.seungjun.viewmodel.TestFragmentViewModel
import com.project.seungjun.viewmodel.factory.BaseViewModelFactory

class TestFragment : BaseFragment<FragmentTestBinding, TestFragmentViewModel>() {
    override val viewModel: TestFragmentViewModel
        get() = ViewModelProvider(this, BaseViewModelFactory()).get(TestFragmentViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.fragment_test

    override fun createObserveData() {

    }

    override fun initView() {

    }

    override fun initArgument(bundle: Bundle) {

    }


    companion object {

        private var instance: TestFragment? = null

        fun newInstance(): TestFragment {
            if (instance == null)
                instance = TestFragment()

            return instance!!
        }

    }
}