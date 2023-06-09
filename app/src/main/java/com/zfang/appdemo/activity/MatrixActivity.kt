package com.zfang.appdemo.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.zfang.appdemo.R
import com.zfang.appdemo.activity.matrix.*
import com.zfang.appdemo.base.BaseActivity

class MatrixActivity : BaseActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, MatrixActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matrix)
        initToolbar(title = "Matrix运算相关")
    }

    fun onClickBasicUse(view: View) {
        BasicUseActivity.start(this)
    }

    fun onClickTranslate(view: View) {
        TranslateActivity.start(this)
    }

    fun onClickRotate(view: View) {
        RotateActivity.start(this)
    }

    fun onClickScale(view: View) {
        ScaleActivity.start(this)
    }

    fun onClickSkew(view: View) {
        SkewActivity.start(this)
    }

    fun onClickClip(view: View) {
        ClipActivity.start(this)
    }

    fun onClickInvert(view: View) {
        InvertActivity.start(this)
    }

    fun onClickMap(view: View) {
        MapActivity.start(this)
    }
}
