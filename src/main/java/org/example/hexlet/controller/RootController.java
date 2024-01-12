package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.example.hexlet.dto.MainPage;

import java.util.Collections;

public class RootController {

    public static void index(Context context) {
        var visited = Boolean.valueOf(context.cookie("visited"));
        var page = new MainPage(visited);
        context.render("index.jte", Collections.singletonMap("page", page));
        context.cookie("visited", String.valueOf(true));
    }
}
