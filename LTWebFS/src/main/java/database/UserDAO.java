package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements IDAO<User> {
    public static UserDAO getInstance() {
        return new UserDAO();
    }

    public int insert(User u){
        int re=0;
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "insert into users values (?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, u.getId());
            pst.setString(2, u.getName());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getPwd());
            pst.setInt(5, u.getLevel());
            pst.setString(6, u.getPhone());
            pst.setString(7, u.getAddress());
            pst.setInt(8, u.getBranchID());
            pst.setString(9, u.getInfo());
            pst.setString(10, u.getDateIn().getDateInMonthDayYearSql());
            pst.setString(11, null);
            pst.setInt(12, u.getAvailable());

            re = pst.executeUpdate();

            System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return u.getId();

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
    public ArrayList<User> selectAll(){
        ArrayList<User> uList = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select * from users order by id asc;";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String pwd = rs.getString("pwd");
                int level =  rs.getInt("level");
                String phone = rs.getString("phone");
                String address  = rs.getString("address");
                int banchID = rs.getInt("branchID");
                String  info = rs.getString("info");
                Date dateInSql = rs.getDate("dateIn");
                Datee dateIn = new Datee(dateInSql);
                Datee dateOut;
                Date dateOutSql = rs.getDate("dateOut");
                if(dateOutSql!=null){
                    dateOut = new Datee(dateOutSql);
                } else
                    dateOut = null;
                int available= rs.getInt("available");

                User u = new User(id,name, email,pwd,level,phone,address,banchID,info,dateIn,dateOut,available);

                uList.add(u);

            }
            JDBCUtil.closeConnection(conn);
            return uList;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }

    }



    public ArrayList<User> selectAllCustomer(){
        ArrayList<User> uList = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select * from users  where level = 0;";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String pwd = rs.getString("pwd");
                int level =  rs.getInt("level");
                String phone = rs.getString("phone");
                String address  = rs.getString("address");
                int banchID = rs.getInt("branchID");
                String  info = rs.getString("info");
                Date dateInSql = rs.getDate("dateIn");
                Datee dateIn = new Datee(dateInSql);
                Date dateOutSql = rs.getDate("dateOut");
                Datee dateOut = new Datee(dateOutSql);
                int available= rs.getInt("available");

                User u = new User(id,name, email,pwd,level,phone,address,banchID,info,dateIn,dateOut,available);

                uList.add(u);

            }
            JDBCUtil.closeConnection(conn);
            return uList;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }

    }

    public User selectById(int idin){
        User res = null;
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "select * from users where id=?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idin);


            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String pwd = rs.getString("pwd");
                int level =  rs.getInt("level");
                String phone = rs.getString("phone");
                String address  = rs.getString("address");
                int banchID = rs.getInt("branchID");
                String  info = rs.getString("info");
                Date dateInSql = rs.getDate("dateIn");
                Datee dateIn = new Datee(dateInSql);
                Datee dateOut;
                Date dateOutSql = rs.getDate("dateOut");
                if(dateOutSql!=null){
                    dateOut = new Datee(dateOutSql);
                } else
                    dateOut = null;
                int available= rs.getInt("available");

                User u = new User(id,name, email,pwd,level,phone,address,banchID,info,dateIn,dateOut,available);
                res = u;
            }

//			System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return res;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public int updateAvailableByUserID(int idin, int avaiin) {
        int re=0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "update users set available = ? where id =?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, avaiin);
            pst.setInt(2, idin);
            re = pst.executeUpdate();
            return re;

        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }


    public int updateCustomer(User u) {
        int re=0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "update users set name = ?, email = ?, phone = ?, info = ?, dateIn = ? where id =?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,u.getName());
            pst.setString(2,u.getEmail());
            pst.setString(3,u.getPhone());
            pst.setString(4,u.getInfo());
            pst.setString(5,u.getDateIn().getDateInMonthDayYearSql());
            pst.setInt(6, u.getId());
            re = pst.executeUpdate();
            return re;

        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    public int insertCustomer(User u) {
        int re=0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "insert into users name = ?, email = ?, phone = ?, info = ?, dateIn = ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,u.getName());
            pst.setString(2,u.getEmail());
            pst.setString(3,u.getPhone());
            pst.setString(4,u.getInfo());
            pst.setString(5,u.getDateIn().getDateInMonthDayYearSql());
            pst.setInt(6, u.getId());
            re = pst.executeUpdate();
            return re;

        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    public int selectTheMaxID(){
        int res=-1;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select max(id) from users;";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                res = rs.getInt("max(id)");
            }

            JDBCUtil.closeConnection(conn);
            return res;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }





    @Override
    public ArrayList<User> selectByCondition(User user) {
        return null;
    }

    public ArrayList<User> selectByNameOrEmailOrPhone(String input) {
        ArrayList<User> re = new ArrayList<>();
        ArrayList<Integer> idAdded  = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM users where name like ? or  phone like ? or email like  ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,"%"  + input + "%");
            pst.setString(2,"%"  + input + "%");
            pst.setString(3,"%"  + input + "%");

            ResultSet rs = pst.executeQuery();


            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String pwd = rs.getString("pwd");
                int level =  rs.getInt("level");
                String phone = rs.getString("phone");
                String address  = rs.getString("address");
                int banchID = rs.getInt("branchID");
                String  info = rs.getString("info");
                Date dateInSql = rs.getDate("dateIn");
                Datee dateIn = new Datee(dateInSql);
                Datee dateOut;
                Date dateOutSql = rs.getDate("dateOut");
                if(dateOutSql!=null){
                    dateOut = new Datee(dateOutSql);
                } else
                    dateOut = null;
                int available= rs.getInt("available");

                User u = new User(id,name, email,pwd,level,phone,address,banchID,info,dateIn,dateOut,available);

                re.add(u);


            }
            JDBCUtil.closeConnection(conn);
            return re;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
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

    public static void main(String[] args) {
//        User u = new User(3039,"m","m","1234",0,"234567","address",0,"nu=2000-04-04",Datee.getToday(),null,0);
//        System.out.println(UserDAO.getInstance().insert(u));
        System.out.println(UserDAO.getInstance().selectTheMaxID());

    }
}