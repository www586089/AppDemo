package com.zfang.appdemo.view.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.customview.widget.ViewDragHelper

class DragViewGroup2(context: Context, attributeSet: AttributeSet): FrameLayout(context, attributeSet) {

    private var viewDragHelper: ViewDragHelper? = null

    init {
        viewDragHelper = ViewDragHelper.create(this, object : ViewDragHelper.Callback() {
            private var orignLeft = 0
            private var originTop = 0
            override fun onEdgeDragStarted(edgeFlags: Int, pointerId: Int) {
                viewDragHelper?.captureChildView(getChildAt(childCount - 1), pointerId)
            }
            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                orignLeft = child.left
                originTop = child.top
                return true
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                return left
            }

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                return top
            }

            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                viewDragHelper?.settleCapturedViewAt(orignLeft, originTop)
                invalidate()
            }
        })
        viewDragHelper?.setEdgeTrackingEnabled(ViewDragHelper.EDGE_ALL)
    }

    override fun computeScroll() {
        if (viewDragHelper?.continueSettling(true)!!) {
            invalidate()
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        viewDragHelper?.shouldInterceptTouchEvent(ev!!)
        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        viewDragHelper?.processTouchEvent(event!!)
        return true
    }
}