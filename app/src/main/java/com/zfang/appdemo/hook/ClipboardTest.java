package com.zfang.appdemo.hook;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

public class ClipboardTest {
    private static ClipboardManager mClipService;
    public static void test(Context ctx) {
        ClipboardManager clip = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = clip.getPrimaryClip();
        if (null != clipData && clipData.getItemCount() > 0) {
            String text = clipData.getItemAt(0).getText().toString();
            Log.d("zfang", "test read clipboard text =" + text);
        }


        initClipService(ctx);
        setClipContent(ctx);
    }

    private void rest() {
//        IBinder binder = ServiceManager
    }

    private static void initClipService(Context ctx) {
        if (mClipService == null) {
            mClipService = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
            mClipService.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
                @Override
                public void onPrimaryClipChanged() {

                }
            });
        }
    }

    private static void setClipContent(Context ctx){
        initClipService(ctx);
        ClipData data = ClipData.newPlainText("消息", "今天是2月14日");
        mClipService.setPrimaryClip(data);
    }
}
