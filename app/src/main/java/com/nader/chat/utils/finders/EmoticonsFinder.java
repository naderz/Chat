package com.nader.chat.utils.finders;

/**
 * Created by nader on 22/01/16.
 */
public class EmoticonsFinder extends Finder {

    private static final String REGEX_EMOTICONS = "(\\()([a-zA-Z\\d]{1,15})(\\))";

    @Override
    public String getRegex() {
        return REGEX_EMOTICONS;
    }

    @Override
    public int getGroup() {
        return 2;
    }
}
