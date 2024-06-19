package org.ina.p4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ina.p4.beans.FeedBean;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract base servlet for URL validation and handling.
 * Subclasses must implement the `doAction` method.
 */
@WebServlet(name = "urlValidatorP4", value = "/url-validator-p4")
public abstract class UrlValidatorBase extends HttpServlet {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ControllerHelper ch;

    /**
     * Handles HTTP GET requests.
     *
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws IOException      If an I/O error occurs.
     * @throws ServletException If a servlet-specific error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.request = request;
        this.response = response;

        if (request.getSession().getAttribute("helper") == null)
            ch = new ControllerHelper(request, response);
        else
            ch = (ControllerHelper) request.getSession().getAttribute("helper");
        ch.doGet(request, response);

        String url = request.getParameter("url");
        if (url != null && !url.isEmpty()) {
            Pattern pattern = Pattern.compile("^www\\.\\w+\\.\\w+$");
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                try {
                    URI siteURI = new URI("http://" + url);
                    HttpURLConnection connection = (HttpURLConnection) siteURI.toURL().openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    int code = connection.getResponseCode();
                    if (code != 404) {
                        String feedUrl = ch.feedFinder(url);
                        if (feedUrl != null) {
                            doAction(feedUrl);
                            System.out.println(((FeedBean) request.getSession().getAttribute("data")).getFeedUrls());
                        } else {
                            response.sendRedirect("org/ina/p4/urlInputAdvanced.jsp?errorMessage=No+feed+found+for+URL.&input=" + url);
                        }
                    } else {
                        response.sendRedirect("org/ina/p4/urlInputAdvanced.jsp?errorMessage=The+Website+doesnt+exist.&input=" + url);
                    }
                } catch (Exception e) {
                    response.sendRedirect("org/ina/p4/urlInputAdvanced.jsp?errorMessage=The+website+doesnt+exist.&input=" + url);
                }
            } else {
                response.sendRedirect("org/ina/p4/urlInputAdvanced.jsp?errorMessage=The+entered+URL+does+not+match+the+expected+format+(www.name.suffix).&input=" + url);
            }
        } else {
            response.sendRedirect("org/ina/p4/urlInputAdvanced.jsp?errorMessage=No+valid+website+address+entered.&input=");
        }
    }

    /**
     * Abstract method to be implemented by subclasses.
     * Performs specific actions based on the feed URL.
     *
     * @param feedUrl The feed URL associated with the website.
     * @throws IOException If an I/O error occurs.
     */
    protected abstract void doAction(String feedUrl) throws IOException;
}

