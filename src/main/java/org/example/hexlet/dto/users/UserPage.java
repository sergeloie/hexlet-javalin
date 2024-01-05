package org.example.hexlet.dto.users;

import org.example.hexlet.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserPage {
    private User user;
}
