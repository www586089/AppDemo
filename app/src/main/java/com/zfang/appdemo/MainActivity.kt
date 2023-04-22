package com.zfang.appdemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zfang.appdemo.activity.ColorFilterActivity
import com.zfang.appdemo.activity.MatrixActivity
import com.zfang.appdemo.activity.PathEffectActivity
import com.zfang.appdemo.activity.TestActivity
import com.zfang.appdemo.activity.matrix.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickMatrix(view: View) {
        MatrixActivity.start(this)
    }

    fun onClickPathEffect(view: View) {
        PathEffectActivity.start(this)
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
}
