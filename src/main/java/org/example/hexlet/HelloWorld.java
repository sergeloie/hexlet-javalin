package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;

import org.apache.commons.text.StringEscapeUtils;
import org.owasp.html.HtmlPolicyBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        Javalin app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });
        Course course1 = new Course(1L,"Java", "Super Java Course");
        Course course2 = new Course(2L,"PHP", "some php course");
        Course course3 = new Course(3L,"JS", "Shtako JavaScript course");
        List<Course> courses = List.of(course1, course2, course3);
        // Описываем, что загрузится по адресу /
        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/hello", ctx -> {
            String name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result(String.format("Hello, %s", name));
        });

        app.get("/courses/", ctx -> {
           String header = "Programming courses";
           var term = ctx.queryParam("term");
            ArrayList<Course> displayedCourses;
           if (term != null) {
               displayedCourses = courses.stream()
                       .filter(x -> StringUtils.containsIgnoreCase(x.getDescription(), term))
                       .collect(Collectors.toCollection(ArrayList::new));
           } else {
               displayedCourses = new ArrayList<>(courses);
           }
           CoursesPage page = new CoursesPage(displayedCourses, term);
           ctx.render("courses/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/courses/{id}", ctx -> {
            Long id = ctx.pathParamAsClass("id", Long.class).get();
            Course course = courses.stream().filter(x -> x.getId().equals(id)).findFirst().get();
            CoursePage page = new CoursePage(course);
            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
//            ctx.result("Course ID: " + ctx.pathParam("id"));
        });
//        app.get("users/{id}", ctx -> {
//            int userID = ctx.pathParamAsClass("id", Integer.class).getOrDefault(0);
//           ctx.result("User ID: " + userID);
//        });

        app.get("/users/{id}", ctx -> {
           String id = ctx.pathParam("id");
           String escapedId = StringEscapeUtils.escapeHtml4(id);
           ctx.contentType("text/html");
           ctx.result(escapedId);
        });
        app.start(7070); // Стартуем веб-сервер
    }
}