package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.example.hexlet.dto.MainPage;

import java.util.Collections;

public class RootController {

    public static void index(Context context) {
        Boolean visited = Boolean.valueOf(context.cookie("visited"));
        MainPage page = new MainPage(visited);
        context.render("index.jte", Collections.singletonMap("page", page));
        context.cookie("visited", String.valueOf(true));
    }
}
