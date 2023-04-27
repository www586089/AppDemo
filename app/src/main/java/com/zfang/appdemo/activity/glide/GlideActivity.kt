package com.zfang.appdemo.activity.glide

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.FutureTarget
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.zfang.appdemo.R
import com.zfang.appdemo.activity.glide.progress.ProgressInterceptor
import com.zfang.appdemo.activity.glide.progress.ProgressListener
import com.zfang.appdemo.activity.glide.transform.CircleCrop
import com.zfang.appdemo.base.BaseActivity
import com.zfang.appdemo.databinding.ActivityGlideBinding
import com.zfang.appdemo.view.glide.MyLayout
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.GrayscaleTransformation
import java.io.File
import kotlin.concurrent.thread

class GlideActivity  : BaseActivity() {
    companion object {
        //https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=4122597421,1961510560&os=1794850309,2296073297&simid=3326141625,67930705&pn=57&rn=1&di=7146857200093233153&ln=1819&fr=&fmq=1665930225634_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fimg.jj20.com%252Fup%252Fallimg%252F1115%252F092621094420%252F210926094420-8-1200.jpg%26refer%3Dhttp%253A%252F%252Fimg.jj20.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Dauto%3Fsec%3D1668522226%26t%3D0a9daa871b510d0ff262de9afd40c2ae&rpstart=0&rpnum=0&adpicid=0&nojc=undefined&dyTabStr=MCwzLDIsNSw4LDEsNCw2LDcsOQ%3D%3D
        val url = "https://img1.baidu.com/it/u=2946246786,1305711472&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500"
        val gifUrl = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.soogif.com%2FDOyQIe3zclOhy31YzajY6mY48BaHUcNU.gif&refer=http%3A%2F%2Fimg.soogif.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1668523481&t=8dcc7f613effadc29838a43fe5d84419"
        //val gifUrl = "https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87%20gif&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=2704440335,1264384879&os=1103832632,3116612948&simid=3479260021,392707691&pn=33&rn=1&di=7146857200093233153&ln=1011&fr=&fmq=1665931481666_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fhbimg.b0.upaiyun.com%252Fb25515c32f0610a2a81ceba529b984e7ddff1dc0232b07-cuBBou_fw658%26refer%3Dhttp%253A%252F%252Fhbimg.b0.upaiyun.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Dauto%3Fsec%3D1668523482%26t%3Db04fa83c719acf82250db288a9713d8e&rpstart=0&rpnum=0&adpicid=0&nojc=undefined&dyTabStr=MCwxLDMsNiw0LDUsOCw3LDIsOQ%3D%3D"
        val loading = R.drawable.icon_star
        val error = R.drawable.icon_liuxing
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, GlideActivity::class.java))
        }
    }

    private lateinit var binding: ActivityGlideBinding
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlideBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initToolbar(title = "Glide用法")
        init()
    }

    private fun init() {
        ProgressDialog(this).apply {
            progressDialog = this
            setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            setMessage("Loading... ...")
        }
    }

    fun loadImage(view: View) {
//        Glide.with(this).load(url).into(imageView)
        Glide.with(this)
            .load(R.drawable.image_local)
//            .fitCenter()
            .bitmapTransform(CircleCrop(this), BlurTransformation(this), GrayscaleTransformation(this))
            .into(binding.imageView)
    }

    fun loadImagePlaceHolder(view: View) {
        Glide.with(this)
            .load(url)
            .placeholder(loading)
            .error(error)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(binding.imageView)
    }

    fun loadGif(view: View) {
//        loadAsBitmap(gifUrl)
        loadAsGif(gifUrl)
    }

    private fun loadAsBitmap(url: String) {
        Glide.with(this)
            .load(url)
            .asBitmap()
            .placeholder(loading)
            .error(error)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(binding.imageView);
    }

    private fun loadAsGif(url: String) {
        ProgressInterceptor.addListener(url, object: ProgressListener {

            override fun onProgress(progress: Int) {
                progressDialog.setProgress(progress)
            }
        })
//        Glide.with(this)
//            .load(url)
//            .asGif()
//            .placeholder(loading)
//            .error(error)
//            .diskCacheStrategy(DiskCacheStrategy.NONE)
//            .into(imageView)

        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .into(object: GlideDrawableImageViewTarget(binding.imageView) {
                override fun onLoadStarted(placeholder: Drawable?) {
                    super.onLoadStarted(placeholder)
                    progressDialog.show();
                }

                override fun onResourceReady(resource: GlideDrawable?, animation: GlideAnimation<in GlideDrawable>?) {
                    super.onResourceReady(resource, animation)
                    progressDialog.dismiss()
                    ProgressInterceptor.removeListener(url)
                }
            })
    }

    fun loadWithConstraint(view: View) {
//        val url = "https://img2.baidu.com/it/u=1002466511,344372075&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500"
//        val url = "https://img2.baidu.com/it/u=1803383139,3209928988&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=602"
        val url = "https://img2.baidu.com/it/u=3588294612,1795376586&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500"
        Glide.with(this)
            .load(url)
//            .load(GlideUrl(""))
            .placeholder(loading)
            .error(error)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .override(300, 300)
            .into(binding.imageView)
    }

    fun loadImageViewTarget(view: View) {
        val url = "http://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg"
        (view as? MyLayout)?.let {
            Glide.with(this)
                .load(url)
                .into(it.getTarget())
        }
    }

    fun loadBitmapTarget(view: View) {
        val simpleTarget = object : SimpleTarget<GlideDrawable>() {
            override fun onResourceReady(resource: GlideDrawable?, glideAnimation: GlideAnimation<in GlideDrawable>?) {
                resource?.let { (view as? ImageView)?.setImageDrawable(it) }
            }
        }
        val simpleTargetBitmap = object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                resource?.let { (view as? ImageView)?.setImageBitmap(it) }
            }
        }
        val url = "http://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg";
        Glide.with(this)
            .load(url)
            .into(simpleTarget)
    }


    /**
     * 1 preload
     */
    fun preloadImageView(view: View) {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .preload()
    }

    /**
     * 2 use preload image
     */
    fun usePreloadImageView(view: View) {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(binding.imageView)
    }

    fun loadWithListener(view: View) {
        val url = "http://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg"
        Glide.with(this)
            .load(url)
            .listener(object: RequestListener<String, GlideDrawable> {
                override fun onException(e: java.lang.Exception?, model: String?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
                    return false
                }

                override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                    return false
                }
            })
            .into(binding.imageView)
    }

    fun downloadOnly(view: View) {
        thread {
            try {
                val url = "http://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg"
                val context = getApplicationContext()
                val target: FutureTarget<File> = Glide.with(context)
                    .load(url)
                    .downloadOnly(SIZE_ORIGINAL, SIZE_ORIGINAL)
                val imageFile = target.get();
                runOnUiThread { Toast.makeText(context, imageFile.getPath(), Toast.LENGTH_LONG).show() }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun downloadUseCustomTarget(view: View) {
        val url = "http://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg";
        Glide.with(this)
            .load(url)
            .downloadOnly( DownloadImageTarget())
    }

    class DownloadImageTarget : com.bumptech.glide.request.target.Target<File> {
        val TAG = "DownloadImageTarget"
        override fun onStart() {

        }

        override fun onStop() {
        }

        override fun onDestroy() {
        }

        override fun onLoadStarted(placeholder: Drawable?) {
        }

        override fun onLoadFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {
        }

        override fun onResourceReady(resource: File?, glideAnimation: GlideAnimation<in File>?) {
            Log.d(TAG, resource?.getPath() ?: "failure")
        }

        override fun onLoadCleared(placeholder: Drawable?) {
        }

        override fun getSize(cb: SizeReadyCallback?) {
            cb?.onSizeReady(SIZE_ORIGINAL, SIZE_ORIGINAL);
        }

        override fun setRequest(request: Request?) {
        }

        override fun getRequest(): Request? {
            return null
        }

    }
}