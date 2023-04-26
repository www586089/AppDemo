package com.zfang.appdemo.activity.bitmap

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import com.zfang.appdemo.R
import com.zfang.appdemo.base.BaseActivity
import com.zfang.appdemo.utils.BitmapUtils
import kotlinx.android.synthetic.main.activity_bitmap_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BitmapActivity: BaseActivity() {
    companion object {
        fun startActivity(ctx: Context) {
            ctx.startActivity(Intent(ctx, BitmapActivity::class.java))
        }
    }

    var girlBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap_layout)
        initToolbar(title = "Bitmap操作")
    }

    fun loadSample(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            girlBitmap = BitmapUtils.getBitmap(this@BitmapActivity.resources, R.drawable.icon_girl, imageView.width, imageView.height)
            if (null != girlBitmap) {
                runOnUiThread {
                    imageView.setImageBitmap(girlBitmap)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        girlBitmap?.recycle()
    }
}