package com.yun.utils.image

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.text.TextUtils
import android.util.Log
import androidx.annotation.ColorInt
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.zfang.appdemo.common.px2Dp
import java.util.*


/**
 * 二维码生成工具类
 */
object QRCodeUtil {

    val IMAGE_HALFWIDTH = 50;//宽度值，影响中间图片大小
    /**
     * 创建二维码位图 (支持自定义配置和自定义样式)
     * @param url          字符串内容
     * @param width            位图宽度,要求>=0(单位:px)
     * @param height           位图高度,要求>=0(单位:px)
     * @param charSet    字符集/字符转码格式 (支持格式:[])。传null时,zxing源码默认使用 "ISO-8859-1"
     * @param errorCorrection 容错级别 (支持级别:[])。传null时,zxing源码默认使用 "L"
     * @param margin           空白边距 (可修改,要求:整型且>=0), 传null时,zxing源码默认使用"4"。
     * @param color_black      黑色色块的自定义颜色值
     * @param color_white      白色色块的自定义颜色值
     * @return
     */
    @JvmOverloads
    fun createQRCodeBitmap(ctx: Context,
        url: String, width: Int, height: Int,
        charSet: String? = "UTF-8", errorCorrection: String? = "H", margin: String? = "0",
        @ColorInt color_black: Int = Color.BLACK, @ColorInt color_white: Int = Color.WHITE
    ): Bitmap? {
        /** 1.参数合法性判断  */
        if (TextUtils.isEmpty(url)) { // 字符串内容判空
            return null
        }
        if (width < 0 || height < 0) { // 宽和高都需要>=0
            return null
        }
        try {
            /** 2.设置二维码相关配置,生成BitMatrix(位矩阵)对象  */
            val hints = Hashtable<EncodeHintType, String>()
            if (!TextUtils.isEmpty(charSet)) {
                hints[EncodeHintType.CHARACTER_SET] = charSet // 字符转码格式设置
            }
            if (!TextUtils.isEmpty(errorCorrection)) {
                hints[EncodeHintType.ERROR_CORRECTION] = errorCorrection // 容错级别设置
            }
            if (!TextUtils.isEmpty(margin)) {
                hints[EncodeHintType.MARGIN] = margin // 空白边距设置
            }
            val bitMatrix = QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints)
//            var logoBitmap = BitmapFactory.decodeResource(ctx.resources, R.mipmap.ic_launcher)
//            val imageHalfWidth = width / 10// 原图1/5的一半
//            val imageHalfHeight = height / 10
//            val halfWidth = width / 2
//            val halfHeight = height / 2
//            val matrix = Matrix()
//            val sx = (2f * imageHalfWidth) / logoBitmap.width
//            val sy = (2f * imageHalfWidth) / logoBitmap.height
//            matrix.setScale(sx, sy)
//            logoBitmap = Bitmap.createBitmap(logoBitmap, 0, 0, logoBitmap.width, logoBitmap.height, matrix, false)

            /** 3.创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值*/
            val pixels = IntArray(width * height)
            for (y in 0 until height) {
                for (x in 0 until width) {
                    /*if (x > halfWidth - imageHalfWidth && x < halfWidth + imageHalfWidth
                            && y > halfHeight - imageHalfWidth && y < halfHeight + imageHalfWidth) {
                        pixels[y * width + x] = logoBitmap.getPixel(x - halfWidth + imageHalfWidth, y - halfHeight + imageHalfHeight)
                    } else*/
                    {

                    }
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = color_black // 黑色色块像素设置
                    } else {
                        pixels[y * width + x] = color_white // 白色色块像素设置
                    }
                }
            }
            /** 4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,之后返回Bitmap对象  */
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
            return bitmap
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        return null
    }

    fun addLogo2(qrBitmap: Bitmap, logoBitmap: Bitmap): Bitmap {
        val qrWidth = qrBitmap.width
        val qrHeight = qrBitmap.height
        val logoWidth = logoBitmap.width
        val logoHeight = logoBitmap.height

        if (0 == qrWidth || 0 == qrHeight || 0 == logoWidth || 0 == logoHeight) {
            return qrBitmap
        }

        val scaleFactor = (qrWidth / 5f) / logoWidth
        Log.e("zfang", "qrWidth = $qrWidth, logoWidth = $logoWidth, scaleFactor = $scaleFactor")
        val finalBitmap = Bitmap.createBitmap(qrWidth, qrHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(finalBitmap)

        canvas.save()
        canvas.drawBitmap(qrBitmap, 0f, 0f, null)
        canvas.scale(scaleFactor, scaleFactor, (qrWidth / 2).toFloat(), (qrHeight / 2).toFloat())
        canvas.drawBitmap(logoBitmap, ((qrWidth - logoWidth) / 2).toFloat(), ((qrHeight - logoHeight) / 2).toFloat(), null)
        canvas.restore()

        return finalBitmap
    }

    /**
     * 添加LOGO
     */
    fun addLogo(qrBitmap: Bitmap, logoBitmap: Bitmap): Bitmap {
        val qrBitmapWidth = qrBitmap.width
        val qrBitmapHeight = qrBitmap.height

        val logoBitmapWidth = logoBitmap.width
        val logoBitmapHeight = logoBitmap.height

        val blankBitmap = Bitmap.createBitmap(qrBitmapWidth, qrBitmapHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(blankBitmap)
        canvas.drawBitmap(qrBitmap, 0f, 0f, null)
        //canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.save()
        var scaleSize = 1.0f
        while (logoBitmapWidth / scaleSize > qrBitmapWidth / 3.5 || logoBitmapHeight / scaleSize > qrBitmapHeight / 3.5) {
            scaleSize *= 2f
        }
        val sx = 1.0f / scaleSize
        canvas.scale(sx, sx, (qrBitmapWidth / 2).toFloat(), (qrBitmapHeight / 2).toFloat())
        canvas.drawBitmap(logoBitmap, ((qrBitmapWidth - logoBitmapWidth) / 2).toFloat(), ((qrBitmapHeight - logoBitmapHeight) / 2).toFloat(), null)
        canvas.restore()
        return blankBitmap
    }


    fun mergeBitmap(ctx: Context, originBitmap: Bitmap?, qrBitmap: Bitmap?, left: Float, top: Float, textX: Float, textY: Float): Bitmap? {
        if (originBitmap == null || qrBitmap == null) return null
        val originBitmapWidth = originBitmap.width
        val originBitmapHeight = originBitmap.height
        val bitmap = Bitmap.createBitmap(originBitmapWidth, originBitmapHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawBitmap(originBitmap, 0f, 0f, null)

        val paint = TextPaint()
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 12f
        paint.textSize = 20.px2Dp(ctx).toFloat()
        val msg = "邀请码ID:123456"
        val textBounds = Rect()
        paint.getTextBounds(msg, 0, msg.length, textBounds)
        canvas.drawText(msg, (textX - textBounds.width() / 2).toFloat(), textY - (textBounds.top + textBounds.bottom) / 2, paint)


        canvas.save()
        canvas.drawBitmap(qrBitmap, left, top, null)
        canvas.restore()
        return bitmap
    }

}

