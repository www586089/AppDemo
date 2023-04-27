package com.zfang.appdemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zfang.appdemo.activity.*
import com.zfang.appdemo.activity.glide.GlideActivity
import com.zfang.appdemo.activity.matrix.*
import com.zfang.appdemo.activity.qr.QRCodeActivity
import com.zfang.appdemo.activity.view.ViewOpActivity
import com.zfang.appdemo.activity.view.drag.ViewDragActivity
import com.zfang.appdemo.activity.window.PopWindowActivity

//github token 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
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickMatrix(view: View) {
        MatrixActivity.start(this)
//        testException()
    }

    fun onClickPathEffect(view: View) {
        PathEffectActivity.start(this)
    }

    fun onClickPathCommon(view: View) {
        PathCommonActivity.start(this)
    }

    fun onClickShader(view: View) {
        ShaderEffectActivity.start(this)
    }

    fun onClickRotate(view: View) {
        RotateActivity.start(this)
    }

    fun onClickColorFilter(view: View) {
        ColorFilterActivity.start(this)
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

    fun onClickTest(view: View) {
        TestActivity.start(this)
    }

    fun onClickViewOp(view: View) {
        ViewOpActivity.start(this)
    }

    fun onClickDragView(view: View) {
        ViewDragActivity.start(this)
    }

    fun onClickWindow(view: View) {
        PopWindowActivity.start(this)
    }

    fun onClickQR(view: View) {
        QRCodeActivity.start(this)
    }

    fun onClickGlide(view: View) {
        GlideActivity.start(this)
    }
}