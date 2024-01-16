package database;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    public static User checkLogin(String email, String password) {
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "select email, pwd from users where email = ? and pwd = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return new User(rs.getString("email"), rs.getString("pwd"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}