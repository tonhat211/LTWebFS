package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;
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



    public static void main(String[] args) {
        System.out.println(CustomerDAO.getInstance().selectByCusNameOrEmailOrPhone("038"));
        System.out.println("----------------------");
        System.out.println(CustomerDAO.getInstance().selectById(3031));
    }
}
