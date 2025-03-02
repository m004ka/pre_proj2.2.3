package org.example.springbootproj2.service;

import org.example.springbootproj2.model.User;

public interface UserService {
    User getUserById(long id);

    void addUser(User user);
}
