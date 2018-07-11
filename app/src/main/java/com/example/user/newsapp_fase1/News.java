package com.example.user.newsapp_fase1;

public class News {
    private String mTitle;
    private String mLink;
    private String mDate;
    private String mSection;
    private String mAuthor;

    public News(String title, String link, String date, String section, String author){
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
    public String getAuthor() {
        return mAuthor;
    }
}
