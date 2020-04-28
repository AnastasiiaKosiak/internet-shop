package mate.academy.internetshop.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;

public class AddProductsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private final ProductService productService =
            (ProductService)injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addProducts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String productName = req.getParameter("name");
        String price = req.getParameter("price");
        Product product = new Product(productName, BigDecimal.valueOf(Double.parseDouble(price)));
        productService.create(product);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
