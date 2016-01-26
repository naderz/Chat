package com.nader.chat.utils.finders;

public class MentionFinder extends Finder {

    private static final String REGEX_MENTIONS = "(@)([a-zA-Z\\d]+)";

    @Override
    public String getRegex() {
        return REGEX_MENTIONS;
    }

    @Override
    public int getGroup() {
        return 2;
    }
}
