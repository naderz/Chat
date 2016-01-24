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
        List<String> mentions = MentionFinder.fetch("@chris you around?");
        assertEquals(mentions.get(0), "chris");
    }

    @Test
    public void findMentions_multiple() throws Exception {
        List<String> mentions = MentionFinder.fetch("@chris and @john you around?");
        assertEquals(mentions.get(0), "chris");
        assertEquals(mentions.get(1), "john");
    }

    @Test
    public void findMentions_no_match() throws Exception {
        List<String> mentions = MentionFinder.fetch("Nothing to find here!");
        assertEquals(mentions.size(), 0);
    }


    @Test
    public void findMentions_mention_within_email() throws Exception {
        List<String> mentions = MentionFinder.fetch("nader@chris.com");
        assertEquals(mentions.get(0), "chris");
    }


}
