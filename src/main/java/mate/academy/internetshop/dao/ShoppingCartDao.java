package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;

public interface ShoppingCartDao {

    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart update(ShoppingCart shoppingCart);

    Optional<ShoppingCart> getByUserId(Long userId);

    List<Product> getAllProducts(ShoppingCart shoppingCart);

}
