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
    public int insert(Customer customer) {
        return 0;
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
//        System.out.println(CustomerDAO.getInstance().getUnbackCus(0,0,4));
        Map<User, Order> recentCustomer = CustomerDAO.getInstance().getRecentCustomer(5);
//        System.out.println(recentCustomer);
        String html="";
        for (Map.Entry<User, Order> entry : recentCustomer.entrySet()) {

            html += " <div class=\"activity-item d-flex\">\n" +
                    "                                    <div class=\"activity-content\">\n" +
                    "                                        "+entry.getKey().getId() + " | " + entry.getKey().getName()+"<br/>\n" +
                    "                                        <a href=\"#\" class=\"fw-bold text-dark\">Mã đơn hàng: "+entry.getValue()+"</a> <br/>\n" +
//                    "                                       "+entry.getValue().getDateSet() + " | " + entry.getValue().getTimeSet()+"\n" +
                    "                                    </div>\n" +
                    "                                </div>";

        }
        System.out.println(html);
    }
}
