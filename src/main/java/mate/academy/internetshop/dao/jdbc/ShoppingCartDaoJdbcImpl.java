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
                + "FROM products JOIN shopping_carts_products "
                + "ON products.id = shopping_carts_products.product_id "
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
    public ShoppingCart create(ShoppingCart element) {
        String insertQuery = "INSERT INTO shopping_carts (user_id) VALUES (?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, element.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                element.setId(resultSet.getLong(1));
            }
            return element;
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
            Optional<ShoppingCart> shoppingCart = Optional.empty();
            while (resultSet.next()) {
                shoppingCart = getShoppingCartFromResultSet(resultSet);
            }
            return shoppingCart;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get shopping cart with id = " + id, e);
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
                shoppingCarts.add(getShoppingCartFromResultSet(resultSet).get());
            }
            return shoppingCarts;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get a shopping cart list", e);
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
        String deleteCartQuery = "DELETE FROM shopping_carts WHERE cart_id = ?";
        String deleteProductQuery = "DELETE FROM shopping_carts_products WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement deleteProductsStatement =
                    connection.prepareStatement(deleteProductQuery);
            deleteProductsStatement.setLong(1, id);
            deleteProductsStatement.executeUpdate();
            PreparedStatement deleteCartStatement = connection.prepareStatement(deleteCartQuery);
            deleteCartStatement.setLong(1, id);
            deleteCartStatement.executeUpdate();
            return true;
        } catch (SQLException exception) {
            throw new DataProcessingException("Can't delete shopping cart with id = " + id,
                    exception);
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

    private Optional<ShoppingCart> getShoppingCartFromResultSet(ResultSet resultSet)
            throws SQLException {
        ShoppingCart shoppingCart = new ShoppingCart(resultSet.getLong("user_id"));
        shoppingCart.setProducts(getAllProducts(resultSet.getLong("id")));
        shoppingCart.setId(resultSet.getLong("cart_id"));
        return Optional.of(shoppingCart);
    }
}
