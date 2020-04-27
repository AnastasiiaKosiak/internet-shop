package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, K> {
    T create(T element);

    Optional<T> get(K id);

    List<T> getAll();

    T update(T element);

    boolean delete(T element);
}
