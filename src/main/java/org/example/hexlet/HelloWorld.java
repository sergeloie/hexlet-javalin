package org.example.hexlet;

import io.javalin.Javalin;

import io.javalin.validation.ValidationException;
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

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        Javalin app = Javalin.create(config -> config.plugins.enableDevLogging());

        Course course1 = new Course(1L, "Java", "Super Java Course");
        Course course2 = new Course(2L, "PHP", "some php course");
        Course course3 = new Course(3L, "JS", "Shtako JavaScript course");
        List<Course> courses = List.of(course1, course2, course3);
        // Описываем, что загрузится по адресу /
        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/hello", ctx -> {
            String name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result(String.format("Hello, %s", name));
        });

        app.get("/courses/", ctx -> {
            CoursesPage page = new CoursesPage(CourseRepository.getEntities(), null);
            ctx.render("courses/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/users", ctx -> {
            UsersPage page = new UsersPage(UserRepository.getEntities());
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/courses/build", ctx -> {
            BuildCoursePage page = new BuildCoursePage();
            ctx.render("courses/build.jte", Collections.singletonMap("page", page));

        });

        app.get("/courses/{id}", ctx -> {
            Long id = ctx.pathParamAsClass("id", Long.class).get();
            Course course = courses.stream().filter(x -> x.getId().equals(id)).findFirst().get();
            CoursePage page = new CoursePage(course);
            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
        });


        app.get("/users/build", ctx -> {
            BuildUserPage page = new BuildUserPage();
            ctx.render("users/build.jte", Collections.singletonMap("page", page));
        });



        app.get("/users/{id}", ctx -> {
            String id = ctx.pathParam("id");
            String escapedId = StringEscapeUtils.escapeHtml4(id);
            ctx.contentType("text/html");
            ctx.result(escapedId);
        });

        app.post("/users", ctx -> {
            String name = ctx.formParam("name");
            String email = ctx.formParam("email").trim().toLowerCase();

            try {
                String passwordConfirmation = ctx.formParam("passwordConfirmation");
                String password = ctx.formParamAsClass("password", String.class)
                        .check(value -> value.equals(passwordConfirmation), "Passwords not match")
                        .get();
                User user = new User(name, email, password);
                UserRepository.save(user);
                ctx.redirect("/users");
            } catch (ValidationException e) {
                BuildUserPage page = new BuildUserPage(name, email, e.getErrors());
                ctx.render("users/build.jte", Collections.singletonMap("page", page));
            }



        });

        app.post("/courses", ctx -> {
            String name = null;
            String description = null;

            try {
                name = ctx.formParamAsClass("name", String.class)
                        .check(value -> value.length() > 2, "Course name must be more than 2 characters")
                        .get();
                description = ctx.formParamAsClass("description", String.class)
                        .check(value -> value.length() > 10, "Course description must be more than 10 characters")
                        .get();
                Course course = new Course(CourseRepository.getEntities().size() + 1L, name, description);
                CourseRepository.save(course);
                ctx.redirect("/courses");
            } catch (ValidationException e) {
                BuildCoursePage page = new BuildCoursePage(name, description, e.getErrors());
                ctx.render("courses/build.jte", Collections.singletonMap("page", page));
            }
        });



        app.start(7070); // Стартуем веб-сервер
    }
}
