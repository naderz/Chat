package com.nader.chat.utils.finders;

import java.util.List;

/**
 * Created by nader on 22/01/16.
 */
public class EmoticonsFinder extends Finder {

    private static final String REGEX_EMOTICONS = "(\\()([a-zA-Z\\d]+)(\\))";

    public static List<String> fetch(String messageString) {
        return fetch(messageString, REGEX_EMOTICONS, 2);
    }
}
