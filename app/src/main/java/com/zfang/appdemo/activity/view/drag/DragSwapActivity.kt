package com.zfang.appdemo.activity.view.drag

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.zfang.appdemo.R
import com.zfang.appdemo.base.BaseActivity

class DragSwapActivity : BaseActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, DragSwapActivity::class.java))
        }

        private const val TAG = "DragSwapActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_swap)
        initToolbar(title = "ViewDrag(DragSwapActivity)")
    }
}
