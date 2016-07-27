package com.wzy.teachlibrary.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by zy on 2016/3/28.
 */
public class HttpUtil {
    private static RequestQueue queue1;
    private static RequestQueue queue2;

    private static String response1;
    private static String response2;

    public static void init(Context context) {
        queue1 = Volley.newRequestQueue(context);
        queue2 = Volley.newRequestQueue(context);
    }

    public static String get(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                response1=response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue1.add(stringRequest);
        return response1;
    }

    public static String post(String url, final Map<String, String> params) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                response2=response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        queue2.add(stringRequest);
        return response2;

    }

}
