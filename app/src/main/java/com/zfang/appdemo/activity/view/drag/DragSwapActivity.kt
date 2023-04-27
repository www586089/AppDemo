package com.zfang.appdemo.activity.view.drag

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.zfang.appdemo.base.BaseActivity
import com.zfang.appdemo.databinding.ActivityDragSwapBinding

class DragSwapActivity : BaseActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, DragSwapActivity::class.java))
        }

        private const val TAG = "DragSwapActivity"
    }
    private lateinit var binding: ActivityDragSwapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDragSwapBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initToolbar(title = "ViewDrag(DragSwapActivity)")
    }
}
