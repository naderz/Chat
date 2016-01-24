package com.nader.chat.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by nader on 22/01/16.
 */
public class UrlTitleExtractorTest {

    @Test
    public void extractUrl_working() throws Exception {

        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Testing</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>This is a Heading</h1>\n" +
                "<p>This is a paragraph.</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        StringBuilder stringBuilder = new StringBuilder(html);
        assertEquals(UrlTitleExtractor.extractTitleFromHtml(stringBuilder), "Testing");
    }

    @Test
    public void extractUrl_no_title_tag() throws Exception {

        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>This is a Heading</h1>\n" +
                "<p>This is a paragraph.</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        StringBuilder stringBuilder = new StringBuilder(html);
        assertEquals(UrlTitleExtractor.extractTitleFromHtml(stringBuilder), null);
    }

    @Test
    public void extractUrl_no_html() throws Exception {
        StringBuilder stringBuilder = new StringBuilder("");
        assertEquals(UrlTitleExtractor.extractTitleFromHtml(stringBuilder), null);
    }

    @Test
    public void extractUrl_null() throws Exception {
        assertNull(UrlTitleExtractor.extractTitleFromHtml(null));
    }

    @Test
    public void formatUrl_working_full_url() throws Exception {
        String title = UrlTitleExtractor.formatUrl("http://www.google.com");
        assertEquals(title, "http://www.google.com");
    }

    @Test
    public void formatUrl_working_no_protocol_url() throws Exception {
        String title = UrlTitleExtractor.formatUrl("www.google.com");
        assertEquals(title, "http://www.google.com");
    }

    @Test
    public void formatUrl_working_no_protocol_no_www_url() throws Exception {
        String title = UrlTitleExtractor.formatUrl("google.com");
        assertEquals(title, "http://google.com");
    }

    @Test
    public void formatUrl_working_ftp_protocol_url() throws Exception {
        String title = UrlTitleExtractor.formatUrl("ftp://testing.com");
        assertEquals(title, "ftp://testing.com");
    }

    @Test
    public void formatUrl_null() throws Exception {
        assertNull(UrlTitleExtractor.formatUrl(null));
    }

}