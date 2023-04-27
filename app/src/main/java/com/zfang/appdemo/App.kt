package com.zfang.appdemo

import android.app.Application
import android.content.Context
import com.zfang.appdemo.hook.ClipboardTest
import com.zfang.appdemo.hook.HookUtil

class App: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
//        HookUtil.hookClipboard(base)
        HookUtil.hookClipboard()
        ClipboardTest.test(base)
    }
}