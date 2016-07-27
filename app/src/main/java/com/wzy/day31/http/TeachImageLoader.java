package com.wzy.day31.http;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.wzy.day31.R;

/**
 * Created by zy on 2016/3/28.
 */
public class TeachImageLoader {
    private static TeachImageLoader imageLoader;
    private static RequestQueue queue;
    //1私有化构造
    private TeachImageLoader(Context context){
        queue= Volley.newRequestQueue(context);
    }
    //2对外提供获取实例的方法
    public static TeachImageLoader getInstance(Context context){
        if (imageLoader==null){
            synchronized (TeachImageLoader.class){
                if (imageLoader==null){
                    imageLoader=new TeachImageLoader(context);
                }
            }
        }

        return imageLoader;
    }
    public static void display(String url, ImageView container,int imageDefaultEesId,int imageErrorResId){
        //实例化imageloader 1RequestQueue 2ImageCache
        ImageLoader imageLoader=new ImageLoader(queue,TeachImageCache.getInstance());
        //
//   //获取ImageListener
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(container, imageDefaultEesId, imageErrorResId);
        //加载图片
        imageLoader.get(url,imageListener);
    }
    public static void display(String url,ImageView container){
        display(url,container, R.mipmap.ic_launcher,R.mipmap.ic_launcher);
    }
}
