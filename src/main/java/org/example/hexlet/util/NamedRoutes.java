package org.example.hexlet.util;

import lombok.Getter;

@Getter
public class NamedRoutes {

    public static final String ROOT = "/";
    public static final String COURSES = "/courses";
    public static final String COURSES_BUILD = "/courses/build";
    public static final String USERS = "/users";
    public static final String USERS_BUILD = "/users/build";
    public static final String SESSIONS = "/sessions";
    public static final String SESSIONS_BUILD = "/sessions/build";


    // Методы для путей с параметром id в виде строки
    public static String courseShow(String id) {
        return COURSES + "/" + id;
    }

    public static String userShow(String id) {
        return USERS + "/" + id;
    }

    // Методы для путей с параметром id
    public static String courseShow(Long id) {
        return courseShow(String.valueOf(id));
    }

    public static String userShow(Long id) {
        return userShow(String.valueOf(id));
    }
}
