package mate.academy.internetshop.service.jdbc;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ShoppingCartService;
import mate.academy.internetshop.service.UserService;

@Service
public class UserServiceJdbcImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Optional<User> findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public User create(User element) {
        return userDao.create(element);
    }

    @Override
    public Optional<User> get(Long id) {
        return userDao.get(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User update(User element) {
        return userDao.update(element);
    }

    @Override
    public boolean delete(Long id) {
        ShoppingCart cart = shoppingCartService.get(id).get();
        shoppingCartDao.delete(cart.getId());
        return userDao.delete(id);
    }
}
