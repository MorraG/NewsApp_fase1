package com.example.user.newsapp_fase1;

import java.util.List;

public class News {
    private String mTitle;
    private String mLink;
    private String mDate;
    private String mSection;
    private List<String> mAuthor;

    public News(String title, String link, String date, String section, List<String> author){
        mTitle = title;
        mLink = link;
        mDate = date;
        mSection = section;
        mAuthor = author;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmLink() {
        return mLink;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmSection() {
        return mSection;
    }
    public List<String> getAuthor() {
        return mAuthor;
    }
}
