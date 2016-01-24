package com.nader.chat.utils.finders;

import android.util.Patterns;

import java.util.List;

/**
 * Created by nader on 22/01/16.
 */
public class UrlFinder {

    public static final String REGEX_WEB_URL = Patterns.WEB_URL.pattern();

    public static List<Finder.Match> fetch(String messageString) {
        return Finder.fetch(messageString, REGEX_WEB_URL, 0);
    }

}
