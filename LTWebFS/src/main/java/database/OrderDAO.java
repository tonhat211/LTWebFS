package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderDAO implements IDAO<Order> {

    public static OrderDAO getInstance() {
        return new OrderDAO();
    }
    @Override
    public int insert(Order order) {
//        insert into orders (id,totalPrice,cusID,isCompleted) VALUES  (70716,1000,3031,0);
        int re=0;
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "insert into orders (id, totalPrice, cusID ,deliveryfee, isCompleted) VALUES  (?,?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, order.getId());
            pst.setFloat(2, order.getTotalPrice());
            pst.setInt(3, order.getCusID());
            pst.setFloat(4, order.getDeliveryFee());
            pst.setInt(5,order.getIsCompleted());


            re = pst.executeUpdate();

            System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return order.getId();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Order order) {
        return 0;
    }

    @Override
    public int delete(Order order) {
        return 0;
    }

    @Override
    public ArrayList<Order> selectAll() {
        return null;
    }

    public ArrayList<Order> selectOrderByCusId(int cusIdin) {
        ArrayList<Order> res = new ArrayList<Order>();
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "select * from orders where cusID = ? order by id asc;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,cusIdin);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                Date dateSql  = rs.getDate("dateSet");
                Datee dateeSet  = new Datee(dateSql);
                Time  timeSql  = rs.getTime("timeSet");
                int totalPrice =  rs.getInt("totalPrice");
                int cusID  = rs.getInt("cusID");
                int isCompleted = rs.getInt("isCompleted");

                Order  o = new Order(id,dateeSet,timeSql,totalPrice,cusID,isCompleted);
                res.add(o);
            }

//			System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return res;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }




    public Map<Integer,Double> totalSpendAll(){
        Map<Integer,Double> res = new HashMap<>();
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "SELECT cusid, sum(totalPrice) as  totalspend FROM orders group by cusID;";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                int cusid = rs.getInt("cusID");
                double totalSpend = (double) rs.getInt("totalspend");
                res.put(cusid,totalSpend);
            }

//			System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return res;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Map<Integer,Double> totalSpendOfIDs(ArrayList<Integer> ids){
        String idsString ="";
        for(Integer i : ids) {
            idsString+= i +",";
        }
        idsString = idsString.substring(0, idsString.length()-1);
//        idsString+="";
        System.out.println(idsString);
        Map<Integer,Double> res = new HashMap<>();
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "SELECT cusid, sum(totalPrice) as  totalspend FROM orders where cusID in (?) group by cusID;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,idsString);
            System.out.println(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                int cusid = rs.getInt("cusID");
                double totalSpend = (double) rs.getInt("totalspend");
                res.put(cusid,totalSpend);
            }

//			System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return res;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Order selectById(int idin) {
        Order res = new Order();
        try{
            Connection conn = JDBCUtil.getConnection();
            String sql = "select * from orders where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,idin);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                Date dateSql = rs.getDate("dateSet");
                Datee dateSet = new Datee(dateSql);
                Time timeSet = rs.getTime("timeSet");
                float totalPrice = rs.getInt("totalPrice");
                int cusId = rs.getInt("cusID");
                int isCompleted = rs.getInt("isCompleted");

                res =  new Order(id,dateSet,timeSet,totalPrice,cusId,isCompleted);
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
            String sql = "select max(id) from orders;";
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
    public ArrayList<Order> selectByCondition(Order order) {
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
        System.out.println(OrderDAO.getInstance().selectById(7071));
        System.out.println(OrderDAO.getInstance().selectOrderByCusId(3031));
    }
}
