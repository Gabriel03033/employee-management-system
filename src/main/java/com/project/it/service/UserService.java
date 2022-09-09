package com.project.it.service;

import com.project.it.entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long userId);

    User saveUser(User user);

    User updateUserById(User user, Long userId);

    void deleteUserById(Long userId);
}
