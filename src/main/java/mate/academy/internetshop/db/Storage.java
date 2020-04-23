package mate.academy.internetshop.db;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;

public class Storage {

    public static final List<Product> products = new ArrayList<>();
    public static final List<ShoppingCart> carts = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    private static Long userId = 0L;
    private static Long orderId = 0L;
    private static Long shoppingCartId = 0L;
    private static Long productId = 0L;

    public static void addItem(Product item) {
        productId++;
        item.setId(productId);
        products.add(item);
    }

    public static void addOrder(Order order) {
        orderId++;
        order.setId(orderId);
        orders.add(order);
    }

    public static void addUser(User user) {
        userId++;
        user.setId(userId);
        users.add(user);
    }

    public static void addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartId++;
        shoppingCart.setId(shoppingCartId);
        carts.add(shoppingCart);
    }
}
