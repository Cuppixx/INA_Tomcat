package org.ina.p4;

import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Concrete servlet for adding a feed URL to the data.
 * Extends the abstract base class `UrlValidatorBase`.
 */
@WebServlet(name = "urlValidatorAdd", value = "/url-validator-add")
public class UrlValidatorAdd extends UrlValidatorBase {

    /**
     * Performs the action of adding a feed URL to the data.
     *
     * @param feedUrl The feed URL associated with the website.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doAction(String feedUrl) throws IOException {
        ch.addToData(feedUrl);
        response.sendRedirect("org/ina/p4/urlInputAdvanced.jsp?errorMessage=Url+added");
    }
}

