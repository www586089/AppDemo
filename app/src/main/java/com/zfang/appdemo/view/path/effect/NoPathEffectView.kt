package com.zfang.appdemo.view.path.effect

import android.content.Context
import android.graphics.PathEffect
import android.util.AttributeSet

class NoPathEffectView(context: Context?, attrs: AttributeSet?) : BasePathView(context, attrs) {

    override fun getPathEffect(): PathEffect? {
        return null
    }
}