package mate.academy.internetshop.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {
    private final ShoppingCartDaoImpl shoppingCartDao = new ShoppingCartDaoImpl();

    @Override
    public User create(User user) {
        Storage.addUser(user);
        shoppingCartDao.create(new ShoppingCart(new ArrayList<Product>(), user.getUserId()));
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users
                .stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User update(User user) {
        int index = Storage.users.indexOf(Storage.users.stream()
                .filter(person -> person.getUserId().equals(user.getUserId())));
        Storage.users.set(index, user);
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.users.removeIf(user -> user.getUserId().equals(id));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Storage.users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }
}
