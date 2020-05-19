package mate.academy.internetshop.service.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.service.ShoppingCartService;

@Service
public class ShoppingCartServiceJdbcImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        if (shoppingCart.getId() == null) {
            shoppingCartDao.create(shoppingCart);
        }
        if (shoppingCart.getProducts() == null) {
            shoppingCart.setProducts(new ArrayList<Product>());
        }
        shoppingCart.getProducts().add(product);
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getCartByUserId(Long id) {
        return shoppingCartDao.getAll()
                .stream()
                .filter(cart -> cart.getUserId().equals(id))
                .findFirst().get();
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        return shoppingCartDao.getAllProducts(shoppingCart.getId()).remove(product);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCartDao.getAllProducts(shoppingCart.getId()).clear();
    }

    @Override
    public List<Product> getAllProducts(Long cartId) {
        return shoppingCartDao.getAllProducts(cartId);
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        return shoppingCartDao.get(id);
    }

    @Override
    public List<ShoppingCart> getAll() {
        return shoppingCartDao.getAll();
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean delete(Long id) {
        return shoppingCartDao.delete(id);
    }
}
