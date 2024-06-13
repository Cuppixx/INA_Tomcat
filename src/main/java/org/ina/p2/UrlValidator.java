package org.ina.p2;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

/**
 * Servlet implementation class UrlValidator.
 * This servlet validates a URL entered by the user and redirects to the URL if it is valid and exists.
 * If the URL is not valid or does not exist, it redirects the client back to the input page with an error message.
 */
@WebServlet(name = "urlValidatorP2", value = "/url-validator-p2")
public class UrlValidator extends HttpServlet {

    /**
     * Handles the HTTP GET method.
     * This method retrieves the 'url' parameter from the request, validates it, and checks if the URL exists.
     * If the URL is valid and exists, it redirects the client to the URL.
     * If the URL is not valid or does not exist, it redirects the client back to the input page with an error message.
     *
     * @param request  an HttpServletRequest object that contains the request the client has made of the servlet
     * @param response an HttpServletResponse object that contains the response the servlet sends to the client
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                        response.setStatus(301);
                        response.setHeader("Location", "http://" + url);
                        response.setHeader("Connection", "close");
                    } else {
                        response.sendRedirect("org/ina/p2/urlInputAdvanced.jsp?errorMessage=The+Website+doesnt+exist.&input=" + url);
                    }
                } catch (Exception e) {
                    response.sendRedirect("org/ina/p2/urlInputAdvanced.jsp?errorMessage=The+website+doesnt+exist.&input=" + url);
                }
            } else {
                response.sendRedirect("org/ina/p2/urlInputAdvanced.jsp?errorMessage=The+entered+URL+does+not+match+the+expected+format+(www.name.suffix).&input=" + url);
            }
        } else {
            response.sendRedirect("org/ina/p2/urlInputAdvanced.jsp?errorMessage=No+valid+website+address+entered.&input=");
        }
    }
}
