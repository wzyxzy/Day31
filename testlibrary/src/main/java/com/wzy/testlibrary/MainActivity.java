package com.wzy.testlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.wzy.teachlibrary.http.HttpUtil;
import com.wzy.teachlibrary.httpgson.GsonRequest;
import com.wzy.testlibrary.adapters.BBCAdapter;
import com.wzy.testlibrary.model.BBCNews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String s_get;
    private String s_post;
    private Button testGet;
    private TextView tv_get;
    private Button testPost;
    private TextView tv_post;
    private Button testGson;
    private ListView lv_bbc;
    private RequestQueue requestQueue;
    private static final String APIURL="http://walter-producer-cdn.api.bbci.co.uk/content/cps/news/front_page";
    private BBCAdapter adapter;
    private List<BBCNews.RelationsEntity> relationsEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpUtil.init(this);

        initView();

    }


    private void initView() {
        testGet = (Button) findViewById(R.id.testGet);
        tv_get = (TextView) findViewById(R.id.tv_get);
        testPost = (Button) findViewById(R.id.testPost);
        tv_post = (TextView) findViewById(R.id.tv_post);

        testGet.setOnClickListener(this);
        testPost.setOnClickListener(this);
        testGson = (Button) findViewById(R.id.testGson);
        testGson.setOnClickListener(this);
        lv_bbc = (ListView) findViewById(R.id.lv_bbc);
        relationsEntities=new ArrayList<>();
        adapter=new BBCAdapter(null,this,R.layout.item);
        lv_bbc.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.testGet:
                s_get = HttpUtil.get("https://www.google.com.hk");
                tv_get.setText(s_get);
                break;
            case R.id.testPost:
                Map<String, String> map = new HashMap<>();
                map.put("p", "1");
                s_post = HttpUtil.post("http://apiv2.vmovier.com/api/post/getPostInCate?cateid=0", map);
                tv_post.setText(s_post);
                break;
            case R.id.testGson:
                requestQueue= Volley.newRequestQueue(this);
                GsonRequest<BBCNews> gsonRequest=new GsonRequest<BBCNews>(Request.Method.GET, APIURL, BBCNews.class, null, new Response.Listener<BBCNews>() {
                    @Override
                    public void onResponse(BBCNews response) {
                        relationsEntities=response.getRelations();
                        adapter.updateRes(relationsEntities);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(gsonRequest);
                break;
        }
    }
}
