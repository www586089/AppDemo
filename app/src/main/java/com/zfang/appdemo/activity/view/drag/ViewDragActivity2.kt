package com.zfang.appdemo.activity.view.drag

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.zfang.appdemo.R
import com.zfang.appdemo.base.BaseActivity

class ViewDragActivity2 : BaseActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, ViewDragActivity2::class.java))
        }

        private const val TAG = "ViewDragActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_drag2)
        initToolbar(title = "ViewDrag(ViewDragHelper)")
    }
}
