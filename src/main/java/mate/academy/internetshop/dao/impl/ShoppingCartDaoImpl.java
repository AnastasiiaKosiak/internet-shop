package mate.academy.internetshop.dao.impl;

import java.util.Optional;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.ShoppingCart;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return null;
    }

    @Override
    public Optional<ShoppingCart> get(Long cartId) {
        return Storage.carts
                .stream()
                .filter(i -> i.getId().equals(cartId))
                .findFirst();
    }
}

