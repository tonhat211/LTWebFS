package database;

import model.Address;
import model.Datee;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddressDAO implements IDAO<Address> {

    public static AddressDAO getInstance() {
        return new AddressDAO();
    }
    @Override
    public int insert(Address a) {
//        insert into orders (id,totalPrice,cusID,isCompleted) VALUES  (70716,1000,3031,0);
        int re=0;
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "insert into addresses (userID, receiver ,phone ,address) VALUES  (?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, a.getUserid());
            pst.setString(2,a.getReceiver());
            pst.setString(3,a.getPhone());
            pst.setString(4,a.getAddress());


            re = pst.executeUpdate();

            System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return re;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Address a) {
        return 0;
    }

    @Override
    public int delete(Address a) {
        return 0;
    }

    @Override
    public ArrayList<Address> selectAll() {
        return null;
    }

    @Override
    public Address selectById(int id) {
        Address res = new Address();
        try{
            Connection conn = JDBCUtil.getConnection();
            String sql = "select * from addresses where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
               int user = rs.getInt("userID");
                String receiver = rs.getString("receiver");
                String phone = rs.getString("phone");
                String address = rs.getString("address");

                res =  new Address(user,receiver,phone,address);
            }

            JDBCUtil.closeConnection(conn);
            return res;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Address> selectByUserID(int userID) {
        ArrayList<Address> res = new ArrayList<>();
        try{
            Connection conn = JDBCUtil.getConnection();
            String sql = "select * from addresses where userID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,userID);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int user = rs.getInt("userID");
                String receiver = rs.getString("receiver");
                String phone = rs.getString("phone");
                String address = rs.getString("address");

                res.add(new Address(user,receiver,phone,address));
            }

            JDBCUtil.closeConnection(conn);
            return res;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getMaxID() {
        int res=-1;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select max(id) from addresses;";
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
    public ArrayList<Address> selectByCondition(Address a) {
        return null;
    }

    public static void main(String[] args) {
//        String s= "1,2,3,4,";
//        System.out.println(s.substring(0,s.length()-1));
//
//        ArrayList<Integer> ids = new ArrayList<>();
//        ids.add(3031);
//        ids.add(3032);
//        System.out.println(OrderDAO.getInstance().totalSpendAll());
//        System.out.println(ids);
//
//        System.out.println(OrderDAO.getInstance().totalSpendOfIDs(ids));
        System.out.println(AddressDAO.getInstance().selectByUserID(3031));
    }
}
