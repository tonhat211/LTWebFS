package database;

import model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements IDAO<User> {
    public static UserDAO getInstance() {
        return new UserDAO();
    }

    @Override
    public int insert(User user) {
        int res = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            conn = JDBCUtil.getConnection();

            String sql = "INSERT INTO users ( `name`, email, pwd, `level`, phone, address, info ) VALUES (?, ?, ?, 0, ? , ?, ?);";
            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPwd());
            pst.setString(4, user.getPhone());
            pst.setString(5, user.getAddress());
            pst.setString(6, user.getInfo());
            res = pst.executeUpdate();
            JDBCUtil.closeConnection(conn);
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int trueTheUser(String email){
        int re = 0;
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "update users set available =1 where email = ?;";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, email);


            re = pst.executeUpdate();

            System.out.println(re + " dong da duoc cap nhat");
            JDBCUtil.closeConnection(conn);
            return re;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isExist(String email){
        return false;
    }

    @Override
    public int update(User user) {
//        int re = 0;
//        try {
//            Connection conn = JDBCUtil.getConnection();
//
//            String sql = "update users " +
//                    "set name=?" +
//                    "where id=?;";
//            PreparedStatement pst = conn.prepareStatement(sql);
//
//            pst.setString(1, user.getName());
//            pst.setInt(2, user.getAvailable());
//            pst.setInt(3, user.getId());
//
//            re = pst.executeUpdate();
//
//            System.out.println(re + " dong da duoc cap nhat");
//            JDBCUtil.closeConnection(conn);
//            return re;
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return 0;

    }

    @Override
    public int delete(User user) {
        return 0;
    }

    @Override
    public ArrayList selectAll() {
        return null;
    }

    public User selectById(int id) {
        User user = new User();
        return null;
    }

    @Override
    public ArrayList<User> selectByCondition(User user) {
        return null;
    }

    public User getUserByEmail(String email) {
        Connection conn = null;
        ResultSet rs = null;
        Statement s = null;
        try {
            conn = JDBCUtil.getConnection();

            String sql = "SELECT `name`,email,pwd,`level` FROM users WHERE email = ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();

            while (rs.next()) {
                return new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JDBCUtil.closeConnection(conn);
        return null;
    }
}