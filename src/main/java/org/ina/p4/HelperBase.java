package org.ina.p4;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * The {@code HelperBase} class is an abstract class that provides a framework for handling
 * HTTP requests and responses within a servlet context. It defines the basic structure and
 * lifecycle for a helper class that will process servlet requests.
 *
 * <p>This class is designed to be extended by concrete helper classes that implement the
 * {@code doGet} method to handle GET requests according to specific business logic.</p>
 */
public abstract class HelperBase {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    /**
     * Constructor for {@code HelperBase} that initializes the request and response objects.
     *
     * @param request  the {@link HttpServletRequest} object that contains the client's request
     * @param response the {@link HttpServletResponse} object that contains the servlet's response
     */
    protected HelperBase(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * An abstract method that must be implemented by subclasses to handle the HTTP GET request.
     *
     * <p>This method should contain the logic to process the request and generate a response
     * that will be sent back to the client.</p>
     *
     * @param request  the {@link HttpServletRequest} object that contains the client's request
     * @param response the {@link HttpServletResponse} object that contains the servlet's response
     * @throws ServletException if the request could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
