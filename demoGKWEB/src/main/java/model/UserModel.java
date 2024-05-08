package model;

import dao.UserDAO;
import services.JDBIConnect;

import java.util.List;

public class UserModel implements UserDAO {

    private JDBIConnect connect;
    private User user;

    @Override
    public User addUser(String username, String password) {
        user = new User(username, password);
        JDBIConnect.getJdbi().useHandle(handle -> {
            handle.execute("INSERT INTO users (username, password) VALUES (?, ?)",
                    username, password);
        });
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = JDBIConnect.getJdbi().withHandle(handle ->
                handle.createQuery("SELECT * FROM users")
                        .mapToBean(User.class)
                        .list());
        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }

    @Override
    public User deleteUser(User user) {
        String username = user.getUsername();
        JDBIConnect.getJdbi().useHandle(handle -> {
            handle.execute("DELETE FROM users WHERE username = ?", username);
        });
        return user;
    }
}
