package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.ProductService;

public class DeleteProductController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private final ProductService productService =
            (ProductService)INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String productId = req.getParameter("id");
        try {
            productService.delete(Long.valueOf(productId));
        } catch (NumberFormatException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
        resp.sendRedirect(req.getContextPath() + "/products/admin");
    }
}
