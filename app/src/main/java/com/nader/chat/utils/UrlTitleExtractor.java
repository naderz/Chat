package com.nader.chat.utils;

/**
 * Created by nader on 22/01/16.
 */

import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UrlTitleExtractor {
    /* the CASE_INSENSITIVE flag accounts for
     * sites that use uppercase title tags.
     * the DOTALL flag accounts for sites that have
     * line feeds in the title text */
    private static final Pattern TITLE_TAG =
            Pattern.compile("<title>(.*)</title>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);


    static String formatUrl(final String url) {
        if (url != null && !url.contains("://")) {
            return "http://" + url;
        }
        return url;
    }


    public static String getPageTitle(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(formatUrl(url))
                .build();

        Response response = client.newCall(request).execute();

        ContentType contentType = new ContentType(response.headers().get("Content-Type"));

        if (contentType.type == null || !contentType.type.equalsIgnoreCase("text/html")) {
            return null;// don't continue if not HTML
        } else {
            // determine the charset, or use the default
            Charset charset = getCharset(contentType.charset);

            // read the response body, using BufferedReader for performance
            InputStream in = response.body().byteStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, charset));
            int n, totalRead = 0;
            char[] buf = new char[1024];
            StringBuilder content = new StringBuilder();

            // read until EOF or first 8192 characters
            while (totalRead < 8192 && (n = reader.read(buf, 0, buf.length)) != -1) {
                content.append(buf, 0, n);
                totalRead += n;
            }
            reader.close();
            return extractTitleFromHtml(content);

        }
    }

    @Nullable
    static String extractTitleFromHtml(StringBuilder content) {
        // extract the title
        if (content != null) {
            Matcher matcher = TITLE_TAG.matcher(content);
            if (matcher.find()) {
            /* replace any occurrences of whitespace (which may
             * include line feeds and other uglies) as well
             * as HTML brackets with a space */
                return matcher.group(1).replaceAll("[\\s<>]+", " ").trim();
            }
        }
        return null;
    }


    private static Charset getCharset(String charsetName) {
        if (charsetName != null && Charset.isSupported(charsetName))
            return Charset.forName(charsetName);
        else {
            return Charset.defaultCharset();
        }
    }

    /**
     * Class holds the content type and charset (if present)
     */
    private static final class ContentType {
        private static final Pattern CHARSET_HEADER = Pattern.compile("charset=([-_a-zA-Z0-9]+)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

        private String type;
        private String charset;

        private ContentType(String headerValue) {
            if (headerValue == null)
                throw new IllegalArgumentException("ContentType must be constructed with a not-null headerValue");
            int n = headerValue.indexOf(";");
            if (n != -1) {
                type = headerValue.substring(0, n);
                Matcher matcher = CHARSET_HEADER.matcher(headerValue);
                if (matcher.find())
                    charset = matcher.group(1);
            } else {
                type = headerValue;
            }
        }
    }
}