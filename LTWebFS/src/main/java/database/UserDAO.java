package database;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO implements IDAO<User> {
    public static UserDAO getInstance() {
        return new UserDAO();
    }

    public int insert(User user) {
        return 0;
    }

    public int update(User user) {
        return 0;
    }

    public int delete(User user) {
        return 0;
    }

    public ArrayList<User> selectAll() {
        return null;
    }

    public User selectById(int id) {
        return null;
    }

    public ArrayList<User> selectByCondition(User user) {
        ArrayList<User> res = new ArrayList<User>();
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "select * from users;";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("pwd");
                int level = rs.getInt("level");
                String phone = rs.getString("phone");

                res.add(new User());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}