package com.nader.chat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WebUrlLink {

    @Expose
    @SerializedName("url")
    private String mUrl;
    @Expose
    @SerializedName("title")
    private String mTitle;

    public WebUrlLink() {
    }

    public WebUrlLink(String title, String url) {
        mTitle = title;
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}