package com.zfang.appdemo.activity.surfaceview

import android.content.Context
import android.content.Intent
import android.graphics.RectF
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.zfang.appdemo.activity.surfaceview.packet.PacketManager
import com.zfang.appdemo.base.BaseActivity
import com.zfang.appdemo.common.px2Dp
import com.zfang.appdemo.databinding.ActivityRedPackRainBinding
import com.zfang.appdemo.utils.getScreenHeight
import com.zfang.appdemo.utils.getScreenWidth

class RedPackRainActivity: BaseActivity() {
    companion object {
        private val TAG = "RedPackRainActivity"
        fun startActivity(ctx: Context) {
            ctx.startActivity(Intent(ctx, RedPackRainActivity::class.java))
        }
    }

    private lateinit var binding: ActivityRedPackRainBinding
    private var surfaceLocation: IntArray = IntArray(2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRedPackRainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initToolbar(title = "红包雨")
        binding.packetView.post {
            binding.packetView.getLocationOnScreen(surfaceLocation)
            Log.d(TAG, "width = ${binding.packetView.width}, height = ${binding.packetView.height}, left = ${surfaceLocation[0]}, top = ${surfaceLocation[1]}")
        }
    }

    fun startRedPacketRain(view: View) {
        val width = getScreenWidth(this).toFloat()
        val height = getScreenHeight(this).toFloat()
        val offset = 5.px2Dp(this)
        val startRegion = RectF(0f + offset, 0f + offset, width - offset, 100.px2Dp(this).toFloat() - offset)

        val bottomViewHeight = 100.px2Dp(this)
        val bottomDisappearHeight = 80.px2Dp(this)
        val viewHeight = binding.packetView.height.toFloat()
        val endRegion = RectF(0f + offset, viewHeight - (bottomViewHeight + bottomDisappearHeight), width - offset, viewHeight - bottomViewHeight)
        PacketManager.generateAnimationItem(this, "12", startRegion, endRegion, packetCount = 32, 75f)
        PacketManager.startRainAnimation(object : PacketManager.RainAnimationEnd {
            override fun onEnd() {
                binding.packetView.stopDrawing()
            }
        })
        binding.packetView.startDrawing()
    }
}