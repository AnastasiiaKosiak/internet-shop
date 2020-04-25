package mate.academy.internetshop;

import java.math.BigDecimal;
import java.util.ArrayList;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;
import mate.academy.internetshop.service.UserService;

public class Application {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {

        final ProductService itemService =
                (ProductService)injector.getInstance(ProductService.class);

        final OrderService orderService =
                (OrderService)injector.getInstance(OrderService.class);

        final UserService userService = (UserService)injector.getInstance(UserService.class);

        final ShoppingCartService shoppingCartService =
                (ShoppingCartService)injector.getInstance(ShoppingCartService.class);

        Product product1 = new Product("Little Prince", new BigDecimal(12.00));
        Product product2 = new Product("The Book thief", new BigDecimal(21.00));
        Product product3 = new Product("Steve Jobs. A Biography", new BigDecimal(15.34));

        itemService.create(product1);
        itemService.create(product2);
        itemService.create(product3);
        itemService.getAll().stream().forEach(System.out::println);

        User user1 = new User("nastya", "abc", "123");
        User user2 = new User("ivan", "xyz", "456");

        userService.create(user1);
        userService.create(user2);
        userService.getAll().forEach(System.out::println);

        ShoppingCart shoppingCart = new ShoppingCart(new ArrayList<>(), user1);
        shoppingCartService.addProduct(shoppingCart, product1);
        shoppingCartService.addProduct(shoppingCart, product2);

        Order order1 = new Order(shoppingCart.getProducts(), user1);
        orderService.completeOrder(order1.getProducts(), user1);
        orderService.getUserOrders(user1).forEach(System.out::println);
    }
}
