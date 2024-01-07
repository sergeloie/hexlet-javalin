package org.example.hexlet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import lombok.Getter;
import org.example.hexlet.model.Course;

public class CourseRepository {
    @Getter
    private static List<Course> entities = new ArrayList<>();

    public static void save(Course course) {
        course.setId((long) entities.size() + 1);
        entities.add(course);
    }

    public static List<Course> search(String term) {
        return entities.stream()
                .filter(entity -> entity.getName().contains(term))
                .toList();
    }

    public static Optional<Course> find(Long id) {
        return entities.stream()
                .filter(entity -> Objects.equals(entity.getId(), id))
                .findAny();
    }

}
