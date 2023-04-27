package com.zfang.appdemo

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zfang.appdemo.activity.*
import com.zfang.appdemo.activity.bitmap.BitmapActivity
import com.zfang.appdemo.activity.matrix.*
import com.zfang.appdemo.activity.view.ViewOpActivity
import com.zfang.appdemo.activity.view.drag.ViewDragActivity
import com.zfang.appdemo.receiver.HomeWatcherReceiver


class MainActivity : AppCompatActivity() {

    private lateinit var mHomeKeyReceiver: HomeWatcherReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerHomeKeyReceiver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterHomeKeyReceiver(this)
    }


    private fun registerHomeKeyReceiver(context: Context) {
        Log.e("zfang", "registerHomeKeyReceiver")
        mHomeKeyReceiver = HomeWatcherReceiver()
        val homeFilter = IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
        context.registerReceiver(mHomeKeyReceiver, homeFilter)
    }

    private fun unregisterHomeKeyReceiver(context: Context) {
        Log.e("zfang", "unregisterHomeKeyReceiver")
        context.unregisterReceiver(mHomeKeyReceiver)
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

    fun onClickSurface(view: View) {
        SurfaceViewActivity.startActivity(this)
    }

    fun onClickBitmap(view: View) {
        BitmapActivity.startActivity(this)
    }
}
