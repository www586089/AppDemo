package com.zfang.appdemo.base

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.zfang.appdemo.R

open class BaseActivity() : AppCompatActivity() {

    private val TAG = "BaseActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> {
                Log.e(TAG, "nothing todo")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    protected fun initToolbar(displayHomeAsUp: Boolean = true, title: String = getString(R.string.app_name)) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle(title)
    }
}