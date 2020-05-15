package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        String insertQuery = "INSERT INTO orders (user_id) VALUES (?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insertQuery,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
            addProductsToOrder(order);
            return order;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        String selectQuery = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Order> order = Optional.empty();
            while (resultSet.next()) {
                order = getOrderFromResultSet(resultSet);
            }
            return order;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<Order> getAll() {
        String selectQuery = "SELECT * FROM orders";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet).get());
            }
            return orders;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    @Override
    public Order update(Order order) {
        deleteProductsFromOrder(order.getId());
        addProductsToOrder(order);
        return order;
    }

    @Override
    public boolean delete(Long id) {
        String deleteQuery = "DELETE FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            deleteProductsFromOrder(id);
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        String selectQuery = "SELECT * FROM orders WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet).get());
            }
            return orders;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }

    }

    private List<Product> getProductsFromOrder(Long id) throws SQLException {
        String selectQuery = "SELECT * "
                + "FROM products JOIN orders_products "
                + "ON orders_products.product_id = products.id "
                + "WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getBigDecimal("price"));
                product.setId(resultSet.getLong("id"));
                products.add(product);
            }
            return products;
        }
    }

    private void addProductsToOrder(Order order) {
        String insertQuery = "INSERT INTO orders_products (order_id, product_id)"
                + " VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Product product : order.getProducts()) {
                PreparedStatement statement = connection.prepareStatement(insertQuery);
                statement.setLong(1, order.getId());
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    private void deleteProductsFromOrder(Long id) {
        String deleteQuery = "DELETE FROM orders_products WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    private Optional<Order> getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order(getProductsFromOrder(resultSet.getLong("order_id")),
                resultSet.getLong("user_id"));
        order.setId(resultSet.getLong("order_id"));
        return Optional.of(order);
    }
}
