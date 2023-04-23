package com.zfang.appdemo.activity.view.drag

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.zfang.appdemo.R
import com.zfang.appdemo.base.BaseActivity

class ViewDragActivity : BaseActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, ViewDragActivity::class.java))
        }

        private const val TAG = "ViewDragActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_drag)
        initToolbar(title = "ViewDrag(基础拖动-手动实现)")
    }
}
