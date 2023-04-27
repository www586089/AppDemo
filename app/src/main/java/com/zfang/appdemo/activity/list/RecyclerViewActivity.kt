package com.zfang.appdemo.activity.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.zfang.appdemo.R
import com.zfang.appdemo.activity.list.adapter.ListAdapter
import com.zfang.appdemo.activity.list.model.ListViewModel
import com.zfang.appdemo.base.BaseActivity
import com.zfang.appdemo.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : BaseActivity() {


    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, RecyclerViewActivity::class.java))
        }
    }
    private val viewModel: ListViewModel by viewModels()
    private lateinit var binding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("RecyclerView测试")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view)

        val listAdapter = ListAdapter()
        binding.recyclerView.apply {
            binding.recyclerView.adapter = listAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
        }

        viewModel.listLiveData.observe(this) {
            Log.e("zfang", "get Data size = ${it.size}")
            it ?: return@observe

            listAdapter.setData(it)
        }
        binding.change.setOnClickListener {
            binding.recyclerView.adapter?.notifyItemChanged(0, 0)
        }
    }
}