package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.model.User;

public interface UserDao {

    User create(User user);

    User update(User user);

    boolean delete(Long id);

    Optional<User> get(Long id);
}
