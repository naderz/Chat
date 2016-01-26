package com.nader.chat.utils.message.finders;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class EmoticonFinderTest {

    private Finder mEmoticonFinder;

    @Before
    public void init() {
        mEmoticonFinder = new EmoticonsFinder();
    }

    @Test
    public void findEmoticon_one_result_letters_only() throws Exception {
        List<Finder.Match> emoticons = mEmoticonFinder.fetchUsingRegex("hey (wink)");
        assertEquals(emoticons.get(0).string, "wink");
    }

    @Test
    public void findEmoticon_one_result_numbers_only() throws Exception {
        List<Finder.Match> emoticons = mEmoticonFinder.fetchUsingRegex("hellow (272)");
        assertEquals(emoticons.get(0).string, "272");
    }

    @Test
    public void findEmoticon_one_result_alphanumeric() throws Exception {
        List<Finder.Match> emoticons = mEmoticonFinder.fetchUsingRegex("(f2f)");
        assertEquals(emoticons.get(0).string, "f2f");
    }

    @Test
    public void findEmoticon_no_result_more_than_15() throws Exception {
        List<Finder.Match> emoticons = mEmoticonFinder.fetchUsingRegex("(thisisaverylongstringthatwillnotbematched)");
        assertEquals(emoticons.size(), 0);
    }

    @Test
    public void findEmoticon_returns_no_results() throws Exception {
        List<Finder.Match> emoticons = mEmoticonFinder.fetchUsingRegex("Nothing to see :( here :) or even here :(  ): ");
        assertEquals(emoticons.size(), 0);
    }

    @Test
    public void findEmoticon_emoticon_inception() throws Exception {
        List<Finder.Match> emoticons = mEmoticonFinder.fetchUsingRegex("(wi(wink)nk)");
        assertEquals(emoticons.get(0).string, "wink");
    }

}
