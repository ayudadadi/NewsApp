package com.example.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsFragment extends Fragment {
    private static final String TAG = "MainActivity";
    private static final int MSG_GET_NEWS = 1001;
    private String baseUrl = "http://v.juhe.cn/toutiao/index";
    private final String key = "78971bf055a3d6770a3a5e8fe3acf9ab";
    private RecyclerView recyclerView;
    private List<Data> dataList = new ArrayList<>();
    private NewsListAdapter newsListAdapter;
    private Handler handler;
    private SwipeRefreshLayout swipeRefreshLayout;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = view.findViewById(R.id.news_recyclerView);
        newsListAdapter = new NewsListAdapter(dataList);
        recyclerView.setAdapter(newsListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeRefreshLayout = view.findViewById(R.id.SwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNewsData();
            }
        });

        initHandler();
        getNewsData();
        return  view;
    }

    private void initHandler() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what ==  MSG_GET_NEWS){
                    newsListAdapter.changeData(dataList);
                    swipeRefreshLayout.setRefreshing(false);
                    return true;
                }
                return false;
            }
        });
    }

    private void getNewsData() {
        String url = baseUrl.concat("?type=").concat(type).concat("&key=").concat(key);
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).get().build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonStr = response.body().string();
                Log.e(TAG, jsonStr);

                Gson gson = new Gson();
                NewsRootBean newsRootBean = gson.fromJson(jsonStr, NewsRootBean.class);
                dataList = newsRootBean.getResult().getData();

                handler.sendEmptyMessage(MSG_GET_NEWS);


                /*Data data = newsRootBean.getResult().getData().get(0);
                Log.e(TAG, "title = " + data.getTitle());
                Log.e(TAG, "author = " + data.getAuthor_name());
                Log.e(TAG, "date = " + data.getDate());*/
            }
        });

    }
}
