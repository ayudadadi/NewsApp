package com.example.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

class NewsListAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    private List<Data> dataList;

    public NewsListAdapter(List<Data> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,
                parent, false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(view);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        final Data data = dataList.get(position);
        holder.tv_author.setText(data.getAuthor_name());
        holder.tv_title.setText(data.getTitle());
        holder.tv_date.setText(data.getDate());
        Glide.with(holder.iv_news.getContext()).load(data.getThumbnail_pic_s()).into(holder.iv_news);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),NewsDetailActivity.class);
/*                intent.putExtra("TITLE",data.getTitle());*/
                intent.putExtra("URL",data.getUrl());
                intent.putExtra("NEWSID",data.getUniquekey());
                intent.putExtra("NEWSJSON",new Gson().toJson(data));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void changeData(List<Data> dataList) {
        this.dataList =  dataList;
        notifyDataSetChanged();
    }
}
