package com.agluseek.farsoon.farsoononline.model;

/**
 * Created by Farsoon on 2017/4/24.
 */

public class News_item {
    private int news_img;
    private String news_str;

    public News_item(int news_img, String news_str) {
        this.news_img = news_img;
        this.news_str = news_str;
    }

    public int getNews_img() {
        return news_img;
    }

    public void setNews_img(int news_img) {
        this.news_img = news_img;
    }

    public String getNews_str() {
        return news_str;
    }

    public void setNews_str(String news_str) {
        this.news_str = news_str;
    }
}
