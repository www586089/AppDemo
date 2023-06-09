package com.zfang.appdemo.view.matrix

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.zfang.appdemo.R

class SkewMatrixView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var bitmap: Bitmap = BitmapFactory.decodeResource(context!!.resources,
        R.drawable.home_car_instalment
    )
    private var bitmapMatrix: Matrix = Matrix()
    private var bitmapPaint: Paint? = null
    private var canvasWidth: Int = 0
    private var canvasHeight: Int = 0
    private var bitmapWidth = 0
    private var bitmapHeight = 0

    init {
        bitmapPaint = Paint()
        bitmapWidth = bitmap.width
        bitmapHeight = bitmap.height
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //在没有平移之前默认在左上角绘制
        bitmapMatrix.reset()
        canvas!!.drawBitmap(bitmap, bitmapMatrix, bitmapPaint)

        //平移之后在中心开始绘制
        bitmapMatrix.postTranslate((canvasWidth / 2 - bitmapWidth / 2).toFloat(), (canvasHeight / 2 - bitmapHeight / 2).toFloat())
        //错切
        bitmapMatrix.preSkew(1f, 0f)
        canvas.drawBitmap(bitmap, bitmapMatrix, bitmapPaint!!)

        bitmapPaint!!.alpha = 0x77
        canvas.drawCircle((canvasWidth / 2).toFloat(),
            (canvasHeight / 2).toFloat(), (canvasWidth / 16).toFloat(), bitmapPaint!!)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (measuredWidth > 0) {
            canvasWidth = measuredWidth
            canvasHeight = measuredHeight
        }
    }
}