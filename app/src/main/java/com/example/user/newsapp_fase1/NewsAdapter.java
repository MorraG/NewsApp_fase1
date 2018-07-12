package com.example.user.newsapp_fase1;


import android.content.Context;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class NewsAdapter extends ArrayAdapter<News>{
    public NewsAdapter(@NonNull Context context,@NonNull List<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        News currentNews = getItem(position);

        TextView titleView = (TextView) listView.findViewById(R.id.title);
        TextView linkView = (TextView) listView.findViewById(R.id.link);
        TextView dateView = (TextView) listView.findViewById(R.id.date);
        TextView sectionView = (TextView) listView.findViewById(R.id.section);
        TextView authorView = (TextView) listView.findViewById(R.id.author);

        titleView.setText(currentNews.getmTitle());
        linkView.setText(currentNews.getmLink());
        dateView.setText(formatDate(currentNews.getmDate()));
        sectionView.setText(currentNews.getmSection());

        if (currentNews.getAuthor() != null) {
            authorView.setText((CharSequence) currentNews.getAuthor());
        }

        return listView;
    }

    public String formatDate(String date) {
        String formattedDate = date.substring(0, 10);
        return formattedDate;
    }


}
