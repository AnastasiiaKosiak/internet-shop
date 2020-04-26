package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User create(User user) {
        Storage.addUser(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User update(User user) {
        int index = Storage.users.indexOf(Storage.users.stream()
                .filter(person -> person.getId().equals(user.getId())));
        Storage.users.set(index, user);
        return user;
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.users.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public boolean delete(User user) {
        return Storage.users.remove(user);
    }
}
