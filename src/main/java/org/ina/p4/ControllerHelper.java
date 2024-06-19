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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ControllerHelper} class extends {@code HelperBase} and is responsible for
 * managing the data flow between the {@code InputBean} model and the servlet that processes
 * user requests. It encapsulates the logic to build and populate the {@code InputBean} object
 * with data from the request parameters.
 */
public class ControllerHelper extends HelperBase {

    protected FeedBean data;
    private final List<String> feedUrls = new ArrayList<>();

    /**
     * Constructs a new {@code ControllerHelper} instance by initializing the base class with
     * the provided request and response objects.
     *
     * @param request  the {@link HttpServletRequest} object that contains the client's request
     * @param response the {@link HttpServletResponse} object that contains the servlet's response
     */
    public ControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    /**
     * Builds the {@code InputBean} data model by extracting parameters from the request object.
     * It initializes a new {@code InputBean} instance and sets its properties using the request parameters.
     */
    protected void buildData(String feedUrl){
        feedUrls.add(feedUrl);
        data = new FeedBean();
        data.setFeedUrls(feedUrls);
        request.getSession().setAttribute("data", data);
    }

    /**
     * Handles the HTTP GET request by delegating to the base class and then building the data model.
     * It ensures that a {@code ControllerHelper} instance is stored in the session and sets the
     * {@code InputBean} model as a session attribute for further processing.
     *
     * @param request  the {@link HttpServletRequest} object that contains the client's request
     * @param response the {@link HttpServletResponse} object that contains the servlet's response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        if (request.getSession().getAttribute("helper") == null) {
            request.getSession().setAttribute("helper", this);
        }
    }

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

    private SyndFeed feedLoader(String feedUrl) {
        try {
            URI url = new URI(feedUrl);
            SyndFeedInput input = new SyndFeedInput();
            return input.build(new XmlReader(url.toURL()));
        } catch (IOException | FeedException | URISyntaxException e) {e.printStackTrace();}
        return null;
    }
}
