package com.zfang.appdemo.activity.test

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatImageView
import com.zfang.appdemo.R
import com.zfang.appdemo.base.BaseActivity
import com.zfang.appdemo.databinding.ActivityScrollTestBinding
import com.zfang.appdemo.utils.getScreenHeight

class ScrollTestActivity : BaseActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, ScrollTestActivity::class.java))
        }
    }

    private lateinit var binding: ActivityScrollTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollTestBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initToolbar(title = "滚动测试")
        setImageHeight(this)
    }

    private fun setImageHeight(ctx: Context) {
        findViewById<AppCompatImageView>(R.id.user_icon).post {
            val screenHeight = getScreenHeight(ctx)
            val childHeight = binding.childContainer.height
            val parentFrameHeight = binding.parentFrame.height
            Log.e("zfang", "screenHeight = $screenHeight, childHeight = $childHeight, parentFrameHeight = $parentFrameHeight")
            val userIconLP = binding.userIcon.layoutParams
            userIconLP.height = parentFrameHeight
            binding.childContainer.requestLayout()
        }
    }
}
