package dao;

import model.User;

import java.util.List;

public interface UserDAO {

    User addUser(String username, String password);
    List<User> getAllUsers();
    User deleteUser(User user);
}
