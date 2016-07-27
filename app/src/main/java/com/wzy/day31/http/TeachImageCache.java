package com.wzy.day31.http;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by zy on 2016/3/28.
 */
public class TeachImageCache implements ImageLoader.ImageCache {
    private static final String TAG = TeachImageCache.class.getSimpleName();
    //
    private static TeachImageCache imageCache;
    private static LruCache<String, Bitmap> lruCache;

    private TeachImageCache() {
        //获取系统最大的可用内存
        long maxMemory = Runtime.getRuntime().maxMemory();
        int maxSize = (int) (maxMemory / 8);
        //实例化LruCache
        lruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //重写Bitmap大小的计算方式
                return value.getRowBytes() * value.getHeight();
            }
        };

    }

    /**
     * @return
     */
    public static TeachImageCache getInstance() {
        if (imageCache == null) {
            synchronized (TeachImageCache.class) {
                if (imageCache == null) {
                    imageCache = new TeachImageCache();
                }
            }
        }
        return imageCache;
    }

    @Override
    public Bitmap getBitmap(String url) {
        Log.e(TAG, "getBitmap----" + Thread.currentThread().getName());
        return lruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        Log.e(TAG, "putBitmap----" + Thread.currentThread().getName());
        lruCache.put(url, bitmap);
    }
}
