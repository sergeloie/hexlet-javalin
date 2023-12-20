package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.validation.Validator;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;

import java.util.Collections;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        Javalin app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });
        // Описываем, что загрузится по адресу /
        app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/hello", ctx -> {
            String name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result(String.format("Hello, %s", name));
        });

        app.get("/courses/", ctx -> {

            Course course1 = new Course("Java", "Super Java Course");
            Course course2 = new Course("PHP", "some php course");
            List<Course> courses = List.of(course1, course2);
            String header = "Programming courses";
            CoursesPage page = new CoursesPage(courses, header);
           ctx.render("courses/index.jte", Collections.singletonMap("page", page));
        });
//        app.get("/courses/{id}", ctx -> {
//            ctx.result("Course ID: " + ctx.pathParam("id"));
//        });
//        app.get("users/{id}", ctx -> {
//            int userID = ctx.pathParamAsClass("id", Integer.class).getOrDefault(0);
//           ctx.result("User ID: " + userID);
//        });
        app.start(7070); // Стартуем веб-сервер
    }
}