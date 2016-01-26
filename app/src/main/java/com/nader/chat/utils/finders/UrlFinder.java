package com.nader.chat.utils.finders;

import android.util.Patterns;

public class UrlFinder extends Finder {

    public static final String REGEX_WEB_URL = Patterns.WEB_URL.pattern();

    @Override
    public String getRegex() {
        return REGEX_WEB_URL;
    }

    @Override
    public int getGroup() {
        return 0;
    }
}
