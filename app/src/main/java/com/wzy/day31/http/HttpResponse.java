package com.wzy.day31.http;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by zy on 2016/3/28.
 * 创建一个HTTPRESPONSE用来包装VOLLEY请求的两个回调函数
 * Response.Listener,Response,ErrorListener
 */
public abstract class HttpResponse {
    private static final String TAG=HttpResponse.class.getSimpleName();
    private Response.Listener<String> sucess;
    private Response.ErrorListener error;
    //
    public HttpResponse(){
        sucess=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG,"result------"+response);
                onSucceed(response);
            }
        };
        error=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"error------"+error);
                onSucceed(error.getMessage());
            }
        };
    }

    public Response.Listener<String> getSucess() {
        return sucess;
    }

    public Response.ErrorListener getError() {
        return error;
    }
    public abstract void onSucceed(String result);
    public abstract void onError(String message);
}
