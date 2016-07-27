package com.wzy.bbcnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wzy.bbcnews.adapters.BBCAdapter;
import com.wzy.bbcnews.model.BBCNews;
import com.wzy.teachlibrary.http.HttpUtil;
import com.wzy.teachlibrary.httpgson.GsonRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView在快读滑动的时候,暂停图片加载,当滑动状态降下来,变成一般滑动或者静止
 */

public class MainActivity extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener2 {


    private ListView listView;
    private RequestQueue requestQueue;
    private static final String APIURL = "http://walter-producer-cdn.api.bbci.co.uk/content/cps/news/front_page";
    private BBCAdapter adapter;
    private List<BBCNews.RelationsEntity> relationsEntities;
    private PullToRefreshListView lv_bbc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpUtil.init(this);

        initView();
        initData();

    }

    private void initData() {
        requestQueue = Volley.newRequestQueue(this);
        GsonRequest<BBCNews> gsonRequest = new GsonRequest<BBCNews>(Request.Method.GET, APIURL, BBCNews.class, null, new Response.Listener<BBCNews>() {
            @Override
            public void onResponse(BBCNews response) {
                relationsEntities = response.getRelations();
                adapter.updateRes(relationsEntities);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(gsonRequest);
    }


    private void initView() {
        lv_bbc = (PullToRefreshListView) findViewById(R.id.lv_bbc);
        lv_bbc.setMode(PullToRefreshBase.Mode.BOTH);
        lv_bbc.setOnRefreshListener(this);
        listView = lv_bbc.getRefreshableView();

        relationsEntities = new ArrayList<>();
        adapter = new BBCAdapter(null, this, R.layout.item);
        listView.setAdapter(adapter);
        //滑动监听,为我们的listView设置滑动监听,使用universal-image-loader中提供的默认PauseOnScrollListener
        //实例化的时候需要三个参数,1 ImageLoader实例,2当滑动的时候是否暂停,3.当快速滑动的时候是都暂停
//        lv_bbc.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(),false,true));
        lv_bbc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = relationsEntities.get(position - 1).getContent().getShareUrl();
                if (url != null) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, newsInfo.class);
                    intent.putExtra("infoUrl", url);
                    startActivity(intent);
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                relationsEntities.remove(position);
                initData();
                return false;
            }
        });
    }


    @Override
    public void onPullDownToRefresh(final PullToRefreshBase refreshView) {
        refreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                initData();
                adapter.updateRes(relationsEntities);
                refreshView.onRefreshComplete();

            }
        }, 100);
    }

    @Override
    public void onPullUpToRefresh(final PullToRefreshBase refreshView) {
        refreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                initData();
                adapter.addRes(relationsEntities);
                refreshView.onRefreshComplete();

            }
        }, 100);
    }
}
