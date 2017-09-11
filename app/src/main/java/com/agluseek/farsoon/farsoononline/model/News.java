package com.agluseek.farsoon.farsoononline.model;

/**
 * Created by Farsoon on 2017/4/25.
 */

public class News {
    private String Summary;
    private String Title;
    private String ImagePath;
    private String strDate;
    private int nID;
    private String UrlPath;

    public int getnID() {
        return nID;
    }

    public void setnID(int nID) {
        this.nID = nID;
    }


    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getUrlPath() {
        return UrlPath;
    }

    public void setUrlPath(String urlPath) {
        UrlPath = urlPath;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        this.Summary = summary;
    }


    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }


}
