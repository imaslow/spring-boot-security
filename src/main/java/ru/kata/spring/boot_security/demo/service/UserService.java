package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User findUserByName(String name);
    Set<Role> getSetOfRoles(List<String> id);
    List<User> getUsers();
    void createUser(User user);
    void updateUser(User updateUser);
    User readUser(long id);
    User deleteUser(long id);
}
