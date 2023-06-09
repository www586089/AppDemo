package com.zfang.appdemo.activity.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.zfang.appdemo.R
import com.zfang.appdemo.activity.view.drag.DragSwapActivity
import com.zfang.appdemo.activity.view.drag.ViewDragActivity
import com.zfang.appdemo.activity.view.drag.ViewDragActivity2
import com.zfang.appdemo.activity.view.drag.ViewRotationActivity
import com.zfang.appdemo.base.BaseActivity

class ViewOpActivity : BaseActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, ViewOpActivity::class.java))
        }

        private const val TAG = "ViewScrollActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_scroll)
        initToolbar(title = "View相关操作")
    }

    fun onClickViewRotation(view: View) {
        ViewRotationActivity.start(this)
    }
    fun onClickDragMe(view: View) {
        ViewDragActivity.start(this)
    }
    fun onClickViewDragHelper(view: View) {
        ViewDragActivity2.start(this)
    }
    fun onClickDragSwap(view: View) {
        DragSwapActivity.start(this)
    }
    fun onClickComposeShader(view: View) {}
    fun onClickShowComposeShader(view: View) {}
    fun onClickShaderTranslate(view: View) {}
    fun onClickShaderScale(view: View) {}
    fun onClickShaderScaleCircle(view: View) {}
}
