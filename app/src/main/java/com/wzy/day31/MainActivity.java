package com.wzy.day31;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wzy.day31.http.HttpResponse;
import com.wzy.day31.http.HttpUtil;
import com.wzy.day31.http.TeachImageLoader;
import com.wzy.day31.model.BigModel;
import com.wzy.day31.model.Model;
import com.wzy.day31.request.JsonRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Volley是谷歌咋unianIO大会上退出的一个异步网络请求框架,适合小而多的网络请求
 * <p/>
 * 基本实用
 * 1获取RequestQueue
 * 2实例化Request
 * 3将Request添加到RequestQueue中
 * <p/>
 * <p/>
 * HttpUtil封装
 * 1 创建HttpUtil,写静态请求方法
 * 2 去写方法中的具体实现:1获取RequestQueue(直接声明了一个全局静态的)
 * 2实例化Request,将请求参数传递进来(我们要尽量与Volley解耦,将Volley的两个回调封装到一个类里面)
 * 3添加到RequestQueue中
 * 3 提供RequestQueue的实例化方法.(写在Application中,因为Application只创建一次.在Application中调用初始化,可以有效避免多线程安全问题)
 * 1.创建一个类继承自Application
 * 2.在Manifest文件中进行注册,name="";
 * 3.重写Applicationz红的onCreate方法,在onCreate中完成HttpUtil的初始化
 * 4 完成HttpResponse,在HttpResponse构造的时候对两个回调进行初始化
 * 因为在安卓开发过程中,我们每次的网络请求,返回的结果对我们来说都至关重要,我们需要根据结果进行不同的业务逻辑操作
 * 我们写了两个抽象方法,用来将Volley的两个回调的结果传递回调用处,这样我们就需要将HttpResponse变成抽象类,在实现方法
 * 这样我们就可以直接处理请求结果了
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String path = "http://apiv2.vmovier.com/api/post/getPostInCate?cateid=0&p=1";
    private static final String path1 = "http://apiv2.vmovier.com/api/post/getPostInCate?cateid=0";
    private Button volley_get;
    private Button volley_post;
    private Button http_get;
    private Button http_post;
    private TextView tv;
    private Button http_image;
    private ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        volley_get = (Button) findViewById(R.id.volley_get);

        volley_get.setOnClickListener(this);
        volley_post = (Button) findViewById(R.id.volley_post);
        volley_post.setOnClickListener(this);
        http_get = (Button) findViewById(R.id.http_get);
        http_get.setOnClickListener(this);
        http_post = (Button) findViewById(R.id.http_post);
        http_post.setOnClickListener(this);
        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(this);
        http_image = (Button) findViewById(R.id.http_image);
        http_image.setOnClickListener(this);
        iv_image = (ImageView) findViewById(R.id.iv_image);
        iv_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.volley_get:

                //实例化RequestQueue,参数Context,Stack根据系统版本会有不同的值
                //当API大于等于9的时候,使用httpurlconnection实现,叫做HurlStack
                //当API小于9的时候,使用HttpClient实现,叫做HttpClientStack
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                //实例化一个Request,StringRequest有四个参数,1请求方式,2请求的地址,3请求成功的回调函数,4请求错误的回调函数
                StringRequest stringRequest = new StringRequest(Request.Method.GET, path, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "result-----" + response);
                        Log.e(TAG, "thread-----" + Thread.currentThread().getName());
                        tv.setText(response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "error-----" + error.getMessage());
                        Log.e(TAG, "thread-----" + Thread.currentThread().getName());
                    }
                });
                //将请求添加到请求队列中
                requestQueue.add(stringRequest);
                break;
            case R.id.volley_post:
                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest request = new StringRequest(Request.Method.POST, path1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "result-----" + response);
                        Log.e(TAG, "thread-----" + Thread.currentThread().getName());
                        tv.setText(response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "error-----" + error.getMessage());
                        Log.e(TAG, "thread-----" + Thread.currentThread().getName());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("p", "1");

                        return params;
                    }
                };
                queue.add(request);
                break;
            case R.id.http_get:
                HttpUtil.getString(path, TAG,new HttpResponse() {
                    @Override
                    public void onSucceed(String result) {

                        Log.e(TAG, "result-------" + result);
                        Log.e(TAG, "thread-----" + Thread.currentThread().getName());

                        tv.setText(result);
                    }

                    @Override
                    public void onError(String message) {
                        Log.e(TAG, "error-------" + message);
                        Log.e(TAG, "thread-----" + Thread.currentThread().getName());


                    }
                });
                break;
            case R.id.http_post:
                Map<String, String> params = new HashMap<>();
                params.put("p", "1");
                HttpUtil.postString(path1, params, new HttpResponse() {
                    @Override
                    public void onSucceed(String result) {
                        VolleyLog.e(TAG, "result----" + result);
                        Log.e(TAG, "thread-----" + Thread.currentThread().getName());
                        tv.setText(result);
                    }

                    @Override
                    public void onError(String message) {
                        VolleyLog.e(TAG, "error----" + message);
                        Log.e(TAG, "thread-----" + Thread.currentThread().getName());

                    }
                });
                break;
            case R.id.http_image:
//                HttpUtil.display("http://himg.baidu.com/sys/portraitl/item/d7bbb43a?t=1405726358", iv_image);
                TeachImageLoader.getInstance(this).display("http://himg.baidu.com/sys/portraitl/item/d7bbb43a?t=1405726358",iv_image);
                break;

            case R.id.iv_image:
                RequestQueue jsonQueue = Volley.newRequestQueue(this);
                JsonRequest jsonRequest = new JsonRequest(Request.Method.GET, path, BigModel.class, new Response.Listener<BigModel>() {
                    @Override
                    public void onResponse(BigModel bigModel) {
                        switch (bigModel.getStatus()) {
                            case "0":
                                List<Model> data=bigModel.getData();
                                for (Model model:data){

                                    Log.e(TAG, "onResponse---" + model.getTitle());
                                }
                                break;
                            default:
                                Log.e(TAG, "onResponse:" + "未知原因失败");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                jsonQueue.add(jsonRequest);
                break;
        }
    }

    /**
     * 与生命周期进行联动,页面销毁时,根据TAG,取消网络请求
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpUtil.cancelRequest(TAG);
    }
}
