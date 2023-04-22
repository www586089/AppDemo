package com.zfang.appdemo.activity.cfilter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.zfang.appdemo.R
import com.zfang.appdemo.base.BaseActivity

class PorterDuffColorFilter2Activity : BaseActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, PorterDuffColorFilter2Activity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_porterduff_color_filter2)
        initToolbar(title = "PorterDuff(颜色混合2)")
    }
}