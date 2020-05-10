package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ProductDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {

    @Override
    public Product create(Product element) {
        String insertionQuery = "INSERT INTO products (name, price) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insertionQuery);
            statement.setString(1, element.getName());
            statement.setBigDecimal(2, element.getPrice());
            statement.executeUpdate();
            return element;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage());
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        String selectQuery = "SELECT * FROM products WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Product> product = Optional.empty();
            while (resultSet.next()) {
                product = getProductFromResultSet(resultSet);
            }
            return product;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage());
        }
    }

    @Override
    public List<Product> getAll() {
        String selectQuery = "SELECT * FROM products";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(getProductFromResultSet(resultSet).get());
            }
            return products;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage());
        }
    }

    @Override
    public Product update(Product element) {
        String updateQuery = "UPDATE products SET name = ?, price = ? WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, element.getName());
            statement.setBigDecimal(2, element.getPrice());
            statement.setLong(3, element.getId());
            statement.executeUpdate();
            return element;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) {
        String deleteQuery = "DELETE FROM products WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage());
        }
    }

    public Optional<Product> getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product(resultSet.getString("name"),
                resultSet.getBigDecimal("price"));
        product.setId(resultSet.getLong(1));
        return Optional.of(product);
    }
}
