package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {
    @Override
    public List<Product> getAllProducts(Long cartId) {
        String selectQuery = "SELECT * "
                + "FROM products p JOIN shopping_carts_products shp"
                + "ON p.id = shp.product_id "
                + "WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setLong(1, cartId);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getBigDecimal("price"));
                product.setId(resultSet.getLong("id"));
                products.add(product);
            }
            return products;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        String insertQuery = "INSERT INTO shopping_carts (user_id) VALUES (?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, shoppingCart.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                shoppingCart.setId(resultSet.getLong(1));
            }
            addProductsToCart(shoppingCart);
            return shoppingCart;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        String selectQuery = "SELECT * FROM shopping_carts WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getShoppingCartFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<ShoppingCart> getAll() {
        String selectQuery = "SELECT * FROM shopping_carts";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            List<ShoppingCart> shoppingCarts = new ArrayList<>();
            while (resultSet.next()) {
                shoppingCarts.add(getShoppingCartFromResultSet(resultSet));
            }
            return shoppingCarts;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    @Override
    public ShoppingCart update(ShoppingCart element) {
        deleteProductsFromCart(element);
        addProductsToCart(element);
        return element;
    }

    @Override
    public boolean delete(Long id) {
        String deleteQuery = "DELETE FROM shopping_carts WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    private void deleteProductsFromCart(ShoppingCart shoppingCart) {
        String deleteQuery = "DELETE FROM shopping_carts_products WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, shoppingCart.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    private void addProductsToCart(ShoppingCart shoppingCart) {
        String insertQuery = "INSERT INTO shopping_carts_products (cart_id, product_id) "
                + "VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            for (Product product : shoppingCart.getProducts()) {
                statement.setLong(1, shoppingCart.getId());
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    private ShoppingCart getShoppingCartFromResultSet(ResultSet resultSet)
            throws SQLException {
        Long shoppingCartId = resultSet.getLong("cart_id");
        Long userId = resultSet.getLong("user_id");
        ShoppingCart shoppingCart = new ShoppingCart(userId);
        shoppingCart.setId(shoppingCartId);
        shoppingCart.setProducts(getAllProducts(shoppingCartId));
        return shoppingCart;
    }
}
