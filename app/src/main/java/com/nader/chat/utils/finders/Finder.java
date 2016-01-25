package com.nader.chat.utils.finders;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nader on 24/01/16.
 */
public abstract class Finder {

    abstract String getRegex();

    abstract int getGroup();

    public List<Match> fetchUsingRegex(String messageString) {
        Pattern pattern = Pattern.compile(getRegex());

        Matcher matcher = pattern.matcher(messageString);
        List<Match> matches = new ArrayList<>();
        while (matcher.find()) {
            Match match = new Match();
            match.string = matcher.group(getGroup());
            match.startIndex = matcher.start();
            match.endIndex = matcher.end();
            matches.add(match);
        }
        return matches;
    }

    public class Match {
        public String string;
        public int startIndex;
        public int endIndex;
    }

}