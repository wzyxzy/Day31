package com.wzy.day31.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

/**
 * Created by zy on 2016/3/28.
 */
public class JsonRequest<T> extends Request<T> {

    private final Response.Listener<T> listener;
    private final Class<T> cls;
    private final Gson gson;

    public JsonRequest(int method, String url, Class<T> cls, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.cls = cls;
        this.listener = listener;
        this.gson=new Gson();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String result = new String(response.data);
        T t = gson.fromJson(result, cls);

        return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        this.listener.onResponse(response);
    }
}
