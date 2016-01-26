package com.nader.chat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
