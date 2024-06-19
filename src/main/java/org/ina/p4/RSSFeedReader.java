package org.ina.p4;

import java.io.*;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.ina.p4.beans.FeedBean;

import java.util.List;

/**
 * Servlet implementation class RSSFeedReader.
 * Reads URL parameters and displays RSS feed entries.
 */
@WebServlet(name = "rssFeedReader", value = "/rss-feed-reader")
public class RSSFeedReader extends HttpServlet {

    /**
     * This method is called by the servlet container to indicate to a servlet that the
     * servlet is being placed into service.
     * This implementation does nothing.
     */
    @Override
    public void init() {}

    /**
     * Handles HTTP GET requests to display RSS feed entries.
     *
     * @param request  The {@link HttpServletRequest} object containing the client's request.
     * @param response The {@link HttpServletResponse} object containing the servlet's response.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        List<String> feedUrls = ((FeedBean)request.getSession().getAttribute("data")).getFeedUrls();
        ControllerHelper ch;
        if (request.getSession().getAttribute("helper") == null)
            ch = new ControllerHelper(request, response);
        else
            ch = (ControllerHelper)request.getSession().getAttribute("helper");
        ch.doGet(request, response);

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");

            if (feedUrls.isEmpty()) {out.println("<h1>No RSS-Feeds found</h1>");
            } else {
                out.println("<h1>RSS-Feed URLs:</h1>");
                for (String feedUrl : feedUrls) {
                    out.println("<h2>Feeds from: <a href=\"" + feedUrl + "\">" + feedUrl + "</a></h2>");

                    SyndFeed feed = ch.feedLoader(feedUrl);
                    if (feed != null) {
                        for (SyndEntry entry : feed.getEntries()) {
                            out.println("<a href=\"" + entry.getLink() + "\">" + entry.getTitle() + "</a><br>");
                        }
                    }
                    out.println("<br><br>");
                }
            }
            out.println("</body></html>");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Called by the servlet container to indicate to a servlet that the servlet is being taken out of service.
     * This implementation does nothing.
     */
    @Override
    public void destroy() {}
}

