package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.example.hexlet.dto.SessionPage;
import org.example.hexlet.util.NamedRoutes;

import java.util.Collections;

public class SessionsController {

    public static void build(Context context) {
        context.render("sessions/build.jte");
    }

    public static void create(Context context) {
        String nickname = context.formParam("nickname");
        context.sessionAttribute("currentUser", nickname);
        context.redirect(NamedRoutes.ROOT);
    }

    public static void destroy(Context context) {
        context.sessionAttribute("currentUser", null);
        context.redirect(NamedRoutes.ROOT);
    }

    public static void show(Context context) {
        SessionPage page = new SessionPage(context.sessionAttribute("currentUser"));
        context.render("sessions/show.jte", Collections.singletonMap("page", page));
    }
}
