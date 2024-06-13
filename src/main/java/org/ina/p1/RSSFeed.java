package org.ina.p1;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Class for handling RSS feeds.
 */
public class RSSFeed {
    /**
     * Finds the RSS feed URL from a given URL.
     *
     * @param url the URL to find the RSS feed from
     * @return the RSS feed URL, or null if no RSS feed was found
     */
    protected String feedFinder(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {url = "http://" + url;}
        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("link[type=application/rss+xml]");
            if (!links.isEmpty()) {
                String rssLink = links.attr("href");
                System.out.println("RSS-Feed found: " + rssLink);
                return rssLink;
            } else {System.out.println("No RSS-Feed found!");}
        } catch (IOException e) {e.printStackTrace();}
        return null;
    }

    /**
     * Loads the RSS feed from a given URL.
     *
     * @param feedUrl the URL of the RSS feed
     * @return the loaded RSS feed, or null if the feed could not be loaded
     */
    private SyndFeed feedLoader(String feedUrl) {
        try {
            URI url = new URI(feedUrl);
            SyndFeedInput input = new SyndFeedInput();
            return input.build(new XmlReader(url.toURL()));
        } catch (IOException | FeedException | URISyntaxException e) {e.printStackTrace();}
        return null;
    }

    /**
     * Interprets an RSS feed from a given URL.
     *
     * @param feedUrl the URL of the RSS feed
     * @param showTitle whether to show the title of each entry
     * @param showLink whether to show the link of each entry
     * @param showDescription whether to show the description of each entry
     */
    protected void feedInterpreter(String feedUrl, boolean showTitle, boolean showLink, boolean showDescription) {
        SyndFeed feed = feedLoader(feedUrl);
        List<SyndEntry> entries;
        if (feed != null) {
            entries = feed.getEntries();
            for (SyndEntry entry : entries) {
                if (showTitle) {System.out.println("Title: " + entry.getTitle());}
                if (showLink) {System.out.println("Link: " + entry.getLink());}
                if (showDescription) {System.out.println("Description: " + entry.getDescription().getValue());}
                System.out.println();
            }
        }
    }
}
