<%@ page import="java.util.regex.*" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.net.URI" %>

<!-- This jsp is the first implementation of an urlValidator.
Since this jsp isn't a simple page anymore it was refactored into a servlet: "UrlValidator.java".
This jsp is technically obsolete.
-->

<%
    // Get the 'url' parameter from the request
    String url = request.getParameter("url");

    // Check if the parameter is not null and not empty
    if(url != null && !url.isEmpty()) {
        // Compile a regex pattern to match the URL format
        Pattern pattern = Pattern.compile("^www\\.\\w+\\.\\w+$");
        Matcher matcher = pattern.matcher(url);

        // Check if the URL matches the expected format
        if(matcher.matches()) {
            try {
                // Create a URI object with the URL
                URI siteURI = new URI("http://" + url);

                // Open a connection to the URL
                HttpURLConnection connection = (HttpURLConnection) siteURI.toURL().openConnection();

                // Set the request method to GET
                connection.setRequestMethod("GET");

                // Connect to the URL
                connection.connect();

                // Get the response code
                int code = connection.getResponseCode();

                // Check if the response code is not 404 (Not Found)
                if(code != 404) {
                    // If the website exists, redirect the client to the URL
                    response.setStatus(301);
                    response.setHeader("Location", "http://" + url);
                    response.setHeader("Connection", "close");
                } else {
                    // If the website does not exist, redirect the client back to the input page with an error message
                    response.sendRedirect("urlInputAdvanced.jsp?errorMessage=The+Website+doesnt+exist.&input=" + url);
                }
            } catch (Exception e) {
                // If an exception occurs, redirect the client back to the input page with an error message
                response.sendRedirect("urlInputAdvanced.jsp?errorMessage=The+website+doesnt+exist.&input=" + url);
            }
        } else {
            // If the URL does not match the expected format, redirect the client back to the input page with an error message
            response.sendRedirect("urlInputAdvanced.jsp?errorMessage=The+entered+URL+does+not+match+the+expected+format+(www.name.suffix).&input=" + url);
        }
    } else {
        // If the 'url' parameter is null or empty, redirect the client back to the input page with an error message
        response.sendRedirect("urlInputAdvanced.jsp?errorMessage=No+valid+website+address+entered.&input=");
    }
%>
