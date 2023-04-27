package com.zfang.appdemo.view.matrix

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.zfang.appdemo.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class BasicMatrixView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var bitmap: Bitmap? = null
    private var bitmapMatrix: Matrix = Matrix()
    private var bitmapPaint: Paint = Paint()
    private var canvasWidth: Int = 0
    private var canvasHeight: Int = 0
    private val mutex = Mutex()
    private var bitmapWidth = 0
    private var bitmapHeight = 0
    private var lifeCycleScope = (context as? AppCompatActivity)?.lifecycleScope

    init {
        lifeCycleScope?.launchWhenStarted {
            loadBitmap()
        }
    }

    private suspend fun loadBitmap() = coroutineScope {
        bitmap = BitmapFactory.decodeResource(context!!.resources, R.drawable.home_car_instalment)
        bitmap?.apply {
            bitmapWidth = width
            bitmapHeight = height
        }
        if (canvasWidth > 0) {
            doMatrixInit()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            bitmap?.let { drawBitmap(it, bitmapMatrix, bitmapPaint) }

            bitmapMatrix.reset()
            bitmap?.let { drawBitmap(it, bitmapMatrix, bitmapPaint) }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (measuredWidth > 0) {
            canvasWidth = measuredWidth
            canvasHeight = measuredHeight
            if (bitmapWidth > 0) {
                lifeCycleScope?.launchWhenStarted { doMatrixInit() }
            }
        }
    }

    private suspend fun doMatrixInit() = coroutineScope {
        mutex.withLock {
            //重置为单位矩阵，否则后面的计算将会无效
            bitmapMatrix.reset()
            /**
             * 把绘制坐标移动到相应应计算出来的点使得绘制出来的图像是整体居中显示的
             * setTranslate也可以移动中心
             */
            /**
             * 把绘制坐标移动到相应应计算出来的点使得绘制出来的图像是整体居中显示的
             * setTranslate也可以移动中心
             */
            bitmapMatrix.postTranslate(((canvasWidth - bitmapWidth) / 2).toFloat(), ((canvasHeight - bitmapHeight) / 2).toFloat())
        }
    }
}