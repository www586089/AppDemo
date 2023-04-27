package com.zfang.appdemo.activity.qr

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import com.yun.utils.image.QRCodeUtil
import com.zfang.appdemo.R
import com.zfang.appdemo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_qr_code.*

class QRCodeActivity : BaseActivity() {
    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, QRCodeActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)
        initToolbar(title = "QRCode")
    }

    fun genQRCode2(view: View) {
        val url = "https://www.baidu.com/"
        val originBitmap = BitmapFactory.decodeResource(resources, R.drawable.logo)
        val viewWidth = originBitmap.width
        val viewHeight = originBitmap.height
        Log.e("zfang", "viewWidth = $viewWidth, viewHeight = $viewHeight")

        val qrWidth = (0.2 * viewHeight).toInt()
        val qrHeight = qrWidth
        val logoBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        val qrCodeBitmap = QRCodeUtil.createQRCodeBitmap(this, url, qrWidth, qrHeight)
        if (null != qrCodeBitmap) {
            val qrWithLogoBitmap = QRCodeUtil.addLogo2(qrCodeBitmap, logoBitmap)


            val textX = (0.34 * viewWidth).toFloat()
            val textY = (0.52 * viewHeight).toFloat()
            val qrLeft = (0.325 * viewWidth).toFloat()
            val qrTop = (0.745 * viewHeight).toFloat()
            val resultBitmap = QRCodeUtil.mergeBitmap(this, originBitmap, qrWithLogoBitmap, qrLeft, qrTop, textX, textY)
            qrImage.setImageBitmap(resultBitmap)
        }
    }

    fun genQRCode(view: View) {
        val qrConfigBean = QRConfigBean()
        val originBitmap = BitmapFactory.decodeResource(resources, R.drawable.logo)
        val bgWidth = originBitmap.width
        val bgHeight = originBitmap.height
        Log.e("zfang", "bgWidth = $bgWidth, bgHeight = $bgHeight")

        val qrWidth = qrConfigBean.qrCodeArea.width
        val qrHeight = qrConfigBean.qrCodeArea.height
        val logoBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher)
        val qrCodeBitmap = QRCodeUtil.createQRCodeBitmap(this, qrConfigBean.url, qrWidth, qrHeight)
        if (null != qrCodeBitmap) {
            val qrWithLogoBitmap = QRCodeUtil.addLogo2(qrCodeBitmap, logoBitmap)


            val textX = qrConfigBean.inviteTextArea.positionX.toFloat()
            val textY = qrConfigBean.inviteTextArea.positionY.toFloat()
            val qrLeft = qrConfigBean.qrCodeArea.positionX.toFloat()
            val qrTop = qrConfigBean.qrCodeArea.positionY.toFloat()
            val resultBitmap = QRCodeUtil.mergeBitmap(this, originBitmap, qrWithLogoBitmap, qrLeft, qrTop, textX, textY)
            qrImage.setImageBitmap(resultBitmap)
        }
    }
}