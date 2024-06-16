<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- The title of the HTML document -->
    <title>JSP - INA_Praktikum_Borgert</title>
  </head>
  <body>
    <!-- Display a heading -->
    <h1><%= "JSP - INA_Overview" %></h1>
    <br/>
    <!-- Link to the Hello Servlet -->
    <a href="hello-servlet">Hello Servlet</a>
    <br/>
    <!-- Link to the UrlParamReader JSP with a URL parameter -->
    <a href="org/ina/p2/urlReader.jsp?url=HelloUrlReader">P2 - Part 3.0: UrlParamReader Jsp</a>
    <br/>
    <!-- Link to the UrlParamReader Servlet with multiple URL parameters -->
    <a href="url-param-reader?url=HelloUrlReader&url2=AnotherUrl&url3=AnotherUrl">P2 - Part 3.0: UrlParamReader Servlet</a>
    <br/>
    <!-- Link to the Input JSP -->
    <a href="org/ina/p2/urlInput.jsp">P2 - Part 4.1: Input Jsp</a>
    <br/>
    <!-- Link to the advanced Input JSP with URL parameters for input and error message -->
    <a href="org/ina/p2/urlInputAdvanced.jsp?errorMessage=Format+is+(www.name.suffix)">P2 - Part 4.0/5.0: Input Jsp Advanced</a>
    <br/>
    <!-- Link to the advanced Input JSP with URL parameters for input and error message -->
    <a href="org/ina/p3/urlInputAdvanced.jsp?errorMessage=Format+is+(www.name.suffix)">P3 - Part 2.0: Input Jsp Advanced</a>
    <br/>
    <!-- Link to the advanced Input JSP with URL parameters for input and error message -->
    <a href="org/ina/p4/urlInputAdvanced.jsp?errorMessage=Format+is+(www.name.suffix)">P4 - Part 1.0: Input Jsp Advanced</a>
  </body>
</html>
