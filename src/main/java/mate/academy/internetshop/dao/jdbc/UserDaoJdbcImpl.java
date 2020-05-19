package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        String selectQuery = "SELECT * FROM users WHERE login = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            Optional<User> user = Optional.empty();
            while (resultSet.next()) {
                user = Optional.of(getUserFromResultSet(resultSet));
            }
            return user;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    @Override
    public User create(User element) {
        String insertQuery = "INSERT INTO users (name, login, password, salt) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insertQuery,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, element.getName());
            statement.setString(2, element.getLogin());
            statement.setString(3, element.getPassword());
            statement.setBytes(4, element.getSalt());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    element.setUserId(generatedKeys.getLong(1));
                }
            }
            addUserRoles(element, connection);
            return element;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        String selectQuery = "SELECT * FROM users WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<User> user = Optional.empty();
            while (resultSet.next()) {
                user = Optional.of(getUserFromResultSet(resultSet));
            }
            return user;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<User> getAll() {
        String selectQuery = "SELECT * FROM users";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
            return users;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    @Override
    public User update(User element) {
        String updateQuery = "UPDATE users SET name = ?, login = ?, password = ?, salt = ? "
                + "WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, element.getName());
            statement.setString(2, element.getLogin());
            statement.setString(3, element.getPassword());
            statement.setBytes(4, element.getSalt());
            statement.setLong(5, element.getUserId());
            statement.executeUpdate();
            removeUserRoles(element, connection);
            addUserRoles(element, connection);
            return element;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    @Override
    public boolean delete(Long id) {
        String deleteUserQuery = "DELETE FROM users WHERE user_id = ?";
        String deleteRolesQuery = "DELETE FROM users_roles WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement deleteRolesStatement = connection.prepareStatement(deleteRolesQuery);
            deleteRolesStatement.setLong(1, id);
            deleteRolesStatement.executeUpdate();
            PreparedStatement deleteUserStatement = connection.prepareStatement(deleteUserQuery);
            deleteUserStatement.setLong(1, id);
            deleteUserStatement.executeUpdate();
            return true;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    private Set<Role> getUserRoles(User user) {
        String selectQuery = "SELECT role_name FROM roles JOIN users_roles "
                + "ON users_roles.role_id = roles.role_id "
                + "WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setLong(1, user.getUserId());
            ResultSet resultSet = statement.executeQuery();
            Set<Role> roles = new HashSet<>();
            while (resultSet.next()) {
                roles.add(Role.of(resultSet.getString("role_name")));
            }
            return roles;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    private void addUserRoles(User user, Connection connection) throws SQLException {
        String query = "INSERT INTO users_roles (user_id, role_id) "
                + "VALUES (?, ?)";
        for (Role role : user.getRoles()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, user.getUserId());
            statement.setLong(2, getRoleId(role.getRoleName()));
            statement.executeUpdate();
        }
    }

    private void removeUserRoles(User user, Connection connection) throws SQLException {
        String deleteQuery = "DELETE FROM users_roles WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(deleteQuery);
        statement.setLong(1, user.getUserId());
        statement.executeUpdate();
    }

    private Long getRoleId(Role.RoleName roleName) {
        String selectQuery = "SELECT role_id FROM roles WHERE role_name = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, roleName.name());
            ResultSet resultSet = statement.executeQuery();
            Long id = null;
            while (resultSet.next()) {
                id = resultSet.getLong("role_id");
            }
            return id;
        } catch (SQLException exception) {
            throw new DataProcessingException(exception.getMessage(), exception);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User(resultSet.getString("name"),
                resultSet.getString("login"),
                resultSet.getString("password"));
        user.setUserId(resultSet.getLong("user_id"));
        user.setSalt(resultSet.getBytes("salt"));
        user.setRoles(getUserRoles(user));
        return user;
    }
}
