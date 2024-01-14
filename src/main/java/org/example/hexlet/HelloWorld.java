package org.example.hexlet;

import io.javalin.Javalin;

import org.example.hexlet.controller.CourseController;
import org.example.hexlet.controller.RootController;
import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.controller.UserController;
import org.example.hexlet.util.NamedRoutes;

public class HelloWorld {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> config.plugins.enableDevLogging());

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
}
