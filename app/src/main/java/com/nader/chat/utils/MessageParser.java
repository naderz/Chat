package com.nader.chat.utils;

import com.nader.chat.models.ChatMessage;
import com.nader.chat.models.ChatMessageMetaData;
import com.nader.chat.models.WebUrlLink;
import com.nader.chat.utils.finders.EmoticonsFinder;
import com.nader.chat.utils.finders.Finder;
import com.nader.chat.utils.finders.MentionFinder;
import com.nader.chat.utils.finders.UrlFinder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nader on 24/01/16.
 */
public class MessageParser {

    public ChatMessage generateChatMessage(String message) {
        MessageParser.Matches matches = fetchMatches(message);
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageMetaData(generateChatMessageData(matches));
        chatMessage.setMessageContent(matches.originalString);
        chatMessage.setMessageDateTime(new Date().getTime());
        chatMessage.setMessageMatches(matches);

        chatMessage.setSender("You"); //TODO get Looged in user?
        return chatMessage;
    }

    private Matches fetchMatches(String message) {
        Matches matches = new Matches();
        matches.originalString = message;
        matches.links = UrlFinder.fetch(message);
        matches.emoticons = EmoticonsFinder.fetch(message);
        matches.mentions = MentionFinder.fetch(message);

        return matches;
    }

    private ChatMessageMetaData generateChatMessageData(Matches matches) {
        ChatMessageMetaData chatMessageMetaData = new ChatMessageMetaData();

        List<String> mentions = new ArrayList<>();
        for (Finder.Match match : matches.mentions) {
            mentions.add(match.string);
        }
        chatMessageMetaData.setMentions(mentions);

        List<String> emoticons = new ArrayList<>();
        for (Finder.Match match : matches.emoticons) {
            emoticons.add(match.string);
        }
        chatMessageMetaData.setEmoticons(emoticons);

        List<WebUrlLink> links = new ArrayList<>();
        UrlTitleExtractor titleExtractor = new UrlTitleExtractor();
        for (Finder.Match match : matches.links) {
            links.addAll(titleExtractor.generateWebUrlLinks(match.string));
        }
        chatMessageMetaData.setLinks(links);
        return chatMessageMetaData;
    }

    public class Matches {
        public String originalString;
        public List<Finder.Match> emoticons;
        public List<Finder.Match> links;
        public List<Finder.Match> mentions;
    }
}
