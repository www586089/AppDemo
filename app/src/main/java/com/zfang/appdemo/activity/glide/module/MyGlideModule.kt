package com.zfang.appdemo.activity.glide.module

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.GlideModule
import com.zfang.appdemo.activity.glide.module.loader.OkHttpGlideUrlLoader
import com.zfang.appdemo.activity.glide.progress.ProgressInterceptor
import okhttp3.OkHttpClient
import java.io.InputStream

class MyGlideModule: GlideModule {
    val DISK_CACHE_SIZE = 500 * 1024 * 1024 // 500M
    override fun applyOptions(context: Context?, builder: GlideBuilder?) {
        builder?.apply {
            setDiskCache(ExternalCacheDiskCacheFactory(context, DISK_CACHE_SIZE))
            setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)
        }
    }

    override fun registerComponents(context: Context?, glide: Glide?) {
        glide?.apply {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(ProgressInterceptor())
            val okHttpClient = builder.build()
            register(GlideUrl::class.java, InputStream::class.java,  OkHttpGlideUrlLoader.Factory(okHttpClient))
        }
    }
}