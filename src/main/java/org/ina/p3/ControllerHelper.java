package org.ina.p3;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ina.p3.beans.InputBean;

/**
 * The {@code ControllerHelper} class extends {@code HelperBase} and is responsible for
 * managing the data flow between the {@code InputBean} model and the servlet that processes
 * user requests. It encapsulates the logic to build and populate the {@code InputBean} object
 * with data from the request parameters.
 */
public class ControllerHelper extends HelperBase {

    protected InputBean data;

    /**
     * Constructs a new {@code ControllerHelper} instance by initializing the base class with
     * the provided request and response objects.
     *
     * @param request  the {@link HttpServletRequest} object that contains the client's request
     * @param response the {@link HttpServletResponse} object that contains the servlet's response
     */
    public ControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    /**
     * Builds the {@code InputBean} data model by extracting parameters from the request object.
     * It initializes a new {@code InputBean} instance and sets its properties using the request parameters.
     */
    private void buildData(){
        data = new InputBean();
        data.setUrl(request.getParameter("url"));
        data.setName(request.getParameter("name"));
        data.setUrl2(request.getParameter("url2"));
        data.setUrl3(request.getParameter("url3"));
    }

    /**
     * Handles the HTTP GET request by delegating to the base class and then building the data model.
     * It ensures that a {@code ControllerHelper} instance is stored in the session and sets the
     * {@code InputBean} model as a session attribute for further processing.
     *
     * @param request  the {@link HttpServletRequest} object that contains the client's request
     * @param response the {@link HttpServletResponse} object that contains the servlet's response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        if (request.getSession().getAttribute("helper") == null) {
            request.getSession().setAttribute("helper", this);
        }
        buildData();
        request.getSession().setAttribute("data", data);
    }
}
