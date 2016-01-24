package com.nader.chat.utils;

import com.nader.chat.models.Link;
import com.nader.chat.models.MessageMetaData;
import com.nader.chat.utils.finders.EmoticonsFinder;
import com.nader.chat.utils.finders.MentionFinder;
import com.nader.chat.utils.finders.UrlFinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nader on 24/01/16.
 */
public class MessageParser {

    public static MessageMetaData getMessageMetaData(String message) {
        MessageMetaData messageMetaData = new MessageMetaData();

        messageMetaData.setEmoticons(EmoticonsFinder.fetch(message));
        messageMetaData.setMentions(MentionFinder.fetch(message));
        messageMetaData.setLinks(fetchLinks(message));

        return messageMetaData;
    }


    private static List<Link> fetchLinks(String message) {
        List<Link> links = new ArrayList<>();
        List<String> urls = UrlFinder.fetch(message);

        for (String url : urls) {
            try {
                Link link = new Link(UrlTitleExtractor.getPageTitle(url), url);
                links.add(link);
            } catch (IOException e) {
                //Handle this error with a design or just ignore
                // depends on how important is it to get the meta data
            }

        }
        return links;
    }


}
