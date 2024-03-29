package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.util.NamedRoutes;

import java.sql.SQLException;
import java.util.Collections;

public class CourseController {

    public static void index(Context context) throws SQLException {
        CoursesPage page = new CoursesPage(CourseRepository.getEntities(), null);
        page.setFlash(context.consumeSessionAttribute("flash"));
        context.render("courses/index.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context context) {
        BuildCoursePage page = new BuildCoursePage();

        context.render("courses/build.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context context) throws SQLException {
        Long id = context.pathParamAsClass("id", Long.class).get();
        Course course = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Course with ID: " + id + " not found"));
        CoursePage page = new CoursePage(course);
        context.render("courses/show.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context context) throws SQLException {

        String name = context.formParamAsClass("name", String.class).get();
        String description = context.formParamAsClass("description", String.class).get();

        try {
            name = context.formParamAsClass("name", String.class)
                    .check(value -> value.length() > 2, "Course name must be more than 2 characters")
                    .get();
            description = context.formParamAsClass("description", String.class)
                    .check(value -> value.length() > 10, "Course description must be more than 10 characters")
                    .get();
            Course course = new Course(CourseRepository.getEntities().size() + 1L, name, description);
            CourseRepository.save(course);
            context.sessionAttribute("flash", "Success. Course has been created!");
            context.redirect(NamedRoutes.COURSES);
        } catch (ValidationException e) {
            BuildCoursePage page = new BuildCoursePage(name, description, e.getErrors());
            page.setFlash("Warning. Course has not been saved!");
            context.render("courses/build.jte", Collections.singletonMap("page", page));
        }
    }
}
