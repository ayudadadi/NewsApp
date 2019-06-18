package com.example.demo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private NewsFragmentAdapter adapter;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tvLike;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragmentList();
        initViews();
    }

    private void initFragmentList() {
        NewsFragment fragment1 = new NewsFragment();
        fragment1.setType("top");
        NewsFragment fragment2 = new NewsFragment();
        fragment2.setType("shehui");
        NewsFragment fragment3 = new NewsFragment();
        fragment3.setType("keji");
        NewsFragment fragment4 = new NewsFragment();
        fragment4.setType("yule");
        NewsFragment fragment5 = new NewsFragment();
        fragment5.setType("tiyu");
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment5);
    }

    @SuppressLint("ResourceAsColor")
    private void initViews() {

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv1.setBackgroundColor(R.color.lightgrey);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3);
            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(4);
            }
        });
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(6);
        adapter = new NewsFragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @SuppressLint("ResourceAsColor")
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        tv1.setBackgroundColor(R.color.lightgrey);
                        tv2.setBackgroundColor(Color.TRANSPARENT);
                        tv3.setBackgroundColor(Color.TRANSPARENT);
                        tv4.setBackgroundColor(Color.TRANSPARENT);
                        tv5.setBackgroundColor(Color.TRANSPARENT);
                        break;
                    case 1:
                        tv2.setBackgroundColor(R.color.lightgrey);
                        tv1.setBackgroundColor(Color.TRANSPARENT);
                        tv3.setBackgroundColor(Color.TRANSPARENT);
                        tv4.setBackgroundColor(Color.TRANSPARENT);
                        tv5.setBackgroundColor(Color.TRANSPARENT);
                        break;
                    case 2:
                        tv3.setBackgroundColor(R.color.lightgrey);
                        tv1.setBackgroundColor(Color.TRANSPARENT);
                        tv2.setBackgroundColor(Color.TRANSPARENT);
                        tv4.setBackgroundColor(Color.TRANSPARENT);
                        tv5.setBackgroundColor(Color.TRANSPARENT);
                        break;
                    case 3:
                        tv4.setBackgroundColor(R.color.lightgrey);
                        tv1.setBackgroundColor(Color.TRANSPARENT);
                        tv2.setBackgroundColor(Color.TRANSPARENT);
                        tv3.setBackgroundColor(Color.TRANSPARENT);
                        tv5.setBackgroundColor(Color.TRANSPARENT);
                        break;
                    case 4:
                        tv5.setBackgroundColor(R.color.lightgrey);
                        tv2.setBackgroundColor(Color.TRANSPARENT);
                        tv3.setBackgroundColor(Color.TRANSPARENT);
                        tv4.setBackgroundColor(Color.TRANSPARENT);
                        tv1.setBackgroundColor(Color.TRANSPARENT);
                        break;
                }
            }
        });
        tvLike = findViewById(R.id.tv_like);
        tvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BmobUser.getCurrentUser(BmobUser.class) == null){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(MainActivity.this,MyLikeActivity.class);
                startActivity(intent);
            }
        });
    }

}

