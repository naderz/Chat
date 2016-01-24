package com.nader.chat.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nader on 22/01/16.
 */
public class MessageMetaData {

    @SerializedName("mentions")
    private List<String> mMentions = new ArrayList<>();
    @SerializedName("emoticons")
    private List<String> mEmoticons = new ArrayList<>();
    @SerializedName("links")
    private List<Link> mLinks = new ArrayList<>();


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


    public List<Link> getLinks() {
        return mLinks;
    }

    public void setLinks(List<Link> links) {

        if (links != null && links.size() == 0) {
            links = null;
        }
        this.mLinks = links;
    }

}