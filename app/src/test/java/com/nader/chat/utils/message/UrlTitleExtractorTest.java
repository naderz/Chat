package com.nader.chat.utils.message;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class UrlTitleExtractorTest {

    private UrlTitleExtractor mExtractor;

    @Before
    public void init() {
        mExtractor = new UrlTitleExtractor();
    }

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
        assertEquals(mExtractor.extractTitleFromHtml(stringBuilder), "Testing");
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
        assertEquals(mExtractor.extractTitleFromHtml(stringBuilder), null);
    }

    @Test
    public void extractUrl_no_html() throws Exception {
        StringBuilder stringBuilder = new StringBuilder("");
        assertEquals(mExtractor.extractTitleFromHtml(stringBuilder), null);
    }

    @Test
    public void extractUrl_null() throws Exception {
        assertNull(mExtractor.extractTitleFromHtml(null));
    }

    @Test
    public void formatUrl_working_full_url() throws Exception {
        String title = mExtractor.formatUrl("http://www.google.com");
        assertEquals(title, "http://www.google.com");
    }

    @Test
    public void formatUrl_working_no_protocol_url() throws Exception {
        String title = mExtractor.formatUrl("www.google.com");
        assertEquals(title, "http://www.google.com");
    }

    @Test
    public void formatUrl_working_no_protocol_no_www_url() throws Exception {
        String title = mExtractor.formatUrl("google.com");
        assertEquals(title, "http://google.com");
    }

    @Test
    public void formatUrl_working_ftp_protocol_url() throws Exception {
        String title = mExtractor.formatUrl("ftp://testing.com");
        assertEquals(title, "ftp://testing.com");
    }

    @Test
    public void formatUrl_null() throws Exception {
        assertNull(mExtractor.formatUrl(null));
    }

}