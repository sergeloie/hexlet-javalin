package org.example.hexlet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import lombok.Getter;
import org.example.hexlet.model.User;

public class UserRepository {
    @Getter
    private static List<User> entities = new ArrayList<>();

    public static void save(User user) {
        user.setId((long) entities.size() + 1);
        entities.add(user);
    }

    public static List<User> search(String term) {
        return entities.stream()
                .filter(entity -> entity.getName().startsWith(term))
                .toList();
    }

    public static Optional<User> find(Long id) {
        return entities.stream()
                .filter(entity -> Objects.equals(entity.getId(), id))
                .findAny();
    }

    public static void delete(Long id) {

    }

}