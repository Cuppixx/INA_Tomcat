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
<!-- Server-side script to display an error message if one exists.
At this point error messages behaves more like an info message-->
<%
    String errorMessage = request.getParameter("errorMessage");
    if(errorMessage != null && !errorMessage.isEmpty()) {
        out.println("<p style='color:red;'>" + errorMessage + "</p>");
    }
%>

<!-- Start of the forms -->
<form action="${pageContext.request.contextPath}/url-validator-add">
    <!-- Label for the text input field -->
    <label for="urlAdd">Url:</label>

    <!-- Text input field for entering the website address-->
    <input type="text" id="urlAdd" name="url" placeholder="www.enter_url_here.de">

    <!-- Submit button for the form -->
    <input type="submit" value="Add Url to List">
</form>

<form action="${pageContext.request.contextPath}/url-validator-remove">
    <!-- Label for the text input field -->
    <label for="urlRemove">Url:</label>

    <!-- Text input field for entering the website address-->
    <input type="text" id="urlRemove" name="url" placeholder="www.enter_url_here.de">

    <!-- Submit button for the form -->
    <input type="submit" value="Remove Url from List">
</form>

<form action="${pageContext.request.contextPath}/rss-feed-reader">
    <input type="submit" value="Show RSS-Feeds">
</form>
<!-- End of the forms -->
</body>
</html>
