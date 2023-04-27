package com.zfang.appdemo.activity.view.drag

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.SeekBar
import com.zfang.appdemo.base.BaseActivity
import com.zfang.appdemo.databinding.ActivityViewRotationBinding

class ViewRotationActivity : BaseActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, ViewRotationActivity::class.java))
        }

        private const val TAG = "ViewRotationActivity"
    }

    private lateinit var binding: ActivityViewRotationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewRotationBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initToolbar(title = "View旋转")
        init()
    }

    private fun init() {
        binding.seekBar.max = 100
        binding.seekBar.min = 0
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val rotation = (progress - 50).toFloat()
                    Log.e(TAG, "rotation = $rotation")
                    binding.frame.rotation = rotation
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}
