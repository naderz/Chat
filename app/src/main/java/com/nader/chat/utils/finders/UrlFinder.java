package com.nader.chat.utils.finders;

import android.util.Patterns;

import java.util.List;

/**
 * Created by nader on 22/01/16.
 */
public class UrlFinder extends Finder {

    public static final String REGEX_WEB_URL = Patterns.WEB_URL.pattern();

    public static List<String> fetch(String messageString) {
        return fetch(messageString, REGEX_WEB_URL, 0);
    }

}
