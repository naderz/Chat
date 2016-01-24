package com.nader.chat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nader.chat.utils.MessageParser;

/**
 * Created by nader on 24/01/16.
 */
public class ChatMessage {

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
    private long mMessageDateTime;

    private MessageParser.Matches mMessageMatches;

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

    public long getMessageDateTime() {
        return mMessageDateTime;
    }

    public void setMessageDateTime(long messageDateTime) {
        mMessageDateTime = messageDateTime;
    }


    public MessageParser.Matches getMessageMatches() {
        return mMessageMatches;
    }

    public void setMessageMatches(MessageParser.Matches mMessageMatches) {
        this.mMessageMatches = mMessageMatches;
    }


}
