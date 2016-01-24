package com.nader.chat.utils.finders;

import java.util.List;

/**
 * Created by nader on 22/01/16.
 */
public class MentionFinder extends Finder {

    private static final String REGEX_MENTIONS = "(@)([a-zA-Z\\d]+)([^a-zA-Z\\d])";

    public static List<String> fetch(String messageString) {
        return fetch(messageString, REGEX_MENTIONS, 2);
    }

}
