package com.nader.chat.utils.finders;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nader on 24/01/16.
 */
public abstract class Finder {

    protected static List<String> fetch(String messageString, String regex, int group) {
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(messageString);

        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group(group));
        }
        return matches;
    }

}
