package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.ShoppingCartService;
import mate.academy.internetshop.service.UserService;

public class CompleteOrderController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private final OrderService orderService =
            (OrderService)INJECTOR.getInstance(OrderService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService)INJECTOR.getInstance(ShoppingCartService.class);
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = (Long)req.getSession().getAttribute(USER_ID);
        ShoppingCart shoppingCart = shoppingCartService.getCartByUserId(userId);
        User user = userService.get(userId).get();
        Order order = orderService.completeOrder(shoppingCart.getProducts(), user);
        if (order != null) {
            shoppingCartService.clear(shoppingCart);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            resp.sendRedirect("/WEB-INF/cart/all");
        }
    }
}
