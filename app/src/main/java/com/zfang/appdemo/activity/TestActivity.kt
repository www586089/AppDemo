package com.zfang.appdemo.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.zfang.appdemo.R
import com.zfang.appdemo.activity.test.ScrollTestActivity
import com.zfang.appdemo.base.BaseActivity

class TestActivity : BaseActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, TestActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        initToolbar(title = "实验")
    }

    fun onClickScrollTest(view: View) {
        ScrollTestActivity.start(this)
    }
}
