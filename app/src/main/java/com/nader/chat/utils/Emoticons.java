package com.nader.chat.utils;

import com.nader.chat.R;

import java.util.HashMap;
import java.util.Map;

public class Emoticons {

    public static final Map<String, Emoticon> sEmoticonMap;

    static {
        sEmoticonMap = new HashMap<>();
        sEmoticonMap.put("elvis", new Emoticon(R.drawable.ic_elvis, "(elvis)"));
        sEmoticonMap.put("angry", new Emoticon(R.drawable.ic_angry, "(angry)"));
        sEmoticonMap.put("h5", new Emoticon(R.drawable.ic_h5, "(h5)"));
        sEmoticonMap.put("geek", new Emoticon(R.drawable.ic_geek, "(geek)"));
    }

    public static class Emoticon {
        public int resource;
        public String shortcut;

        public Emoticon(int resource, String shortcut) {
            this.resource = resource;
            this.shortcut = shortcut;
        }
    }
}
