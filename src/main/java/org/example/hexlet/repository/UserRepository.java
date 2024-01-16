package org.example.hexlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.User;

import static org.example.hexlet.repository.BaseRepository.dataSource;

public class UserRepository {

    private static List<User> entities = new ArrayList<>();

    public static void save(User user) throws SQLException {
//        user.setId((long) entities.size() + 1);
//        entities.add(user);

        String sql = "INSERT INTO users (NAME, EMAIL, PASSWORD) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<User> search(String term) {
        return entities.stream()
                .filter(entity -> entity.getName().startsWith(term))
                .toList();
    }

    public static Optional<User> find(Long id) throws SQLException {
//        return entities.stream()
//                .filter(entity -> Objects.equals(entity.getId(), id))
//                .findAny();

        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                User user = new User(id, name, email, password);
                return Optional.of(user);
            }
            return Optional.empty();
        }
    }

    public static void delete(Long id) {

    }

    public static List<User> getEntities() throws SQLException {
        String sql = "SELECT * FROM USERS";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            ArrayList<User> result = new ArrayList<User>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                User user = new User(id, name, email, password);
                result.add(user);
            }
            return result;
        }
    }

}
