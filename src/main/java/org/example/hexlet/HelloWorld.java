package org.example.hexlet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.javalin.Javalin;

import org.example.hexlet.controller.CourseController;
import org.example.hexlet.controller.RootController;
import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.controller.UserController;
import org.example.hexlet.repository.BaseRepository;
import org.example.hexlet.util.NamedRoutes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class HelloWorld {
    public static void main(String[] args) throws SQLException, IOException {

//        Javalin app = Javalin.create(config -> config.plugins.enableDevLogging());

        Javalin app = getApp();

        app.get(NamedRoutes.ROOT, RootController::index);

        app.get(NamedRoutes.COURSES, CourseController::index);
        app.get(NamedRoutes.COURSES_BUILD, CourseController::build);
        app.get(NamedRoutes.courseShow("{id}"), CourseController::show);
        app.post(NamedRoutes.COURSES, CourseController::create);

        app.get(NamedRoutes.USERS, UserController::index);
        app.get(NamedRoutes.USERS_BUILD, UserController::build);
        app.get(NamedRoutes.userShow("{id}"), UserController::show);
        app.post(NamedRoutes.USERS, UserController::create);

        app.get(NamedRoutes.SESSIONS_BUILD, SessionsController::build);
        app.get(NamedRoutes.SESSIONS, SessionsController::show);
        app.post(NamedRoutes.SESSIONS, SessionsController::create);
        app.delete(NamedRoutes.SESSIONS, SessionsController::destroy);

        app.start(7070); // Стартуем веб-сервер
    }

    public static Javalin getApp() throws IOException, SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:project;DB_CLOSE_DELAY=-1");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        URL url = HelloWorld.class.getClassLoader().getResource("schema.sql");
        File file = new File(url.getFile());
        String sql = Files.lines(file.toPath())
                .collect(Collectors.joining("\n"));

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }

        BaseRepository.dataSource = dataSource;

        Javalin app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        return app;
    }
}
