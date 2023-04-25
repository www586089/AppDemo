package com.zfang.appdemo.activity.surfaceview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.zfang.appdemo.R
import com.zfang.appdemo.base.BaseActivity

class RedPackRainActivity: BaseActivity() {
    companion object {
        fun startActivity(ctx: Context) {
            ctx.startActivity(Intent(ctx, RedPackRainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_red_pack_rain)
        initToolbar(title = "红包雨")
    }
}