package com.nader.chat.utils.finders;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by nader on 22/01/16.
 */
public class MentionFinderTest {


    @Test
    public void findMentions_returns_one_result() throws Exception {
        List<Finder.Match> mentions = MentionFinder.fetch("@chris you around?");
        assertEquals(mentions.get(0).string, "chris");
    }

    @Test
    public void findMentions_returns_one_result_from_the_end() throws Exception {
        List<Finder.Match> mentions = MentionFinder.fetch("How are you @chris");
        assertEquals(mentions.get(0).string, "chris");
    }

    @Test
    public void findMentions_multiple() throws Exception {
        List<Finder.Match> mentions = MentionFinder.fetch("@chris and @john you around?@anyone");
        assertEquals(mentions.get(0).string, "chris");
        assertEquals(mentions.get(1).string, "john");
        assertEquals(mentions.get(2).string, "anyone");
    }

    @Test
    public void findMentions_no_match() throws Exception {
        List<Finder.Match> mentions = MentionFinder.fetch("Nothing to find here!");
        assertEquals(mentions.size(), 0);
    }


    @Test
    public void findMentions_mention_within_email() throws Exception {
        List<Finder.Match> mentions = MentionFinder.fetch("nader@chris.com");
        assertEquals(mentions.get(0).string, "chris");
    }


}
