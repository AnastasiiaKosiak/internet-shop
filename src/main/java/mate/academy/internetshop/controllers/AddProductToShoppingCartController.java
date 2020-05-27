package mate.academy.internetshop.controllers;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;

public class AddProductToShoppingCartController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService)INJECTOR.getInstance(ShoppingCartService.class);
    private final ProductService productService =
            (ProductService)INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long productId;
        try {
            productId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
        Long userId = (Long)req.getSession().getAttribute(USER_ID);
        Optional<Product> product = productService.get(productId);
        if (product.isPresent()) {
            shoppingCartService.addProduct(shoppingCartService.getByUserId(userId), product.get());
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
