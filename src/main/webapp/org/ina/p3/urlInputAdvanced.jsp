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

<!-- Start of the form -->
<form action="${pageContext.request.contextPath}/url-validator-p3">
    <!-- Label for the text input field -->
    <label for="url">Website-Address:</label>

    <!-- Text input field for entering the website address. It is pre-filled with the previous input if one exists -->
    <input type="text" id="url" name="url" placeholder="www.enter_url_here.de" value="<%=previousInput != null ? previousInput : ""%>"><br>

    <!-- Additional fields for name and two more URLs -->
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" placeholder="Enter your name"><br>

    <label for="url2">Additional Website-Address 1:</label>
    <input type="text" id="url2" name="url2" placeholder="www.enter_url_here.de"><br>

    <label for="url3">Additional Website-Address 2:</label>
    <input type="text" id="url3" name="url3" placeholder="www.enter_url_here.de"><br>

    <!-- Submit button for the form -->
    <input type="submit" value="Submit">
</form>
<!-- End of the form -->
</body>
</html>
