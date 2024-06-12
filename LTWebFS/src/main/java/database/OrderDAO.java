package database;

import model.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

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
            pst.setInt(2, order.getTotalPrice());
            pst.setInt(3, order.getCusID());
            pst.setString(4,order.getReceiverInfo());
            pst.setInt(5, order.getDeliveryFee());
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
                int deliveryfee = rs.getInt("deliveryfee");
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
                int deliveryfee = rs.getInt("deliveryfee");
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
    public ArrayList<Order> selectOrderByCusIdStatus(int cusIdin, ArrayList<Integer> s) {
        String sString = "(";
        for(Integer i : s) {
            sString+= i +",";
        }
        sString =  sString.substring(0,sString.length()-1);
        sString+=")";
        ArrayList<Order> res = new ArrayList<Order>();
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "select * from orders where cusID = ? and status in "+ sString +" order by id desc;";
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
                int deliveryfee = rs.getInt("deliveryfee");
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
    public ArrayList<Order> selectRecentOrder(int n) {

        ArrayList<Order> res = new ArrayList<Order>();
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "select id, cusId, dateSet, timeSet from orders where dateSet in (select date(orderTime) from users where level = 0 and orderTime is not null order by orderTime desc) and timeSet in (select time(orderTime) from users where level = 0 and orderTime is not null order by orderTime desc) limit 0,?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,n);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                int cusID  = rs.getInt("cusID");
                Date dateSql = rs.getDate("dateSet");
                Datee dateeSet = new Datee(dateSql);
                Time timeSql = rs.getTime("timeSet");

                Order  o = new Order(id,dateeSet,timeSql,cusID);
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
                int deliveryfee = rs.getInt("deliveryfee");
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
                int deliveryfee = rs.getInt("deliveryfee");
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
                int deliveryfee = rs.getInt("deliveryfee");
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
                int totalPrice = rs.getInt("totalPrice");
                int cusId = rs.getInt("cusID");
                String receiverInfo = rs.getString("receiverInfo");
                int deliveryfee = rs.getInt("deliveryfee");
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

    public int countOrder(int time) {
        int re=0;
        String timee="";
        if(time==1) { //today
            timee = "where Date(dateSet) = CURRENT_DATE";
        } else if(time==2){ //month
            timee = "where Month(dateSet) = Month(CURRENT_DATE)";
        } else if(time==3){ //year
            timee = "where Year(dateSet) = Year(CURRENT_DATE)";
        } else { //all time
            timee="";
        }
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select count(*) as total FROM `orders` " + timee;
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                re = rs.getInt("total");
            }
            JDBCUtil.closeConnection(conn);
            return re;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    public int countPreOrder(int time) {
        int re=0;
        String timee="";
        if(time==1) { //today
            timee = "where Date(dateSet) = CURRENT_DATE - 1 ";
        } else if(time==2){ //month
            timee = "where Month(dateSet) = Month(CURRENT_DATE) -1 ";
        } else if(time==3){ //year
            timee = "where Year(dateSet) = Year(CURRENT_DATE) -1";
        } else { //all time
            timee="";
        }
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select count(*) as total FROM `orders` " + timee;
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                re = rs.getInt("total");
            }
            JDBCUtil.closeConnection(conn);
            return re;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    public int selectPreRevenue(int time) {
        int re=0;
        String timee="";
        if(time==1) { //today
            timee = "where Date(dateSet) = CURRENT_DATE - 1 ";
        } else if(time==2){ //month
            timee = "where Month(dateSet) = Month(CURRENT_DATE) -1 ";
        } else if(time==3){ //year
            timee = "where Year(dateSet) = Year(CURRENT_DATE) -1";
        } else { //all time
            timee="";
        }
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select sum(totalPrice) as total FROM `orders` " + timee;
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                re = rs.getInt("total");
            }
            JDBCUtil.closeConnection(conn);
            return re;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    public int selectRevenue(int time) {
        int re=0;
        String timee="";
        if(time==1) { //today
            timee = "where Date(dateSet) = CURRENT_DATE ";
        } else if(time==2){ //month
            timee = "where Month(dateSet) = Month(CURRENT_DATE) ";
        } else if(time==3){ //year
            timee = "where Year(dateSet) = Year(CURRENT_DATE) ";
        } else { //all time
            timee="";
        }
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select sum(totalPrice) as total FROM `orders` " + timee;
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                re = rs.getInt("total");
            }
            JDBCUtil.closeConnection(conn);
            return re;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    public int selectPreCusAmount(int time) {
        int re=0;
        String timee="";
        if(time==1) { //today
            timee = "and Date(dateIn) = CURRENT_DATE - 1 ";
        } else if(time==2){ //month
            timee = "and Month(dateIn) = Month(CURRENT_DATE) -1 ";
        } else if(time==3){ //year
            timee = "and Year(dateIn) = Year(CURRENT_DATE) -1";
        } else { //all time
            timee="";
        }
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select count(id) as total FROM `users` where level = 0 " + timee;
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                re = rs.getInt("total");
            }
            JDBCUtil.closeConnection(conn);
            return re;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }
    public int selectCusAmount(int time) {
        int re=0;
        String timee="";
        if(time==1) { //today
            timee = "and Date(dateIn) = CURRENT_DATE ";
        } else if(time==2){ //month
            timee = "and Month(dateIn) = Month(CURRENT_DATE) ";
        } else if(time==3){ //year
            timee = "and Year(dateIn) = Year(CURRENT_DATE) ";
        } else { //all time
            timee="";
        }
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select count(id) as total FROM `users` where level = 0 " + timee;
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                re = rs.getInt("total");
            }
            JDBCUtil.closeConnection(conn);
            return re;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

//    public Map<Product,Integer> selectTopProduct(int time, int topin)

    public Map<ProductUnit,Integer> selectTopProductUnit(int time, int topin) {
        Map<Integer,Integer> temp = new LinkedHashMap<>();
        ArrayList<Integer> ids = new ArrayList<>();
        Map<ProductUnit,Integer> res = new LinkedHashMap<>();
        String timee="";
        if(time==1) { //today
            timee = "where Date(dateSet) = CURRENT_DATE";
        } else if(time==2){ //month
            timee = "where Month(dateSet) = Month(CURRENT_DATE)";
        } else if(time==3){ //year
            timee = "where Year(dateSet) = Year(CURRENT_DATE)";
        } else { //all time
            timee="";
        }
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select count(d.proid), d.proID from de_orders d where d.ordid in\n" +
                    "        (SELECT id FROM `orders` "+ timee +") group by d.proID order by count(d.proid) desc LIMIT 0, ?;\n";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,topin);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("proID");
                int count = rs.getInt("count(d.proid)");
                ids.add(id);
                temp.put(id,count);
            }
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }

        Map<Integer,ProductUnit> ps = ProductUnitDAO.getInstance().selectByIDsMap(ids);
        for(Integer i : ids) {
            res.put(ps.get(i),temp.get(i));
        }

        return res;
    }

    public Map<User,Integer> selectTopCustomer(int time,int topin) {
        Map<User,Integer> res = new LinkedHashMap<>();
        String timee="";
        if(time==1) { //today
            timee = "where Date(dateSet) = CURRENT_DATE";
        } else if(time==2){ //month
            timee = "where Month(dateSet) = Month(CURRENT_DATE)";
        } else if(time==3){ //year
            timee = "where Year(dateSet) = Year(CURRENT_DATE)";
        } else { //all time
            timee="";
        }

        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT sum(o.totalPrice), o.cusID, u.name, u.email, u.phone FROM `orders` o join users u on o.cusID = u.id "+ timee +" GROUP by o.cusID order by sum(o.totalPrice) desc LIMIT 0,?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,topin);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("cusID");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int sum = rs.getInt("sum(o.totalPrice)");
                User u = new User(id,name,email,phone);
                res.put(u,sum);
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

    public ArrayList<Integer> selectReportSales(int kindTime, ArrayList<Integer> times) {
//       today: gio (0,2,9,15,18,21)
//       month: ngay (1,7,15,22,30)
//       year: thang (1,3,6,9,12)
//       all: nam :lay tat ca nam >=2020
        String sqlTemp = "";

        String timesStr = "(";
        for(Integer i : times) {
            timesStr+=i + ",";
        }
        timesStr = timesStr.substring(0,timesStr.length()-1);
        timesStr +=")";

        if(kindTime==1) { //today
            sqlTemp = "SELECT count(*) as total, hour(timeSet) as timee from orders where hour(timeSet) in " + timesStr + " and Date(dateSet) = CURRENT_DATE group by hour(timeSet) order by hour(timeSet);";
        } else if(kindTime==2){ //month
            sqlTemp = "SELECT count(*) as total, Day(dateSet) as timee from orders where Day(dateSet) in " + timesStr + " and Month(dateSet) = Month(CURRENT_DATE) group by Day(dateSet) order by Day(dateSet);";
        } else if(kindTime==3){ //year
            sqlTemp = "SELECT count(*) as total, Month(dateSet) as timee from orders where Month(dateSet) in " + timesStr + " and Year(dateSet) = Year(CURRENT_DATE) group by Month(dateSet) order by Month(dateSet);";
        } else { //all time
            sqlTemp = "SELECT count(*) as total, year(dateSet) as timee from orders where year(dateSet) in " + timesStr + " group by year(dateSet) order by year(dateSet);";
        }


        ArrayList<Integer> res = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = sqlTemp;
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                int y = rs.getInt("timee");
                res.add(y);
                int n = rs.getInt("total");
                res.add(n);
            }

            JDBCUtil.closeConnection(conn);
            return res;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Integer> selectReportRevenue(int kindTime, ArrayList<Integer> times) {
//       today: gio (0,2,9,15,18,21)
//       month: ngay (1,7,15,22,30)
//       year: thang (1,3,6,9,12)
//       all: nam :lay tat ca nam >=2020
        String sqlTemp = "";

        String timesStr = "(";
        for(Integer i : times) {
            timesStr+=i + ",";
        }
        timesStr = timesStr.substring(0,timesStr.length()-1);
        timesStr +=")";

        if(kindTime==1) { //today
            sqlTemp = "SELECT sum(totalPrice) as total, hour(timeSet) as timee from orders where hour(timeSet) in " + timesStr + " and Date(dateSet) = CURRENT_DATE group by hour(timeSet) order by hour(timeSet);";
        } else if(kindTime==2){ //month
            sqlTemp = "SELECT sum(totalPrice) as total, Day(dateSet) as timee from orders where Day(dateSet) in " + timesStr + " and Month(dateSet) = Month(CURRENT_DATE) group by Day(dateSet) order by Day(dateSet);";
        } else if(kindTime==3){ //year
            sqlTemp = "SELECT sum(totalPrice) as total, Month(dateSet) as timee from orders where Month(dateSet) in " + timesStr + " and Year(dateSet) = Year(CURRENT_DATE) group by Month(dateSet) order by Month(dateSet);";
        } else { //all time
            sqlTemp = "SELECT sum(totalPrice) as total, year(dateSet) as timee from orders where year(dateSet) in " + timesStr + " group by year(dateSet) order by year(dateSet);";
        }


        ArrayList<Integer> res = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = sqlTemp;
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                int y = rs.getInt("timee");
                res.add(y);
                int n = rs.getInt("total");
                n= n/1000000;
                res.add(n);
            }

            JDBCUtil.closeConnection(conn);
            return res;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }

    }
    public ArrayList<Integer> selectReportCustomer(int kindTime, ArrayList<Integer> times) {
//       today: gio (0,2,9,15,18,21)
//       month: ngay (1,7,15,22,30)
//       year: thang (1,3,6,9,12)
//       all: nam :lay tat ca nam
        String sqlTemp = "";

        String timesStr = "(";
        for(Integer i : times) {
            timesStr+=i + ",";
        }
        timesStr = timesStr.substring(0,timesStr.length()-1);
        timesStr +=")";

        if(kindTime==1) { //today
            sqlTemp = "select count(id) as total, Month(dateIn) as timee FROM `users` where level = 0 and Month(dateIn) in " + timesStr + " and Year(dateIn) = Year(CURRENT_DATE) GROUP by (Month(dateIn)) order by  Month(dateIn);";

        } else if(kindTime==2){ //month
            sqlTemp = "select count(id) as total, Day(dateIn) as timee FROM `users` where level = 0 and Day(dateIn) in " + timesStr + " and Month(dateIn) = Month(CURRENT_DATE) GROUP by (Day(dateIn)) order by  Day(dateIn);";
        } else if(kindTime==3){ //year
            sqlTemp = "select count(id) as total, Month(dateIn) as timee FROM `users` where level = 0 and Month(dateIn) in " + timesStr + " and Year(dateIn) = Year(CURRENT_DATE) GROUP by (Month(dateIn)) order by  Month(dateIn);";
        } else { //all time
            sqlTemp = "SELECT count(id) as total, year(dateIn) as timee from users where level = 0 and year(dateIn) in " + timesStr + " group by year(dateIn) order by year(dateIn);";
        }


        ArrayList<Integer> res = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = sqlTemp;
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                int y = rs.getInt("timee");
                res.add(y);
                int n = rs.getInt("total");
                res.add(n);
            }

            JDBCUtil.closeConnection(conn);
            return res;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }

    }

    public Map<Order,ArrayList<Map.Entry<DeOrder,ProductUnit>>> getOrderOf(int userid) {
        Map<Order,ArrayList<Map.Entry<DeOrder,ProductUnit>>> res = new LinkedHashMap<>();
        Map<DeOrder,ProductUnit> subRes = new LinkedHashMap<>();
        ArrayList<Order> os = OrderDAO.getInstance().selectOrderByCusId(userid);
        ArrayList<Integer> oids = new ArrayList<>();
        for(Order o : os) {
            oids.add(o.getId());
            res.put(o,new ArrayList<>());
        }

        if(oids.isEmpty()) return null;

        ArrayList<DeOrder> dos = DeOrderDAO.getInstance().selectByOIDs(oids);
        ArrayList<Integer> pids = new ArrayList<>();
        for(DeOrder deOrder : dos ){
            if(!pids.contains(deOrder.getOrdID())) pids.add(deOrder.getProID());
        }

//        ArrayList<ProductUnit> pus = ProductUnitDAO.getInstance().selectByIDs(pids);

        Map<Integer,ProductUnit> pus = ProductUnitDAO.getInstance().selectByIDsMap(pids);

        for(DeOrder de : dos) {
            subRes.put(de,pus.get(de.getProID()));
        }
        for (Map.Entry<Order, ArrayList<Map.Entry<DeOrder,ProductUnit>>> item : res.entrySet()) {
            int oid = item.getKey().getId();
//            ArrayList<Map<DeOrder, ProductUnit>> temp = new ArrayList<>();
            for (Map.Entry<DeOrder,ProductUnit> de : subRes.entrySet()) {
                if(de.getKey().getOrdID()==oid) {
//                    temp.add(de.getValue());
                    item.getValue().add(de);
                }
            }
//            res.replace(item.getKey(),temp);
        }
        return res;
    }
    public Map<Order,ArrayList<Map.Entry<DeOrder,ProductUnit>>> getOrderOfStatus(int userid, ArrayList<Integer> s) {
        Map<Order,ArrayList<Map.Entry<DeOrder,ProductUnit>>> res = new LinkedHashMap<>();
        Map<DeOrder,ProductUnit> subRes = new LinkedHashMap<>();
        ArrayList<Order> os = OrderDAO.getInstance().selectOrderByCusIdStatus(userid,s);
        ArrayList<Integer> oids = new ArrayList<>();
        for(Order o : os) {
            oids.add(o.getId());
            res.put(o,new ArrayList<>());
        }

        if(oids.isEmpty()) return null;

        ArrayList<DeOrder> dos = DeOrderDAO.getInstance().selectByOIDs(oids);
        ArrayList<Integer> pids = new ArrayList<>();
        for(DeOrder deOrder : dos ){
            if(!pids.contains(deOrder.getOrdID())) pids.add(deOrder.getProID());
        }

//        ArrayList<ProductUnit> pus = ProductUnitDAO.getInstance().selectByIDs(pids);

        Map<Integer,ProductUnit> pus = ProductUnitDAO.getInstance().selectByIDsMap(pids);

        for(DeOrder de : dos) {
            subRes.put(de,pus.get(de.getProID()));
        }
        for (Map.Entry<Order, ArrayList<Map.Entry<DeOrder,ProductUnit>>> item : res.entrySet()) {
            int oid = item.getKey().getId();
//            ArrayList<Map<DeOrder, ProductUnit>> temp = new ArrayList<>();
            for (Map.Entry<DeOrder,ProductUnit> de : subRes.entrySet()) {
                if(de.getKey().getOrdID()==oid) {
//                    temp.add(de.getValue());
                    item.getValue().add(de);
                }
            }
//            res.replace(item.getKey(),temp);
        }
        return res;
    }


    public static void main(String[] args) {

        ArrayList<Integer> temp =new ArrayList<>();
        temp.add(0);

        Map<Order,ArrayList<Map.Entry<DeOrder,ProductUnit>>> t = OrderDAO.getInstance().getOrderOfStatus(3031,temp);

        for (Map.Entry<Order, ArrayList<Map.Entry<DeOrder,ProductUnit>>> item : t.entrySet()) {
            System.out.println("key:" +  item.getKey().getId());
            for(Map.Entry<DeOrder,ProductUnit> de : item.getValue()) {
                System.out.println("\tchi tiet: "+ de.getKey().getOrdID() + " san pham: " + de.getValue().getName());
            }
        }



    }
}
