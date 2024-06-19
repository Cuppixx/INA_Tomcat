<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- The title of the HTML document -->
    <title>HTML - Input</title>
</head>
<body>
<!-- Display a heading -->
<h2>Enter Website Address</h2>
<!-- Server-side script to display an error message if one exists -->
<%
    String errorMessage = request.getParameter("errorMessage");
    String previousInput = request.getParameter("input");
    if(errorMessage != null && !errorMessage.isEmpty()) {out.println("<p style='color:red;'>" + errorMessage + "</p>");}
%>

<!-- Start of the forms -->
<form action="${pageContext.request.contextPath}/url-validator-p4">
    <!-- Label for the text input field -->
    <label for="url">Website-Address:</label>

    <!-- Text input field for entering the website address. It is pre-filled with the previous input if one exists -->
    <input type="text" id="url" name="url" placeholder="www.enter_url_here.de" value="<%=previousInput != null ? previousInput : ""%>"><br>

    <!-- Submit button for the form -->
    <input type="submit" value="Add Url to List">
</form>
<form action="${pageContext.request.contextPath}/rss-feed-reader">
    <input type="submit" value="Get RSS Feeds">
</form>
<!-- End of the forms -->
</body>
</html>
