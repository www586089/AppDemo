package com.zfang.appdemo.view.path.effect

import android.content.Context
import android.graphics.Path
import android.graphics.PathDashPathEffect
import android.graphics.PathEffect
import android.util.AttributeSet
import com.zfang.appdemo.common.px2Dp

class PathDashPathEffectView(context: Context?, attrs: AttributeSet?) : BasePathView(context, attrs) {

    var circlePath: Path = Path()
    init {
        circlePath.addCircle(0f, 0f, 2.px2Dp(context!!).toFloat(), Path.Direction.CCW)
    }
    //动态改变改值会产生动画效果
    var mPhase = 0f
    override fun getPathEffect(): PathEffect? {
//        mPhase++
//        invalidate()
        return PathDashPathEffect(circlePath, 6.px2Dp(context).toFloat(), mPhase, PathDashPathEffect.Style.TRANSLATE)
    }
}