<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // Get the 'url' parameter from the request
    String param = request.getParameter("url");

    // Check if the parameter is not null and not empty
    // If the parameter exists, print it
    // If the parameter does not exist, print a message
    if(param != null && !param.isEmpty()) {out.println("URL-Parameter: " + param);
    } else {out.println("No URL-Parameter found.");}
%>
