package com.zfang.appdemo.activity.cfilter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.zfang.appdemo.R
import com.zfang.appdemo.base.BaseActivity

class HueColorFilter2Activity : BaseActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, HueColorFilter2Activity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hue_color_filter2)
        initToolbar(title = "Hue(颜色色调2)")
    }
}