package com.nader.chat;

import android.util.Log;

import com.nader.chat.models.ChatMessage;
import com.nader.chat.utils.MessageParser;

import java.util.Date;
import java.util.UUID;

public class ChatController {

    public ChatMessage createChatMessageItem(String message) {

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(UUID.randomUUID().toString());
        chatMessage.setSender("You"); //TODO get Logged in user?
        chatMessage.setLastEdited(new Date().getTime());
        chatMessage.setPending(true);
        chatMessage.setMessageContent(message);

        return chatMessage;
    }

    public ChatMessage postMessage(ChatMessage chatMessage) {
        String metaData = new MessageParser().retrieveMessageMetadataAsJson(chatMessage.getMessageContent());

        Log.i("MetaData", metaData);
        //Message MetaData Do Something with it most probably we will not need the string
        //but rather a model that we can send using RetroFit or something similar
        chatMessage.setPending(false);
        return chatMessage;
    }
}