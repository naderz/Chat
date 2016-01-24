package com.nader.chat.utils;

import com.nader.chat.utils.finders.EmoticonsFinder;
import com.nader.chat.utils.finders.Finder;
import com.nader.chat.utils.finders.MentionFinder;
import com.nader.chat.utils.finders.UrlFinder;

import java.util.List;

/**
 * Created by nader on 24/01/16.
 */
public class MessageParser {

    public static Matches parse(String message) {
        Matches matches = new Matches();
        matches.originalString = message;
        matches.links = UrlFinder.fetch(message);
        matches.emoticons = EmoticonsFinder.fetch(message);
        matches.mentions = MentionFinder.fetch(message);

        return matches;
    }

    public static class Matches {
        public String originalString;
        public List<Finder.Match> emoticons;
        public List<Finder.Match> links;
        public List<Finder.Match> mentions;
    }
}
