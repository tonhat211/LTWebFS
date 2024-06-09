package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomerDAO implements IDAO<Customer> {
    public static CustomerDAO getInstance() {
        return new CustomerDAO();
    }

    @Override
    public int insert(Customer u){
        int re=0;
        try {

            Connection conn = JDBCUtil.getConnection();
            String sql = "insert into users (id,name,email,pwd,level,phone,address,info,dateIn,available) values (?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, u.getId());
            pst.setString(2, u.getName());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getPwd());
            pst.setInt(5, u.getLevel());
            pst.setString(6, u.getPhone());
            pst.setString(7, u.getAddress());
            pst.setString(8, u.getInfo());
            pst.setString(9, u.getDateIn().getDateInMonthDayYearSql());
            pst.setInt(10, u.getAvailable());

            re = pst.executeUpdate();

            System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return u.getId();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insertCustomer(User u){
        int re=0;
        try {

            Connection conn = JDBCUtil.getConnection();
            String sql = "insert into users (id,name,email,pwd,level,phone,address,info,dateIn,available) values (?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, u.getId());
            pst.setString(2, u.getName());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getPwd());
            pst.setInt(5, u.getLevel());
            pst.setString(6, u.getPhone());
            pst.setString(7, u.getAddress());
            pst.setString(8, u.getInfo());
            pst.setString(9, u.getDateIn().getDateInMonthDayYearSql());
            pst.setInt(10, u.getAvailable());

            re = pst.executeUpdate();

            System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return u.getId();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Customer customer) {
        return 0;
    }

    @Override
    public int delete(Customer customer) {
        return 0;
    }

    @Override
    public ArrayList<Customer> selectAll() {
        ArrayList<Customer>res =  new ArrayList<>();
        ArrayList<User> us = UserDAO.getInstance().selectAllCus();
        Map<Integer,Double> totalSpend = OrderDAO.getInstance().totalSpendAll();
        for(User u : us ){
            Double money = totalSpend.get(u.getId());
            if(money==null) money = 0.0;
            Customer c = new Customer(u, money);
            res.add(c);
        }
        return res;
    }

    public Map<User,Order> getRecentCustomer(int n) {
        ArrayList<User> users = UserDAO.getInstance().selectRecentCus(n);
        ArrayList<Order> orders = OrderDAO.getInstance().selectRecentOrder(n);

        Map<User,Order> res = new LinkedHashMap<>();
        for(User u : users) {
            res.put(u,null);
        }

        for(Order o : orders) {
            User temp = new User(o.getCusID());
            if(res.containsKey(temp)) {
                res.replace(temp,o);
            }
        }

        return res;


    }






    @Override
    public Customer selectById(int idin) {
        Customer cus = new Customer();
        User u = UserDAO.getInstance().selectById(idin);
        Map<Integer,Double> totalSpend = OrderDAO.getInstance().totalSpendAll();

        Double money = totalSpend.get(u.getId());
        if(money==null) money = 0.0;
        cus = new Customer(u, money);
        return cus;
    }

    @Override
    public ArrayList<Customer> selectByCondition(Customer customer) {
        return null;
    }

    public ArrayList<Customer> selectByCusNameOrEmailOrPhone(String input) {
        ArrayList<Customer>res =  new ArrayList<>();
        ArrayList<User> us = UserDAO.getInstance().selectByNameOrEmailOrPhone(input);
        Map<Integer,Double> totalSpend = OrderDAO.getInstance().totalSpendAll();
        for(User u : us ){
            Double money = totalSpend.get(u.getId());
            if(money==null) money = 0.0;
            Customer c = new Customer(u, money);
            res.add(c);
        }
        return res;
    }

    public ArrayList<User> getUnbackCus(int n, int index, int amount) {
        return UserDAO.getInstance().selectUnbackCus(n,index,amount);
    }



    public static void main(String[] args) {
        String t = "====";
        System.out.println(t.split("=")[0]);
    }
}
