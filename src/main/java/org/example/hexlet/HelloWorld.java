package org.example.hexlet;

import io.javalin.Javalin;

import io.javalin.validation.ValidationException;
import org.example.hexlet.controller.CourseController;
import org.example.hexlet.controller.RootController;
import org.example.hexlet.controller.UserController;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;

import org.apache.commons.text.StringEscapeUtils;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;


import java.util.Collections;
import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.path;

public class HelloWorld {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> config.plugins.enableDevLogging());

        app.get("/", RootController::index);

        app.get("/courses", CourseController::index);
        app.get("/courses/build",CourseController::build);
        app.get("/courses/{id}", CourseController::show);
        app.post("/courses", CourseController::create);

        app.get("/users", UserController::index);
        app.get("/users/build", UserController::build);
        app.get("/users/{id}", UserController::show);
        app.post("/users", UserController::create);

        app.start(7070); // Стартуем веб-сервер
    }
}
