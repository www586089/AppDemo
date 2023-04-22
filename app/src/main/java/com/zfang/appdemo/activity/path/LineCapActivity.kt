package com.zfang.appdemo.activity.path

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.zfang.appdemo.R
import com.zfang.appdemo.base.BaseActivity

class LineCapActivity : BaseActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, LineCapActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_cap)
        initToolbar(title = "Line-Cap")
    }
}