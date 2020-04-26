package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCartDao.create(shoppingCart);
        shoppingCart.getProducts().add(product);
        return shoppingCart;
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        return shoppingCartDao.get(shoppingCart.getUser().getId())
                .get()
                .getProducts()
                .remove(product);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCartDao.getAllProducts(shoppingCart).clear();
    }

    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        return shoppingCartDao.get(userId);
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return shoppingCartDao.getAllProducts(shoppingCart);
    }
}
