package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.service.ShoppingCartService;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().add(product);
        return shoppingCart;
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        return shoppingCartDao.get(shoppingCart.getId()).get().getProducts().remove(product);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCartDao.getAll().clear();
    }

    @Override
    public ShoppingCart getCartByUserId(Long id) {
        return shoppingCartDao.getAll()
                .stream()
                .filter(cart -> cart.getUserId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public ShoppingCart create(ShoppingCart element) {
        return shoppingCartDao.create(element);
    }

    @Override
    public Optional<ShoppingCart> get(Long userId) {
        return shoppingCartDao.get(userId);
    }

    @Override
    public List<ShoppingCart> getAll() {
        return shoppingCartDao.getAll();
    }

    @Override
    public ShoppingCart update(ShoppingCart element) {
        return shoppingCartDao.update(element);
    }

    @Override
    public boolean delete(Long id) {
        return shoppingCartDao.delete(id);
    }

    @Override
    public List<Product> getAllProducts(Long cartId) {
        return shoppingCartDao.getAllProducts(cartId);
    }
}
