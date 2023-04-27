package com.zfang.appdemo.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.zfang.appdemo.R
import com.zfang.appdemo.activity.test.ScrollTestActivity
import com.zfang.appdemo.base.BaseActivity
import com.zfang.appdemo.common.px2Dp
import com.zfang.appdemo.databinding.ActivityTestBinding
import com.zfang.appdemo.view.test.GuideInfo
import com.zfang.appdemo.view.test.GuideInfoHelper
import com.zfang.appdemo.view.test.TO_ANCHOR_BOTTOM
import com.zfang.appdemo.view.test.TO_ANCHOR_LEFT

class TestActivity : BaseActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, TestActivity::class.java))
        }
    }
    private lateinit var binding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initToolbar(title = "实验")
        supportActionBar?.hide()
        binding.guideView.post {
            val guideViewContent1 = LayoutInflater.from(this).inflate(R.layout.home_guide_layout_1, null)
            guideViewContent1.findViewById<AppCompatTextView>(R.id.guide_home_next).setOnClickListener {
                binding.guideView.showNext()
            }
            val guideViewContent2 = LayoutInflater.from(this).inflate(R.layout.home_guide_layout_2, null)
            guideViewContent2.findViewById<AppCompatTextView>(R.id.guide_home_know).setOnClickListener {
                binding.guideView.completeGuide()
            }
            val guideHomeNext = guideViewContent1.findViewById<AppCompatTextView>(R.id.guide_home_next)
            val guideHomeComplete = guideViewContent2.findViewById<AppCompatTextView>(R.id.guide_home_know)
            GuideInfoHelper.addGuideInfo(GuideInfo(anchorView = binding.pathEffect, clickView = guideHomeNext,
                guideViewContent = guideViewContent1, toAnchorDirection = TO_ANCHOR_LEFT, scale = 0.235f, cornerRadius = 5.px2Dp(this).toFloat()))
            GuideInfoHelper.addGuideInfo(GuideInfo(anchorView = binding.imageTest, clickView = guideHomeComplete,
                guideViewContent = guideViewContent2, toAnchorDirection = TO_ANCHOR_BOTTOM, scale = 0.38f, cornerRadius = 5.px2Dp(this).toFloat()))

            binding.guideView.showGuide(GuideInfoHelper)
        }
    }

    fun onClickScrollTest(view: View) {
        ScrollTestActivity.start(this)
    }

    fun showNextGuide(view: View) {
        val guideViewContent1 = LayoutInflater.from(this).inflate(R.layout.mine_guide_layout_1, null)
        guideViewContent1.findViewById<AppCompatTextView>(R.id.guide_mine_next).setOnClickListener {
            binding.guideView.showNext()
        }
        val guideViewContent2 = LayoutInflater.from(this).inflate(R.layout.mine_guide_layout_2, null)
        guideViewContent2.findViewById<AppCompatTextView>(R.id.guide_mine_know).setOnClickListener {
            binding.guideView.completeGuide()
        }
        val guideViewTextContent = guideViewContent2.findViewById<AppCompatTextView>(R.id.guide_mine_second_content)
        guideViewTextContent.text = getMineGuideContent()

        val guideHomeNext = guideViewContent1.findViewById<AppCompatTextView>(R.id.guide_mine_next)
        val guideHomeComplete = guideViewContent2.findViewById<AppCompatTextView>(R.id.guide_mine_know)
        GuideInfoHelper.addGuideInfo(GuideInfo(anchorView = binding.myEarn, clickView = guideHomeNext,
            guideViewContent = guideViewContent1, toAnchorDirection = TO_ANCHOR_BOTTOM, scale = 0.016f, cornerRadius = 0.px2Dp(this).toFloat()))
        GuideInfoHelper.addGuideInfo(GuideInfo(anchorView = binding.taskCenter, clickView = guideHomeComplete,
            guideViewContent = guideViewContent2, toAnchorDirection = TO_ANCHOR_BOTTOM, scale = 0.28f, cornerRadius = 18.px2Dp(this).toFloat(),gravity = 0.6f))

        binding.guideView.showGuide(GuideInfoHelper)
    }

    private fun getMineGuideContent() = SpannableStringBuilder("15秒带你玩转花盼，获得大量").apply {
        append("搭讪", ForegroundColorSpan(Color.parseColor("#FFE094")), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        append("和")
        append("搭讪", ForegroundColorSpan(Color.parseColor("#FFE094")), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}
