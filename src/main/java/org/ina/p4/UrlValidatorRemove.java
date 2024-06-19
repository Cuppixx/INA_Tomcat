package org.ina.p4;

import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Concrete servlet for removing a feed URL from the data.
 * Extends the abstract base class `UrlValidatorBase`.
 */
@WebServlet(name = "urlValidatorRemove", value = "/url-validator-remove")
public class UrlValidatorRemove extends UrlValidatorBase {

    /**
     * Performs the action of removing a feed URL from the data.
     *
     * @param feedUrl The feed URL associated with the website to be removed.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doAction(String feedUrl) throws IOException {
        ch.removeFromData(feedUrl);
        response.sendRedirect("org/ina/p4/urlInputAdvanced.jsp?errorMessage=Url+removed");
    }
}

