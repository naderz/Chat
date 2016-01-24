package com.nader.chat.utils.finders;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nader on 24/01/16.
 */
public abstract class Finder {

    protected static List<Match> fetch(String messageString, String regex, int group) {
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(messageString);
        List<Match> matches = new ArrayList<>();
        while (matcher.find()) {
            Match match = new Match();
            match.string = matcher.group(group);
            match.startIndex = matcher.start();
            match.endIndex = matcher.end();
            matches.add(match);
        }
        return matches;
    }

    public static class Match {
        public String string;
        public int startIndex;
        public int endIndex;

    }

}
