package ru.java.springbootwebjsp.repositiry;

import ru.java.springbootwebjsp.model.User;

import java.util.List;

public interface UserRepository {
    //null if not found, when update
    User save(User user);

    //false if not found
    boolean delete(int id);

    //null if not found
    User get(int id);

    List<User> getAll();

}
