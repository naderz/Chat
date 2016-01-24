package com.nader.chat.utils.finders;

import java.util.List;

/**
 * Created by nader on 22/01/16.
 */
public class EmoticonsFinder {

    private static final String REGEX_EMOTICONS = "(\\()([a-zA-Z\\d]{1,15})(\\))";

    public static List<Finder.Match> fetch(String messageString) {
        return Finder.fetch(messageString, REGEX_EMOTICONS, 2);
    }
}
