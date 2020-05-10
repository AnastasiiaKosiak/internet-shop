package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ProductDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;

@Service
public class ProductServiceJdbcImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product element) {
        return productDao.create(element);
    }

    @Override
    public Optional<Product> get(Long id) {
        return productDao.get(id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product update(Product element) {
        return productDao.update(element);
    }

    @Override
    public boolean delete(Long id) {
        return productDao.delete(id);
    }
}
