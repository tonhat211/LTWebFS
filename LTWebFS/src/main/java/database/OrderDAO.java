package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

            String sql = "insert into orders (id, totalPrice, cusID ,receiverInfo ,deliveryfee, status) VALUES  (?,?,?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, order.getId());
            pst.setFloat(2, order.getTotalPrice());
            pst.setInt(3, order.getCusID());
            pst.setString(4,order.getReceiverInfo());
            pst.setFloat(5, order.getDeliveryFee());
            pst.setInt(6,order.getStatus());


            re = pst.executeUpdate();

            System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return order.getId();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getOrderStatus(int idin) {
        int re = -99;
        try{
            Connection conn = JDBCUtil.getConnection();
            String sql = "select status from orders where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,idin);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int status = rs.getInt("status");
                re = status;
            }
            JDBCUtil.closeConnection(conn);
            return re;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateOrderStatus(int idin, int status) {
        int re = 0;
        try{
            Connection conn = JDBCUtil.getConnection();
            String sql = "update orders set status = ?, updateTime = ? where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,status);
            Timestamp t = new Timestamp(new java.util.Date().getTime());
            pst.setTimestamp(2,t);
            pst.setInt(3,idin);
            re = pst.executeUpdate();

            JDBCUtil.closeConnection(conn);
            return re;
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
        ArrayList<Order> res = new ArrayList<Order>();
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "select * from orders order by updateTime desc";
            PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setInt(1,cusIdin);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                Date dateSql  = rs.getDate("dateSet");
                Datee dateeSet  = new Datee(dateSql);
                Time  timeSql  = rs.getTime("timeSet");
                int totalPrice =  rs.getInt("totalPrice");
                int cusID  = rs.getInt("cusID");
                String receiverInfo = rs.getString("receiverInfo");
                float deliveryfee = rs.getFloat("deliveryfee");
                int status = rs.getInt("status");

                Order  o = new Order(id,dateeSet,timeSql,totalPrice,cusID,receiverInfo,deliveryfee, status);
                res.add(o);
            }

//			System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return res;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Order> selectOrderByCusId(int cusIdin) {
        ArrayList<Order> res = new ArrayList<Order>();
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "select * from orders where cusID = ? order by id desc;";
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
                String receiverInfo = rs.getString("receiverInfo");
                float deliveryfee = rs.getFloat("deliveryfee");
                int status = rs.getInt("status");

                Order  o = new Order(id,dateeSet,timeSql,totalPrice,cusID,receiverInfo,deliveryfee, status);
                res.add(o);
            }

//			System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return res;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Order> selectOrderStatus(int statusin) {
        ArrayList<Order> res = new ArrayList<Order>();
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "select * from orders where status = ? order by updateTime desc;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, statusin);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Date dateSql = rs.getDate("dateSet");
                Datee dateeSet = new Datee(dateSql);
                Time timeSql = rs.getTime("timeSet");
                int totalPrice = rs.getInt("totalPrice");
                int cusID = rs.getInt("cusID");
                String receiverInfo = rs.getString("receiverInfo");
                float deliveryfee = rs.getFloat("deliveryfee");
                int status = rs.getInt("status");

                Order o = new Order(id, dateeSet, timeSql, totalPrice, cusID, receiverInfo, deliveryfee, status);
                res.add(o);
            }

//			System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return res;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Order> selectOrderBy(String input) {
        ArrayList<Order> res = new ArrayList<Order>();
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "select * from orders where id like "+ "'%"+input+"%'" +" or dateSet like "+ "'%"+input+"%' or timeSet like " + "'%"+input+"%' order by updateTime desc;";
            PreparedStatement pst = conn.prepareStatement(sql);

//            pst.setInt(1, statusin);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Date dateSql = rs.getDate("dateSet");
                Datee dateeSet = new Datee(dateSql);
                Time timeSql = rs.getTime("timeSet");
                int totalPrice = rs.getInt("totalPrice");
                int cusID = rs.getInt("cusID");
                String receiverInfo = rs.getString("receiverInfo");
                float deliveryfee = rs.getFloat("deliveryfee");
                int status = rs.getInt("status");

                Order o = new Order(id, dateeSet, timeSql, totalPrice, cusID, receiverInfo, deliveryfee, status);
                res.add(o);
            }

//			System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return res;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Order> selectOrderStatusIn(ArrayList<Integer> statuses) {
        ArrayList<Order> res = new ArrayList<Order>();
        String tempString ="";
        for(Integer i : statuses) {
            tempString+= i +",";
        }
        if(tempString.length()>1)
            tempString = tempString.substring(0, tempString.length()-1);
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "select * from orders where status in (" + tempString + ") order by  updateTime desc;";
            PreparedStatement pst = conn.prepareStatement(sql);


            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Date dateSql = rs.getDate("dateSet");
                Datee dateeSet = new Datee(dateSql);
                Time timeSql = rs.getTime("timeSet");
                int totalPrice = rs.getInt("totalPrice");
                int cusID = rs.getInt("cusID");
                String receiverInfo = rs.getString("receiverInfo");
                float deliveryfee = rs.getFloat("deliveryfee");
                int status = rs.getInt("status");

                Order o = new Order(id, dateeSet, timeSql, totalPrice, cusID, receiverInfo, deliveryfee, status);
                res.add(o);
            }

