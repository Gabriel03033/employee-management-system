package com.project.it.user;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long userId);

    User saveUser(User user);

    User updateUserById(User user, Long userId);

    void deleteUserById(Long userId);
}
