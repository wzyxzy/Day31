package com.wzy.day31.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wzy.day31.R;

import java.util.Map;

/**
 * Created by zy on 2016/3/28.
 */
public class HttpUtil {
    private static RequestQueue queue;
    //对外暴露初始化方法
    public static void init(Context context){
        queue= Volley.newRequestQueue(context);
    }
    public static void getString(String url,HttpResponse response){
        //
        if (queue==null){
            throw new NullPointerException("空指针异常");
        }

        //实例化一个Request
        StringRequest stringRequest=new StringRequest(Request.Method.GET,url,response.getSucess(),response.getError());
        //添加到请求队列
        queue.add(stringRequest);
    }
    public static void postString(String url, final Map<String,String> params, HttpResponse response){
        //获取一个RequestQueue

        //实例化一个Request
        StringRequest stringRequest=new StringRequest(Request.Method.POST,url,response.getSucess(),response.getError()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        //添加到请求队列
        queue.add(stringRequest);
    }
    public static void display(String url, int maxWidth, int maxHeight, final ImageView container,final int errorRes){
        //获取requestQueue

        //
        ImageRequest imageRequest=new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                container.setImageBitmap(response);
            }
        }, maxWidth, maxHeight, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                container.setImageResource(errorRes);
            }
        }){
            @Override
            public Priority getPriority() {
                return Priority.LOW;
            }
        };
        //添加到请求队列中
        queue.add(imageRequest);
    }
    public static void display(String url,ImageView container){
        display(url,300,300,container, R.mipmap.ic_launcher);
    }
    public static void cancelRequest(String tag){
        queue.cancelAll(tag);
    }
    public static void getString(String url,String tag,HttpResponse response){
        //
        if (queue==null){
            throw new NullPointerException("空指针异常");
        }

        //实例化一个Request
        StringRequest stringRequest=new StringRequest(Request.Method.GET,url,response.getSucess(),response.getError());
        //为请求添加一个tag
        stringRequest.setTag(tag);
        //添加到请求队列
        queue.add(stringRequest);
    }
}
