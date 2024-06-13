package org.ina.p2;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

/**
 * Servlet implementation class HelloServlet.
 * This servlet responds with a simple "Hello Servlet!" message.
 * Created for testing the tomcat server, technically obsolete.
 */
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    /**
     * This method is called by the servlet container to indicate to a servlet that the
     * servlet is being placed into service.
     * Here, we initialize the message to "Hello Servlet!".
     */
    @Override
    public void init() {
        message = "Hello Servlet!";
    }

    /**
     * Called by the server (via the service method) to allow a servlet to handle a GET request.
     * This method writes an HTML document to the client with a "Hello Servlet!" message.
     *
     * @param request  an HttpServletRequest object that contains the request the client has made of the servlet
     * @param response an HttpServletResponse object that contains the response the servlet sends to the client
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>" + message + "</h1>");
            out.println("</body></html>");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Called by the servlet container to indicate to a servlet that the servlet is being taken out of service.
     * This implementation does nothing.
     */
    @Override
    public void destroy() {}
}
