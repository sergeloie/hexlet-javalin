package org.example.hexlet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import org.example.hexlet.model.User;

public class UserRepository {
    @Getter
    private static List<User> entities = new ArrayList<User>();

    public static void save(User user) {
        user.setId((long) entities.size() + 1);
        entities.add(user);
    }

    public static List<User> search(String term) {
        List<User> users = entities.stream()
                .filter(entity -> entity.getName().startsWith(term))
                .toList();
        return users;
    }

    public static Optional<User> find(Long id) {
        Optional<User> maybeUser = entities.stream()
                .filter(entity -> entity.getId() == id)
                .findAny();
        return maybeUser;
    }

    public static void delete(Long id) {

    }

}