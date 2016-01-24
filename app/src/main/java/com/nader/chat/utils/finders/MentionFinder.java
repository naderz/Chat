package com.nader.chat.utils.finders;

import java.util.List;

/**
 * Created by nader on 22/01/16.
 */
public class MentionFinder {

    private static final String REGEX_MENTIONS = "(@)([a-zA-Z\\d]+)";

    public static List<Finder.Match> fetch(String messageString) {
        return Finder.fetch(messageString, REGEX_MENTIONS, 2);
    }
}
