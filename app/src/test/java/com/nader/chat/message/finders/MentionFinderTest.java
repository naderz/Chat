package com.nader.chat.message.finders;

import com.nader.chat.shared.message.finders.Finder;
import com.nader.chat.shared.message.finders.MentionFinder;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class MentionFinderTest {


    private Finder mMetionFinder;

    @Before
    public void init() {
        mMetionFinder = new MentionFinder();
    }

    @Test
    public void findMentions_returns_one_result() throws Exception {
        List<Finder.Match> mentions = mMetionFinder.fetchUsingRegex("@chris you around?");
        assertEquals(mentions.get(0).string, "chris");
    }

    @Test
    public void findMentions_returns_one_result_from_the_end() throws Exception {
        List<Finder.Match> mentions = mMetionFinder.fetchUsingRegex("How are you @chris");
        assertEquals(mentions.get(0).string, "chris");
    }

    @Test
    public void findMentions_multiple() throws Exception {
        List<Finder.Match> mentions = mMetionFinder.fetchUsingRegex("@chris and @john you around?@anyone");
        assertEquals(mentions.get(0).string, "chris");
        assertEquals(mentions.get(1).string, "john");
        assertEquals(mentions.get(2).string, "anyone");
    }

    @Test
    public void findMentions_no_match() throws Exception {
        List<Finder.Match> mentions = mMetionFinder.fetchUsingRegex("Nothing to find here!");
        assertEquals(mentions.size(), 0);
    }

    @Test
    public void findMentions_mention_within_email() throws Exception {
        List<Finder.Match> mentions = mMetionFinder.fetchUsingRegex("nader@chris.com");
        assertEquals(mentions.get(0).string, "chris");
    }

}