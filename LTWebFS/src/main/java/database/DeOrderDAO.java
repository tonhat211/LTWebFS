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
        return 0;
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



    @Override
    public ArrayList<DeOrder> selectByCondition(DeOrder deOrder) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(DeOrderDAO.getInstance().selectByOID(7071));
    }
}
