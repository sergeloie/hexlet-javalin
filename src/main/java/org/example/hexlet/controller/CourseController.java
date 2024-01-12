package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;

import java.util.Collections;

public class CourseController {

    public static void index(Context context) {
        CoursesPage page = new CoursesPage(CourseRepository.getEntities(), null);
        context.render("courses/index.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context context) {
        BuildCoursePage page = new BuildCoursePage();
        context.render("courses/build.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context context) {
        Long id = context.pathParamAsClass("id", Long.class).get();
        Course course = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Course with ID: " + id + " not found"));
        CoursePage page = new CoursePage(course);
        context.render("courses/show.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context context) {
        String name = null;
        String description = null;

        try {
            name = context.formParamAsClass("name", String.class)
                    .check(value -> value.length() > 2, "Course name must be more than 2 characters")
                    .get();
            description = context.formParamAsClass("description", String.class)
                    .check(value -> value.length() > 10, "Course description must be more than 10 characters")
                    .get();
            Course course = new Course(CourseRepository.getEntities().size() + 1L, name, description);
            CourseRepository.save(course);
            context.redirect("/courses");
        } catch (ValidationException e) {
            BuildCoursePage page = new BuildCoursePage(name, description, e.getErrors());
            context.render("courses/build.jte", Collections.singletonMap("page", page));
        }
    }
}
