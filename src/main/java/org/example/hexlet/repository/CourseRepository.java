package org.example.hexlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.Course;

public class CourseRepository extends BaseRepository {

    private static List<Course> entities = new ArrayList<>();

    public static void save(Course course) throws SQLException {
//        course.setId((long) entities.size() + 1);
//        entities.add(course);
        String sql = "INSERT INTO courses (NAME, DESCRIPTION) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                course.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<Course> search(String term) {
        return entities.stream()
                .filter(entity -> entity.getName().contains(term))
                .toList();
    }

    public static Optional<Course> find(Long id) throws SQLException {
//        return entities.stream()
//                .filter(entity -> Objects.equals(entity.getId(), id))
//                .findAny();

        String sql = "SELECT * FROM courses WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Course course = new Course(id, name, description);
                return Optional.of(course);
            }
            return Optional.empty();
        }
    }

    public static List<Course> getEntities() throws SQLException {
        String sql = "SELECT * FROM courses";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Course> result = new ArrayList<Course>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Course course = new Course(id, name, description);
                result.add(course);
            }
            return result;
        }
    }

}
