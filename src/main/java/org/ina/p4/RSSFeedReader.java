package org.ina.p4;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.ina.p4.beans.FeedBean;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Servlet implementation class UrlParamReader.
 * This servlet reads URL parameters and displays them.
 * An advanced version of the basic jsp urlReader that can read multiple parameters.
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
     * Called by the server (via the service method) to allow a servlet to handle a GET request.
     * This method reads URL parameters and writes them to the response, or writes "Hello World!" if no parameters are present.
     *
     * @param request  an HttpServletRequest object that contains the request the client has made of the servlet
     * @param response an HttpServletResponse object that contains the response the servlet sends to the client
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        List<String> feedUrls = ((FeedBean)request.getSession().getAttribute("data")).getFeedUrls();

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");

            if (feedUrls.isEmpty()) {
                out.println("<h1>Keine Feeds verfügbar.</h1>");
            } else {
                out.println("<h1>Feed URLs:</h1>");
                for (String feedUrl : feedUrls) {
                    out.println(feedUrl + "<br>");
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

