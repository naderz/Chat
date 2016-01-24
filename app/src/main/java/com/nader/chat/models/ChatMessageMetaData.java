package com.nader.chat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nader.chat.utils.MessageParser;
import com.nader.chat.utils.UrlTitleExtractor;
import com.nader.chat.utils.finders.Finder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nader on 22/01/16.
 */
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

    public ChatMessageMetaData applyTo(MessageParser.Matches matches) {
        mMentions = new ArrayList<>();
        for (Finder.Match match : matches.mentions) {
            mMentions.add(match.string);
        }

        mEmoticons = new ArrayList<>();
        for (Finder.Match match : matches.emoticons) {
            mEmoticons.add(match.string);
        }

        mLinks = new ArrayList<>();
        UrlTitleExtractor titleExtractor = new UrlTitleExtractor();
        for (Finder.Match match : matches.links) {
            mLinks.addAll(titleExtractor.generateWebUrlLinks(match.string));
        }
        return this;
    }

}