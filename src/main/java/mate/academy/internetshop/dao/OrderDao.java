package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.model.Order;

public interface OrderDao {
    Order create(Order order);

    Order update(Order order);

    Optional<Order> getById(Long id);

    boolean delete(Long id);

    boolean delete(Order order);
}