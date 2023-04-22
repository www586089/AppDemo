package com.zfang.appdemo.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.zfang.appdemo.R
import com.zfang.appdemo.activity.path.LineCapActivity
import com.zfang.appdemo.activity.path.LineJoinActivity
import com.zfang.appdemo.base.BaseActivity

class PathCommonActivity : BaseActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, PathCommonActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_path_common)
        initToolbar(title = "路径相关操作")
    }

    fun onLineCapClick(view: View) {
        LineCapActivity.start(this)
    }

    fun onLineJoinClick(view: View) {
        LineJoinActivity.start(this)
    }
}
