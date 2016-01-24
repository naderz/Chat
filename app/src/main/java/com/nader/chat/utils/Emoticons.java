package com.nader.chat.utils;

import com.nader.chat.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nader on 24/01/16.
 */
public class Emoticons {

    public static final Map<String, Integer> sEmoticonMap;

    static {
        sEmoticonMap = new HashMap<>();
        sEmoticonMap.put("elvis", R.drawable.ic_elvis);
        sEmoticonMap.put("angry", R.drawable.ic_angry);
        sEmoticonMap.put("h5", R.drawable.ic_h5);
        sEmoticonMap.put("geek", R.drawable.ic_geek);
    }
}
