package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;
import mate.academy.internetshop.service.UserService;

public class AddToCartController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private static final Injector injector2 = Injector.getInstance("mate.academy.internetshop");
    private static final Injector injector3 = Injector.getInstance("mate.academy.internetshop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService)injector.getInstance(ShoppingCartService.class);
    private ProductService productService =
            (ProductService) injector2.getInstance(ProductService.class);
    private UserService userService = (UserService)injector3.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ShoppingCart shoppingCart =
                new ShoppingCart(productService.getAll(),userService.get(Long.valueOf(1)).get());
        String productId = req.getParameter("id");
        shoppingCartService.addProduct(shoppingCart,
                productService.get(Long.valueOf(productId)).get());
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
