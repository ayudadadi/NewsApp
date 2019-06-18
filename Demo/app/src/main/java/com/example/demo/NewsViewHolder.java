package com.example.demo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class NewsViewHolder extends RecyclerView.ViewHolder {
    ImageView iv_news;
    TextView tv_title;
    TextView tv_author;
    TextView tv_date;
    View itemView;

    public NewsViewHolder(View itemView) {
        super(itemView);
        this.itemView =itemView;
        iv_news = itemView.findViewById(R.id.iv_news);
        tv_title = itemView.findViewById(R.id.tv_title);
        tv_author = itemView.findViewById(R.id.tv_author);
        tv_date = itemView.findViewById(R.id.tv_date);
    }
}
