package org.ina.p4;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.Enumeration;

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

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");

            Enumeration<String> parameterNames = request.getParameterNames();

            if (!parameterNames.hasMoreElements()) {
                out.println("<h1>Hello World!</h1>");
            } else {
                while (parameterNames.hasMoreElements()) {
                    String paramName  = parameterNames.nextElement();
                    String paramValue = request.getParameter(paramName);
                    out.println(paramName + ": " + paramValue + "<br>");
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