//			System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return res;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Map<Integer,String> getOrderDatailsOf(ArrayList<Integer> ids) {
        Map<Integer,String> res = new HashMap<>();

        ArrayList<DeOrder> des = DeOrderDAO.getInstance().selectByOIDs(ids);
        for(DeOrder de : des) {
            String detail = de.getQty() + " x " + de.getpName();
            if(res.containsKey(de.getOrdID())) {
                String newDatail = res.get(de.getOrdID()) + "\n" + detail;
                res.replace(de.getOrdID(),newDatail);
            } else {
                res.put(de.getOrdID(),detail);
            }
        }

        return res;

    }

    public Map<Order,String> getOrderAndDatail(int statusin) {
        Map<Order,String> res = new LinkedHashMap<>();
        ArrayList<Order> os = OrderDAO.getInstance().selectOrderStatus(statusin);
        if(os.isEmpty()) return null;
        ArrayList<Integer> ids = new ArrayList<>();
        Order temp = new Order();
        for(Order o : os) {
            ids.add(o.getId());
            res.put(o,"");
        }
        ArrayList<DeOrder> des = DeOrderDAO.getInstance().selectByOIDs(ids);
        for(DeOrder de : des) {
            String detail = de.getQty() + " x " + de.getpName();
            temp = new Order(de.getOrdID());
            if(res.containsKey(temp)) {
                String newDatail = detail + "<br>" + res.get(temp);
                res.replace(temp,newDatail);
            } else {
                res.put(temp,detail);
            }
        }

        return res;

    }

    public Map<Order,String> getOrderAndDatailIn(ArrayList<Integer> statuses) {
        Map<Order,String> res = new LinkedHashMap<>();
        ArrayList<Order> os = OrderDAO.getInstance().selectOrderStatusIn(statuses);
        System.out.println("id ar");
        for(Order o : os) {
            System.out.println(o.getId());
        }
        if(os.isEmpty()) return null;
        ArrayList<Integer> ids = new ArrayList<>();
        Order temp = new Order();
        for(Order o : os) {
            System.out.println("id temp: " + o.getId());
            ids.add(o.getId());
            res.put(o,"");

        }
        System.out.println("id trong");
        for (Map.Entry<Order, String> item : res.entrySet()) {
            System.out.println(item.getKey().getId());
        }
        ArrayList<DeOrder> des = DeOrderDAO.getInstance().selectByOIDs(ids);
        for(DeOrder de : des) {
            String detail = de.getQty() + " x " + de.getpName();
            temp = new Order(de.getOrdID());
            if(res.containsKey(temp)) {
                String newDatail = detail + "<br>" + res.get(temp);
                res.replace(temp,newDatail);
            } else {
                res.put(temp,detail);
            }
        }

        return res;

    }

    public Map<Order,String> searchOrderAndDatailBy(String input) {
        Map<Order,String> res = new LinkedHashMap<>();
        ArrayList<Order> os = OrderDAO.getInstance().selectOrderBy(input);
        if(os.isEmpty()) return null;
        ArrayList<Integer> ids = new ArrayList<>();
        Order temp = new Order();
        for(Order o : os) {
            ids.add(o.getId());
            res.put(o,"");
        }
        ArrayList<DeOrder> des = DeOrderDAO.getInstance().selectByOIDs(ids);
        for(DeOrder de : des) {
            String detail = de.getQty() + " x " + de.getpName();
            temp = new Order(de.getOrdID());
            if(res.containsKey(temp)) {
                String newDatail = detail + "<br>" + res.get(temp);
                res.replace(temp,newDatail);
            } else {
                res.put(temp,detail);
            }
        }

        return res;

    }
    public Map<Order,String> getAllOrderAndDatail() {
        Map<Order,String> res = new LinkedHashMap<>();
        ArrayList<Order> os = OrderDAO.getInstance().selectAll();
        ArrayList<Integer> ids = new ArrayList<>();
        Order temp = new Order();
        for(Order o : os) {
            ids.add(o.getId());
            res.put(o,"");
        }
        ArrayList<DeOrder> des = DeOrderDAO.getInstance().selectByOIDs(ids);
        for(DeOrder de : des) {
            String detail = de.getQty() + " x " + de.getpName();
            temp = new Order(de.getOrdID());
            if(res.containsKey(temp)) {
                String newDatail = res.get(temp) + "\n" + detail;
                res.replace(temp,newDatail);
            } else {
                res.put(temp,detail);
            }
        }

        return res;

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
                String receiverInfo = rs.getString("receiverInfo");
                float deliveryfee = rs.getFloat("deliveryfee");
                int status = rs.getInt("status");

                res =  new Order(id,dateSet,timeSet,totalPrice,cusId,receiverInfo,deliveryfee, status);
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
        ArrayList<Integer> is = new ArrayList<>();
        is.add(-2);
        is.add(-1);
        is.add(-3);

//        ArrayList<Order> os = OrderDAO.getInstance().selectOrderStatus(-3);
//        for(Order o : os) {
//            System.out.println(o.getId() + "\t" + o.getStatus())

//        }
//        Map<Order,String> os = (Map<Order, String>) OrderDAO.getInstance().searchOrderAndDatailBy("00:41:31");;
//        for (Map.Entry<Order, String> item : os.entrySet()) {
//            System.out.println(item.getKey().getId() +"\t" + item.getKey().getStatus() +"\n");
//        }
//        OrderDAO.getInstance().updateOrderStatus(1,0);

        String input = "2024/04/30";
        String inputTokens[] = input.split("/");
        if(inputTokens.length>0) {
            input = "";
            for (int i = 0; i < inputTokens.length; i++) {
                input += inputTokens[i] + "-";
            }
        }
        input = input.substring(0,input.length()-1);

        System.out.println(input);


    }
}
