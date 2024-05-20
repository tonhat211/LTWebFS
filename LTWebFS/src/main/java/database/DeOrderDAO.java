package database;

import model.Datee;
import model.DeOrder;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeOrderDAO implements IDAO<DeOrder> {

    public static DeOrderDAO getInstance() {
        return new DeOrderDAO();
    }

    @Override
    public int insert(DeOrder deOrder) {
        int re=0;
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "insert into de_orders (ordID, proID, priceUnit ,qty) VALUES  (?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, deOrder.getOrdID());
            pst.setInt(2, deOrder.getProID());
            pst.setFloat(3, deOrder.getPriceUnit());
            pst.setInt(4, deOrder.getQty());



            re = pst.executeUpdate();

            System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return deOrder.getOrdID();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insertDeOrderList(ArrayList<DeOrder> dos) {
        int re=0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String values = "";
            String value="";

            for(DeOrder o : dos) {
                value ="(" + o.getOrdID() + ", " + o.getProID() + ", " + o.getPriceUnit() +", " + o.getQty() +"),";
                values +=value;
            }
            values = values.substring(0,values.length()-1);
//            values+= ")";
            String sql = "insert into de_orders (ordID, proID, priceUnit, qty) VALUES " + values + ";";
            PreparedStatement pst = conn.prepareStatement(sql);

            re = pst.executeUpdate();

            System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return dos.size();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(DeOrder deOrder) {
        return 0;
    }

    @Override
    public int delete(DeOrder deOrder) {
        return 0;
    }

    @Override
    public ArrayList<DeOrder> selectAll() {
        return null;
    }

    @Override
    public DeOrder selectById(int id) {
        return null;
    }

    public ArrayList<DeOrder> selectByOID(int idin) {
        ArrayList<DeOrder> res = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select o.ordID, o.proID, p.name, o.priceUnit, o.qty from de_orders o join products p on o.proID = p.id where ordID = ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,idin);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int oid = rs.getInt("ordID");
                int pid = rs.getInt("proID");
                String pName = rs.getString("name");
                float priceUnit = rs.getFloat("priceUnit");
                int qty = rs.getInt("qty");
                res.add(new DeOrder(oid,pid,pName,priceUnit,qty));

            }
            JDBCUtil.closeConnection(conn);
            return res;



        } catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    public ArrayList<DeOrder> selectByOIDs(ArrayList<Integer> oids) {
        String oidss = "";
        for(Integer i : oids) {
            oidss += i +",";
        }
        if(oidss.length()>1) {
            oidss =oidss.substring(0,oidss.length()-1);

        }

        ArrayList<DeOrder> res = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select o.ordID, o.proID, p.name, o.priceUnit, o.qty from de_orders o join products p on o.proID = p.id where ordID in (" + oidss+");";
            PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setString(1,oidss);
            System.out.println(pst);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int oid = rs.getInt("ordID");
                int pid = rs.getInt("proID");
                String pName = rs.getString("name");
                float priceUnit = rs.getFloat("priceUnit");
                int qty = rs.getInt("qty");
                res.add(new DeOrder(oid,pid,pName,priceUnit,qty));

            }
            JDBCUtil.closeConnection(conn);
            return res;



        } catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<DeOrder> selectByCondition(DeOrder deOrder) {
        return null;
    }

    public static void main(String[] args) {
        ArrayList<Integer> oids = new ArrayList<>();
        oids.add(1);
        oids.add(9);
        System.out.println(DeOrderDAO.getInstance().selectByOIDs(oids));


    }
}
