package org.ina.p4;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
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
 * The {@code UrlValidator} class extends {@link HttpServlet} and provides
 * a mechanism to validate a URL provided by the user. It checks the format of the URL and its existence
 * by attempting to establish a connection to it.
 *
 * <p>If the URL is valid and reachable, the servlet redirects the client to the specified URL.
 * If the URL is invalid or unreachable, the client is redirected back to the input page with an appropriate
 * error message.</p>
 *
 * <p>This servlet is mapped to "/url-validator-p3" and can be accessed via the GET HTTP method.</p>
 */
@WebServlet(name = "urlValidatorP4", value = "/url-validator-p4")
public class UrlValidator extends HttpServlet {

    /**
     * Processes requests for both HTTP {@code GET} methods.
     *
     * <p>This method retrieves the 'url' parameter from the request, validates its format, and checks
     * for its existence. If the URL is valid and exists, it redirects the client to the URL. If the URL
     * is not valid or does not exist, it redirects the client back to the input page with an error message.</p>
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     * @throws ServletException if the request could not be handled
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ControllerHelper ch;
        if (request.getSession().getAttribute("helper") == null)
            ch = new ControllerHelper(request, response);
        else
            ch = (ControllerHelper)request.getSession().getAttribute("helper");
        ch.doGet(request, response);

        String url = request.getParameter("url");
        if(url != null && !url.isEmpty()) {
            Pattern pattern = Pattern.compile("^www\\.\\w+\\.\\w+$");
            Matcher matcher = pattern.matcher(url);
            if(matcher.matches()) {
                try {
                    URI siteURI = new URI("http://" + url);
                    HttpURLConnection connection = (HttpURLConnection) siteURI.toURL().openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    int code = connection.getResponseCode();
                    if(code != 404) {
                        String feedUrl = ch.feedFinder(url);
                        if (feedUrl != null) {
                            ch.buildData(feedUrl);
                            System.out.println(((FeedBean)request.getSession().getAttribute("data")).getFeedUrls());
                            response.sendRedirect("org/ina/p4/urlInputAdvanced.jsp?errorMessage=Url+added");
                        }
                        else {
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
}
