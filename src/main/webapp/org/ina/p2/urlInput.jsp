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

<!-- Start of the form -->
<form action="urlReader.jsp">
    <!-- Label for the text input field -->
    <label for="url">Website-Address:</label>

    <!-- Text input field for entering the website address -->
    <input type="text" id="url" name="url"><br>

    <!-- Submit button for the form -->
    <input type="submit" value="Submit">
</form>
<!-- End of the form -->
</body>
</html>
