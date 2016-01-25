package com.nader.chat;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nader.chat.models.ChatMessage;
import com.nader.chat.models.ChatMessageMetaData;
import com.nader.chat.models.WebUrlLink;
import com.nader.chat.utils.UrlTitleExtractor;
import com.nader.chat.utils.finders.EmoticonsFinder;
import com.nader.chat.utils.finders.Finder;
import com.nader.chat.utils.finders.MentionFinder;
import com.nader.chat.utils.finders.UrlFinder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by nader on 24/01/16.
 */
public class ChatController {

    public ChatMessage createChatMessageItem(String message) {

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(UUID.randomUUID().toString());
        chatMessage.setSender("You"); //TODO get Logged in user?
        chatMessage.setLastEdited(new Date().getTime());
        chatMessage.setPending(true);
        chatMessage.setMessageContent(message);

        Matches matches = fetchMatches(message);
        chatMessage.setMessageMatches(matches);

        return chatMessage;
    }

    private Matches fetchMatches(String message) {
        Matches matches = new Matches();
        matches.originalString = message;
        matches.links = new UrlFinder().fetchUsingRegex(message);
        matches.emoticons = new EmoticonsFinder().fetchUsingRegex(message);
        matches.mentions = new MentionFinder().fetchUsingRegex(message);

        return matches;
    }


    public ChatMessage postMessage(ChatMessage chatMessage) {
        chatMessage.setMessageMetaData(generateChatMessageMetaDataModel(chatMessage.getMessageMatches()));

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        //Generates a JSON to be sent with chat message object + chat message meta data
        Log.i("Chat", gson.toJson(chatMessage));

        chatMessage.setPending(false);
        return chatMessage;
    }

    private ChatMessageMetaData generateChatMessageMetaDataModel(Matches matches) {
        ChatMessageMetaData chatMessageMetaData = new ChatMessageMetaData();

        chatMessageMetaData.setMentions(getMentionedStringMatches(matches));

        chatMessageMetaData.setEmoticons(getEmoticonStringMatches(matches));

        chatMessageMetaData.setLinks(getWebLinksAndTitleMatches(matches));
        return chatMessageMetaData;
    }

    private List<WebUrlLink> getWebLinksAndTitleMatches(Matches matches) {
        List<WebUrlLink> links = new ArrayList<>();
        UrlTitleExtractor titleExtractor = new UrlTitleExtractor();
        links.addAll(titleExtractor.generateWebUrlLinks(matches.links));
        return links;
    }

    private List<String> getEmoticonStringMatches(Matches matches) {
        List<String> emoticons = new ArrayList<>();
        for (Finder.Match match : matches.emoticons) {
            emoticons.add(match.string);
        }
        return emoticons;
    }

    private List<String> getMentionedStringMatches(Matches matches) {
        List<String> mentions = new ArrayList<>();
        for (Finder.Match match : matches.mentions) {
            mentions.add(match.string);
        }
        return mentions;
    }

    public class Matches {
        public String originalString;
        public List<Finder.Match> emoticons;
        public List<Finder.Match> links;
        public List<Finder.Match> mentions;
    }
}
