package com.zfang.appdemo.utils

import android.content.Context

fun getScreenHeight(ctx: Context): Int {
    val display = ctx.resources.displayMetrics;
    return display.heightPixels;
}