package com.example.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MyLikeActivity extends AppCompatActivity {
    private RecyclerView rec_like;
    private List<Data> dataList = new ArrayList<>();
    private NewsListAdapter adapter;
    private LikeReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_like);
        setTitle("我的收藏");
        rec_like = findViewById(R.id.rev_like);
        rec_like.setLayoutManager(new LinearLayoutManager(MyLikeActivity.this));
        adapter = new NewsListAdapter(dataList);
        rec_like.setAdapter(adapter);
        
        initReceiver();
        getLikeNewsData();
    }

    private void initReceiver() {
        receiver = new LikeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("LIKE_CHANGED");
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    class LikeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            getLikeNewsData();
        }
    }

    // 获取收藏的新闻列表
    private void getLikeNewsData() {
        Log.e("LikeActivity", "getLikeNewsData ");
        String userid = BmobUser.getCurrentUser(BmobUser.class).getObjectId();

        BmobQuery<Like> likeBmobQuery = new BmobQuery<>();
        likeBmobQuery.addWhereEqualTo("userid", userid);
        likeBmobQuery.addWhereEqualTo("isLike", true);
        likeBmobQuery.findObjects(new FindListener<Like>() {
            @Override
            public void done(List<Like> list, BmobException e) {
                if(e==null){
                    dataList.clear();
                    if(list != null && list.size() > 0){
                        Log.e("LikeActivity", "like size = " + list.size());

                        for(Like like :list){
                            String newsjson = like.getNewsjson();
                            Data data = new Gson().fromJson(newsjson, Data.class);
                            dataList.add(data);
                        }
                        adapter.changeData(dataList);
                    }
                }
            }
        });
    }
}
