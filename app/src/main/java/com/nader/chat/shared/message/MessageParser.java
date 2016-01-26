package com.nader.chat.shared.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nader.chat.models.ChatMessageMetaData;
import com.nader.chat.models.WebUrlLink;
import com.nader.chat.shared.message.finders.EmoticonsFinder;
import com.nader.chat.shared.message.finders.Finder;
import com.nader.chat.shared.message.finders.MentionFinder;
import com.nader.chat.shared.message.finders.UrlFinder;

import java.util.ArrayList;
import java.util.List;


public class MessageParser {


    /**
     * retrieves metadata from a message string
     *
     * @param message message to retrieve meta data from
     * @return JSON as a string
     */
    public String retrieveMessageMetadataAsJson(String message) {
        ChatMessageMetaData metaData = generateMessageMetaData(findMatches(message));
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(metaData);
    }

    /**
     * generates message metadata from Matches
     *
     * @param matches object generate through {@method findMatches(String message)}
     * @return ChatMessageMetaData model
     */
    public ChatMessageMetaData generateMessageMetaData(Matches matches) {
        ChatMessageMetaData chatMessageMetaData = new ChatMessageMetaData();

        chatMessageMetaData.setMentions(getMentionedStringMatches(matches));

        chatMessageMetaData.setEmoticons(getEmoticonStringMatches(matches));

        chatMessageMetaData.setLinks(getWebLinksAndTitleMatches(matches));

        return chatMessageMetaData;
    }

    /**
     * Finds matches of Urls Emoticons and Mentions in a string
     *
     * @param message message to be parsed
     * @return Matches an object that contains a list of matches found in every category + original string
     */
    public Matches findMatches(String message) {
        Matches matches = new Matches();
        matches.originalString = message;
        matches.links = new UrlFinder().fetchUsingRegex(message);
        matches.emoticons = new EmoticonsFinder().fetchUsingRegex(message);
        matches.mentions = new MentionFinder().fetchUsingRegex(message);

        return matches;
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
