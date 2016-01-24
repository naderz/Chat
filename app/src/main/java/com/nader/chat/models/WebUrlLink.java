package com.nader.chat.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nader on 22/01/16.
 */

public class WebUrlLink {

    @SerializedName("url")
    private String mUrl;
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