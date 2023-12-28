package database;

import model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
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

    public ArrayList<User> selectAll() throws SQLException {
        ArrayList<User> res = new ArrayList<User>();
        Connection conn = null;
        ResultSet rs = null;
        Statement s = null;
        try {
            conn = JDBCUtil.getConnection();

            String sql = "select * from users;";
            s = conn.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPwd(rs.getString("pwd"));
                user.setLevel(rs.getInt("level"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setBranchID(rs.getInt("branchID"));
                user.setInfo(rs.getString("info"));
                res.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return res;
    }

    public User selectById(int id) {
        User user = new User();
        return null;
    }

    public ArrayList<User> findAdmin() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        Connection conn = null;
        ResultSet rs = null;
        Statement s = null;
        try {
            conn = JDBCUtil.getConnection();

            String sql = "SELECT email,pwd,`level` FROM users WHERE `level` = 2;";
            s = conn.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                User user = new User(rs.getString(1), rs.getString(2), rs.getInt(3));
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserByEmail(String email) {
        Connection conn = null;
        ResultSet rs = null;
        Statement s = null;
        try {
            conn = JDBCUtil.getConnection();

            String sql = "SELECT email,pwd,`level` FROM users WHERE email = ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();

            while (rs.next()) {
               return new User(rs.getString("email"), rs.getString(2), rs.getInt(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}