package org.ina.p4;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ina.p4.beans.FeedBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ControllerHelper} class manages data flow between the {@code InputBean} model
 * and the servlet that processes user requests. It encapsulates logic for building and
 * populating the {@code InputBean} object with data from request parameters.
 */
public class ControllerHelper extends HelperBase implements Serializable {

    protected FeedBean data;
    private final List<String> feedUrls = new ArrayList<>();

    /**
     * Constructs a new {@code ControllerHelper} instance by initializing the base class with
     * the provided request and response objects.
     *
     * @param request  The {@link HttpServletRequest} object containing the client's request.
     * @param response The {@link HttpServletResponse} object containing the servlet's response.
     */
    public ControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    /**
     * Handles the HTTP GET request by delegating to the base class.
     * Ensures that a {@code ControllerHelper} instance is stored in the session.
     *
     * @param request  The {@link HttpServletRequest} object containing the client's request.
     * @param response The {@link HttpServletResponse} object containing the servlet's response.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        if (request.getSession().getAttribute("helper") == null) {
            request.getSession().setAttribute("helper", this);
        }
    }

    /**
     * Adds a feed URL to the data and rebuilds the data object.
     *
     * @param feedUrl The feed URL to add.
     */
    protected void addToData(String feedUrl) {
        feedUrls.add(feedUrl);
        buildData();
    }

    /**
     * Removes a feed URL from the data and rebuilds the data object.
     *
     * @param feedUrl The feed URL to remove.
     */
    protected void removeFromData(String feedUrl) {
        feedUrls.remove(feedUrl);
        buildData();
    }

    /**
     * Builds the {@code FeedBean} data object based on the current feed URLs.
     */
    private void buildData() {
        data = (new FeedBean());
        data.setFeedUrls(feedUrls);
        request.getSession().setAttribute("data", data);
    }

    /**
     * Finds a feed URL associated with the given website URL.
     *
     * @param url The website URL to search for a feed.
     * @return The feed URL if found, or null if not.
     */
    protected String feedFinder(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {url = "http://" + url;}
        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("link[type=application/rss+xml]");
            if (!links.isEmpty()) {
                return links.attr("href");
            }
        } catch (IOException e) {e.printStackTrace();}
        return null;
    }

    /**
     * Loads a SyndFeed from the specified feed URL.
     *
     * @param feedUrl The feed URL to load.
     * @return The loaded SyndFeed or null if an error occurs.
     */
    protected SyndFeed feedLoader(String feedUrl) {
        try {
            URI url = new URI(feedUrl);
            SyndFeedInput input = new SyndFeedInput();
            return input.build(new XmlReader(url.toURL()));
        } catch (IOException | FeedException | URISyntaxException e) {e.printStackTrace();}
        return null;
    }
}
