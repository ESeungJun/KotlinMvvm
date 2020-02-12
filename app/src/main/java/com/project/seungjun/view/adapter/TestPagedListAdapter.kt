package com.project.seungjun.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.project.seungjun.databinding.ItemTestViewBinding
import com.project.seungjun.model.vo.TestVo
import com.project.seungjun.view.viewholder.TestViewHolder

class TestPagedListAdapter : PagedListAdapter<TestVo, TestViewHolder>(TestDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val binding = ItemTestViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TestViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        getItem(position)?.run {

        }
    }


    class TestDiffUtils : DiffUtil.ItemCallback<TestVo>() {
        override fun areItemsTheSame(oldItem: TestVo, newItem: TestVo)
                = oldItem.testInt == newItem.testInt

        override fun areContentsTheSame(oldItem: TestVo, newItem: TestVo)
                = oldItem.testInt == newItem.testInt

    }

}