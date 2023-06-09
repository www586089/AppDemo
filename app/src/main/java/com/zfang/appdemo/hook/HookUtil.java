package com.zfang.appdemo.hook;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class HookUtil {


    private static final String TAG = "HookUtil";

    /**
     * 1 构造一个伪造的系统服务对象，这里的ClipboardService
     * 2
     */
    @SuppressLint({"DiscouragedPrivateApi", "PrivateApi"})
    public static void hookClipboard() {
        try {
            final String CLIPBOARD_SERVICE = "clipboard";

            /*
             * 下面这一段的意思实际就是: ServiceManager.getService("clipboard");
             * 只不过 ServiceManager这个类是@hide的
             */
            Class<?> serviceManagerClazz = Class.forName("android.os.ServiceManager");
            Method getServiceMethod = serviceManagerClazz.getDeclaredMethod("getService", String.class);
            /*
             * ServiceManager里面管理的原始的Clipboard Binder对象,一般来说这是一个Binder代理对象
             */
            IBinder clipboardRawBinder = (IBinder) getServiceMethod.invoke(null, CLIPBOARD_SERVICE);

            /*
             * Hook 掉这个Binder代理对象的 queryLocalInterface 方法,然后在 queryLocalInterface
             * 返回一个IInterface对象, hook掉我们感兴趣的方法即可.
             */
            ClassLoader classLoader = serviceManagerClazz.getClassLoader();
            Class<?>[] interfaces = new Class<?>[] { IBinder.class };
            InvocationHandler invocationHandler = new BinderProxyHookHandler(clipboardRawBinder);
            IBinder hookedBinder = (IBinder) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);

            /*
             * 把这个hook过的Binder代理对象放进ServiceManager的cache里面, 以后查询的时候 会优先
             * 查询缓存里面的Binder, 这样就会使用被我们修改过的Binder了
             */
            Field cacheField = serviceManagerClazz.getDeclaredField("sCache");
            cacheField.setAccessible(true);
            Map<String, IBinder> cacheMap = (Map) cacheField.get(null);
            cacheMap.put(CLIPBOARD_SERVICE, hookedBinder);
        } catch (ClassNotFoundException
                | NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static class BinderProxyHookHandler implements InvocationHandler {

        private static final String TAG = "BinderProxyHookHandler";


        /**
         * 绝大部分情况下,这是一个BinderProxy对象, 只有当Service和我们在同一个进程
         * 的时候才是Binder本地对象, 这个基本不可能
         */
        private final IBinder originBinder;
        private Class<?> clipboardStub;
        private Class<?> clipboardInterface;

        @SuppressLint("PrivateApi")
        public BinderProxyHookHandler(IBinder base) {
            this.originBinder = base;
            try {
                this.clipboardStub = Class.forName("android.content.IClipboard$Stub");
                this.clipboardInterface = Class.forName("android.content.IClipboard");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("queryLocalInterface".equals(method.getName())) {
                Log.d(TAG, "hook queryLocalInterface");

                /*
                 * 这里直接返回真正被Hook掉的Service接口, 这里的 queryLocalInterface 就不是原本的意思了,
                 * 我们肯定不会真的返回一个本地接口, 因为我们接管了 asInterface方法的作用, 因此必须是一个
                 * 完整的 asInterface 过的 IInterface对象, 既要处理本地对象,也要处理代理对象, 这只是一个
                 * Hook点而已, 它原始的含义已经被我们重定义了; 因为我们会永远确保这个方法不返回null, 让
                 * IClipboard.Stub.asInterface 永远走到if语句的else分支里面
                 */
                ClassLoader classLoader = proxy.getClass().getClassLoader();
                /*
                 * asInterface 的时候会检测是否是特定类型的接口然后进行强制转换,
                 * 因此这里的动态代理生成的类型信息的类型必须是正确的
                 */
                Class<?>[] interfaces = new Class[] { IBinder.class, IInterface.class, this.clipboardInterface};
                InvocationHandler invocationHandler = new BinderHookHandler(originBinder, clipboardStub);
                return Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
            }

            Log.d(TAG, "method:" + method.getName());
            return method.invoke(originBinder, args);
        }
    }

    /**
     * 系统服务对象
     */
    public static class BinderHookHandler implements InvocationHandler {

        private static final String TAG = "BinderHookHandler";

        // 原始的Service对象 (IInterface)
        private final Object originClipboardService;

        public BinderHookHandler(IBinder base, Class<?> stubClass) {
            try {
                Method asInterfaceMethod = stubClass.getDeclaredMethod("asInterface", IBinder.class);
                // IClipboard.Stub.asInterface(base);
                this.originClipboardService = asInterfaceMethod.invoke(null, base);
            } catch (Exception e) {
                throw new RuntimeException("hooked failed!");
            }
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            Log.d("hookClipboard", "hook clipboard, method = " + method.getName());
            // 把剪切版的内容替换为 "you are hooked"
            if ("getPrimaryClip".equals(method.getName())) {
                Throwable throwable = new Throwable("getPrimaryClip call stack");
                throwable.printStackTrace();
                Log.d("hookClipboard", "hook getPrimaryClip");
                return ClipData.newPlainText(null, "you are hooked");
            }

            // 欺骗系统,使之认为剪切版上一直有内容
            if ("hasPrimaryClip".equals(method.getName())) {
                return true;
            }

            return method.invoke(originClipboardService, args);
        }
    }
}
