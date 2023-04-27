package com.zfang.appdemo.activity.window

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.PopupMenu
import com.zfang.appdemo.R
import com.zfang.appdemo.base.BaseActivity
import com.zfang.appdemo.common.px2Dp

class PopWindowActivity: BaseActivity() {
    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, PopWindowActivity::class.java))
        }
    }

    private var popMenu: PopupMenu? = null

    private val SORT_TYPE_TIME = 0
    private val SORT_TYPE_INTIMACY = 1

    private var popupWindow: PopupWindow? = null
    private var currentSort = SORT_TYPE_INTIMACY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popwindow)
        initToolbar(title = "PopWindow(菜单)")
    }

    fun onClickPopupWindow(view: View) {
        if (null == popMenu) {
            popMenu = PopupMenu(this, view, Gravity.RIGHT)
            menuInflater.inflate(R.menu.sort_pop_menu, popMenu!!.menu)
            popMenu!!.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.time_sort -> {
                        Toast.makeText(this, "时间排序", Toast.LENGTH_SHORT).show()
                    }
                    R.id.intimacy_sort -> {
                        Toast.makeText(this, "亲密度排序", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
        }
        popMenu?.show()
    }
    fun onClickPopMenu(view: View) {
        Log.e("zfang", "onClickPopMenu")
        if (null == popupWindow) {
            val contentView = LayoutInflater.from(this).inflate(R.layout.intimacy_msg_sort_layout, null)
            setPopupWindowView(contentView)
            popupWindow = PopupWindow(contentView, 140.px2Dp(this), 98.px2Dp(this))
            popupWindow?.setBackgroundDrawable(ColorDrawable())
            popupWindow?.isOutsideTouchable = true
        }

        val viewHeight = view.height
        val viewWidth = view.width
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        Log.e("zfang", "x = ${location[0]}, y = ${location[1]}, height = ${viewHeight}, width = ${view.width}")
        val y = location[1] + getStatusBarHeight()
        val arrowXToStart = 99.px2Dp(this)
        val diff = (location[0] + viewWidth / 2) - (location[0] + arrowXToStart)
        val x = diff + location[0]

        popupWindow?.showAtLocation(view, Gravity.NO_GRAVITY, x, y)
    }

    private fun setPopupWindowView(view: View) {
        val timeSort = view.findViewById<AppCompatTextView>(R.id.sort_time)
        val intimacySort = view.findViewById<AppCompatTextView>(R.id.sort_intimacy)
        setDefaultSortView(timeSort, intimacySort)
        timeSort.setOnClickListener {
            timeSort(timeSort, intimacySort)
            popupWindow?.dismiss()
            Toast.makeText(this, "时间排序", Toast.LENGTH_SHORT).show()
        }
        intimacySort.setOnClickListener {
            intimacySort(timeSort, intimacySort)

            popupWindow?.dismiss()
            Toast.makeText(this, "亲密度排序", Toast.LENGTH_SHORT).show()
        }
    }

    private fun timeSort(timeSort: AppCompatTextView, intimacySort: AppCompatTextView) {
        setSelected(timeSort)
        setUnSelected(intimacySort)
    }

    private fun intimacySort(timeSort: AppCompatTextView, intimacySort: AppCompatTextView) {
        setSelected(intimacySort)
        setUnSelected(timeSort)
    }

    private fun setDefaultSortView(timeSort: AppCompatTextView, intimacySort: AppCompatTextView) {
        when (currentSort) {
            SORT_TYPE_TIME -> {
                timeSort(timeSort, intimacySort)
            }
            SORT_TYPE_INTIMACY -> {
                setSelected(intimacySort)
                intimacySort(timeSort, intimacySort)
            }
        }
    }

    private fun setSelected(textView: AppCompatTextView) {
        textView.setBackgroundResource(R.drawable.bg_intimacy_msg_sort)
        textView.setTextColor(Color.WHITE)
    }


    private fun setUnSelected(textView: AppCompatTextView) {
        textView.setBackgroundResource(R.drawable.bg_intimacy_msg_sort_unselected)
        textView.setTextColor(Color.parseColor("#333333"))
    }

    private fun getStatusBarHeight(): Int {
        var statusBarHeight = -1
        val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resId)
        }
        Log.e("zfang", "statusBarHeight = $statusBarHeight")
        return statusBarHeight
    }
}