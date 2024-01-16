package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;
import org.example.hexlet.util.NamedRoutes;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Objects;

public class UserController {

    public static void index(Context context) throws SQLException {
        UsersPage page = new UsersPage(UserRepository.getEntities());
        page.setFlash(context.consumeSessionAttribute("flash"));
        context.render("users/index.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context context) {
        BuildUserPage page = new BuildUserPage();
        context.render("users/build.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context context) throws SQLException {
        Long id = context.pathParamAsClass("id", Long.class).get();

        User user = UserRepository.find(id).
                orElseThrow(() -> new NotFoundResponse("User with ID: " + id + " not found"));
        UserPage page = new UserPage(user);
        context.render("users/show.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context context) throws SQLException {
        String name = context.formParam("name");
        String email = Objects.requireNonNull(context.formParam("email")).trim().toLowerCase();

        try {
            String passwordConfirmation = context.formParam("passwordConfirmation");
            String password = context.formParamAsClass("password", String.class)
                    .check(value -> value.equals(passwordConfirmation), "Passwords not match")
                    .get();
            User user = new User(name, email, password);
            UserRepository.save(user);
            context.sessionAttribute("flash", "Success. User has been created!");
            context.redirect(NamedRoutes.USERS);
        } catch (ValidationException e) {
            BuildUserPage page = new BuildUserPage(name, email, e.getErrors());
            page.setFlash("Warning. User has not been saved!");
            context.render("users/build.jte", Collections.singletonMap("page", page));
        }
    }
}

