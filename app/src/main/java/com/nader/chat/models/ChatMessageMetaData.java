package com.nader.chat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageMetaData {

    @Expose
    @SerializedName("mentions")
    private List<String> mMentions = new ArrayList<>();

    @Expose
    @SerializedName("emoticons")
    private List<String> mEmoticons = new ArrayList<>();

    @Expose
    @SerializedName("links")
    private List<WebUrlLink> mLinks = new ArrayList<>();

    public List<String> getMentions() {
        return mMentions;
    }

    public void setMentions(List<String> mentions) {
        if (mentions != null && mentions.size() == 0) {
            mentions = null;
        }
        this.mMentions = mentions;
    }

    public List<String> getEmoticons() {
        return mEmoticons;
    }

    public void setEmoticons(List<String> emoticons) {
        if (emoticons != null && emoticons.size() == 0) {
            emoticons = null;
        }
        this.mEmoticons = emoticons;
    }

    public List<WebUrlLink> getLinks() {
        return mLinks;
    }

    public void setLinks(List<WebUrlLink> links) {

        if (links != null && links.size() == 0) {
            links = null;
        }
        this.mLinks = links;
    }

}