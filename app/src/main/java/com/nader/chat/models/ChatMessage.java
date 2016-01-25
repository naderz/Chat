package com.nader.chat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nader.chat.ChatController;

/**
 * Created by nader on 24/01/16.
 */
public class ChatMessage {

    @Expose
    @SerializedName("id")
    private String mId;

    @Expose
    @SerializedName("pending")
    private boolean mPending;

    @Expose
    @SerializedName("sender")
    private String mSender;

    @Expose
    @SerializedName("messageContent")
    private String mMessageContent;

    @Expose
    @SerializedName("messageMetaData")
    private ChatMessageMetaData mMessageMetaData;

    @Expose
    @SerializedName("messageDateTime")
    private long mLastEdited;

    private ChatController.Matches mMessageMatches;

    public String getSender() {
        return mSender;
    }

    public void setSender(String sender) {
        mSender = sender;
    }

    public String getMessageContent() {
        return mMessageContent;
    }

    public void setMessageContent(String messageContent) {
        mMessageContent = messageContent;
    }

    public ChatMessageMetaData getMessageMetaData() {
        return mMessageMetaData;
    }

    public void setMessageMetaData(ChatMessageMetaData messageMetaData) {
        mMessageMetaData = messageMetaData;
    }

    public long getLastEdited() {
        return mLastEdited;
    }

    public void setLastEdited(long lastEdited) {
        mLastEdited = lastEdited;
    }

    public ChatController.Matches getMessageMatches() {
        return mMessageMatches;
    }

    public void setMessageMatches(ChatController.Matches mMessageMatches) {
        this.mMessageMatches = mMessageMatches;
    }

    public boolean isPending() {
        return mPending;
    }

    public void setPending(boolean mPending) {
        this.mPending = mPending;
    }


    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }
}
