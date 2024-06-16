package org.ina.p4;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import jakarta.servlet.annotation.WebServlet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * Servlet for validating RSS feeds.
 */
@WebServlet(name = "rssFeedValidator", value = "/rss-feed-validator")
public class RSSFeedValidator extends HttpServlet {
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url1 = req.getParameter("url");
        String url2 = req.getParameter("url2");
        String url3 = req.getParameter("url3");

        String[] urls = {url1, url2, url3};

        for (String url : urls) {
            String feedUrl = feedFinder(url);
            if (feedUrl != null) {
                SyndFeed feed = feedLoader(feedUrl);
                if (feed != null) {
                    req.setAttribute("feed", feed);
                    req.getRequestDispatcher("/RSSFeedReader").forward(req, resp);
                }
            }
        }
    }
}
