package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;

public class ProductUnitDAO implements IDAO<ProductUnit> {

    public static ProductUnitDAO getInstance() {
        return new ProductUnitDAO();
    }

    @Override
    public int insert(ProductUnit productUnit) {
        return 0;
    }

    @Override
    public int update(ProductUnit productUnit) {
        return 0;
    }

    @Override
    public int delete(ProductUnit productUnit) {
        return 0;
    }

    @Override
    public ArrayList<ProductUnit> selectAll() {
        ArrayList<ProductUnit> re = new ArrayList<>();
        ArrayList<Integer>  idAdded = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select p.id  as pid, p.name as pname, p.brandid as pbrandid, p.areaid as pareaid, p.kind as  pkind, p.amount as pamount,  p.description as pdescription, u.imei as uimei, u.productid as uproductid, u.color as ucolor, u.size as usize, u.wattage as uwattage, u.price as uprice, u.amount as uamount, u.yearmade as uyearmade, u.dateimport as udateimport, u.available as uavailable, b.id as bid, b.name as bname, b.country as bcountry, b.available as bavailable, i.id as iid, i.url as iurl, i.parentid as  iparentid\n" +
                    "from products p join units u on p.id = u.productID" +
                    "                     join brands b on b.id = p.brandID" +
                    "                    join images  i on i.parentID = p.id;";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("pid");
                String name = rs.getString("pname");
                int brandID = rs.getInt("pbrandID");
                int areaID = rs.getInt("pareaID");
                String kind = rs.getString("pkind");
                int amount = rs.getInt("pamount");
                String des = rs.getString("pdescription");
//                ps.add(new Product(id,name, brandID, areaID, kind, amount, des));

                int imei = rs.getInt("uimei");
                int proID = rs.getInt("uproductID");
                String color = rs.getString("ucolor");
                String size = rs.getString("usize");
                double wattage = rs.getDouble("uwattage");
                double price = rs.getDouble("uprice");
                int yearMade = rs.getInt("uyearMade");
                Date dateSql = rs.getDate("udateImport");
                Datee dateImport = new Datee(dateSql);
                int available = rs.getInt("uavailable");
                String phanloai = "" + (color!=""?color:"") + (size!=""?" - " + size:"") + (wattage!=0?" - " + wattage:"");


                int brandid = rs.getInt("bid");
                String brandName = rs.getString("bname");
                String country = rs.getString("bcountry");
                int brandAvailable = rs.getInt("bavailable");

                int imageId = rs.getInt("iid");
                String url = rs.getString("iurl");
                int parentID = rs.getInt("iparentID");

                ProductUnit pu = new ProductUnit(id,name,brandID,areaID,kind,amount,des,imei,color,size,(float) wattage,phanloai,price,yearMade,country,dateImport.toString(),available,null,brandName,url);
                if(!idAdded.contains(id)){
                    idAdded.add(id);
                    re.add(pu);
                }


            }
            JDBCUtil.closeConnection(conn);
            return re;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }


    }

    @Override
    public ProductUnit selectById(int id) {
        return null;
    }

    @Override
    public ArrayList<ProductUnit> selectByCondition(ProductUnit productUnit) {
        return null;
    }

    public ProductUnit selectOneByID(int idin) {
        ProductUnit re = new ProductUnit();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select p.id  as pid, p.name as pname, p.brandid as pbrandid, p.areaid as pareaid, p.kind as  pkind, p.amount as pamount,  p.description as pdescription, u.imei as uimei, u.productid as uproductid, u.color as ucolor, u.size as usize, u.wattage as uwattage, u.price as uprice, u.amount as uamount, u.yearmade as uyearmade, u.dateimport as udateimport, u.available as uavailable, b.id as bid, b.name as bname, b.country as bcountry, b.available as bavailable, i.id as iid, i.url as iurl, i.parentid as  iparentid\n" +
                    "from products p join units u on p.id = u.productID" +
                    "                     join brands b on b.id = p.brandID" +
                    "                    join images  i on i.parentID = p.id" +
                    "                    " +
                    "                    where p.id = ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,idin);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("pid");
                String name = rs.getString("pname");
                int brandID = rs.getInt("pbrandID");
                int areaID = rs.getInt("pareaID");
                String kind = rs.getString("pkind");
                int amount = rs.getInt("pamount");
                String des = rs.getString("pdescription");
//                ps.add(new Product(id,name, brandID, areaID, kind, amount, des));

                int imei = rs.getInt("uimei");
                int proID = rs.getInt("uproductID");
                String color = rs.getString("ucolor");
                String size = rs.getString("usize");
                double wattage = rs.getDouble("uwattage");
                double price = rs.getDouble("uprice");
                int yearMade = rs.getInt("uyearMade");
                Date dateSql = rs.getDate("udateImport");
                Datee dateImport = new Datee(dateSql);
                int available = rs.getInt("uavailable");
                String phanloai = "" + (color!=""?color:"") + (size!=""?" - " + size:"") + (wattage!=0?" - " + wattage:"");


                int brandid = rs.getInt("bid");
                String brandName = rs.getString("bname");
                String country = rs.getString("bcountry");
                int brandAvailable = rs.getInt("bavailable");

                int imageId = rs.getInt("iid");
                String url = rs.getString("iurl");
                int parentID = rs.getInt("iparentID");

                re = new ProductUnit(id,name,brandID,areaID,kind,amount,des,imei,color,size,(float) wattage,phanloai,price,yearMade,country,dateImport.toString(),available,null,brandName,url);


            }
            JDBCUtil.closeConnection(conn);
            return re;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    public ProductUnit selectByIDs(int idin) {
        ProductUnit re = new ProductUnit();
        Product p = ProductDAO.getInstance().selectById(idin);
        Unit u = UnitDAO.getInstance().selectOneByProId(idin);
        Brand b = BrandDAO.getInstance().selectById(p.getBrandID());
        ArrayList<Image> imgs = ImageDAO.getInstance().selectByParentID(idin);

        int id = p.getId();
        String name = p.getName();
        int brandID = p.getBrandID();
        int areaID = p.getAreaID();
        String kind = p.getKind();
        int amount = p.getAmount();
        String des = p.getDescription();

        int imei = u.getImei();
        String color = u.getColor();
        String size = u.getSize();
        float wattage = (float) u.getWattage();
        String phanloai = "" + (color!=""?color:"") + (size!=""?" - " + size:"") + (wattage!=0?" - " + wattage:"");
        double price = u.getPrice();
        int yearMade = u.getYearMade();
        String madeIn = "";
        String dateImport = u.getDateImport().toString();
        int available = u.getAvailable();
        ArrayList<String> imgrls = new ArrayList<>();
        for(Image i : imgs){
            imgrls.add(i.getUrl());
        }
        String brand = b.getName();
        String img = imgrls.size()>0 ? imgrls.get(0) : "no";

        re = new ProductUnit(id,name,brandID,areaID,kind,amount,des,imei,color,size,wattage,phanloai,price,yearMade,madeIn,dateImport,available,imgrls,brand,img);

        return re;

    }


    public ArrayList<ProductUnit> selectByKind(String kindin) {
        ArrayList<ProductUnit> re = new ArrayList<>();
        ArrayList<Integer> idAdded  = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select p.id  as pid, p.name as pname, p.brandid as pbrandid, p.areaid as pareaid, p.kind as  pkind, p.amount as pamount,  p.description as pdescription, u.imei as uimei, u.productid as uproductid, u.color as ucolor, u.size as usize, u.wattage as uwattage, u.price as uprice, u.amount as uamount, u.yearmade as uyearmade, u.dateimport as udateimport, u.available as uavailable, b.id as bid, b.name as bname, b.country as bcountry, b.available as bavailable, i.id as iid, i.url as iurl, i.parentid as  iparentid\n" +
                    "from products p join units u on p.id = u.productID" +
                    "                     join brands b on b.id = p.brandID" +
                    "                    join images  i on i.parentID = p.id" +
                    "                    " +
                    "                    where p.kind = ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,kindin);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("pid");
                String name = rs.getString("pname");
                int brandID = rs.getInt("pbrandID");
                int areaID = rs.getInt("pareaID");
                String kind = rs.getString("pkind");
                int amount = rs.getInt("pamount");
                String des = rs.getString("pdescription");
//                ps.add(new Product(id,name, brandID, areaID, kind, amount, des));

                int imei = rs.getInt("uimei");
                int proID = rs.getInt("uproductID");
                String color = rs.getString("ucolor");
                String size = rs.getString("usize");
                double wattage = rs.getDouble("uwattage");
                double price = rs.getDouble("uprice");
                int yearMade = rs.getInt("uyearMade");
                Date dateSql = rs.getDate("udateImport");
                Datee dateImport = new Datee(dateSql);
                int available = rs.getInt("uavailable");
                String phanloai = "" + (color!=""?color:"") + (size!=""?" - " + size:"") + (wattage!=0?" - " + wattage:"");


                int brandid = rs.getInt("bid");
                String brandName = rs.getString("bname");
                String country = rs.getString("bcountry");
                int brandAvailable = rs.getInt("bavailable");

                int imageId = rs.getInt("iid");
                String url = rs.getString("iurl");
                int parentID = rs.getInt("iparentID");

                ProductUnit pu = new ProductUnit(id,name,brandID,areaID,kind,amount,des,imei,color,size,(float) wattage,phanloai,price,yearMade,country,dateImport.toString(),available,null,brandName,url);
                if(!idAdded.contains(id)){
                    idAdded.add(id);
                    re.add(pu);
                }


            }
            JDBCUtil.closeConnection(conn);
            return re;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ProductUnit> selectByKindAndName(String kindin, String namein) {
        ArrayList<ProductUnit> re = new ArrayList<>();
        ArrayList<Integer> idAdded  = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select p.id  as pid, p.name as pname, p.brandid as pbrandid, p.areaid as pareaid, p.kind as  pkind, p.amount as pamount,  p.description as pdescription, u.imei as uimei, u.productid as uproductid, u.color as ucolor, u.size as usize, u.wattage as uwattage, u.price as uprice, u.amount as uamount, u.yearmade as uyearmade, u.dateimport as udateimport, u.available as uavailable, b.id as bid, b.name as bname, b.country as bcountry, b.available as bavailable, i.id as iid, i.url as iurl, i.parentid as  iparentid\n" +
                    "from products p join units u on p.id = u.productID" +
                    "                     join brands b on b.id = p.brandID" +
                    "                    join images  i on i.parentID = p.id" +
                    "                    " +
                    "                    where p.name like ? and p.kind = ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,"%"  + namein + "%");
            pst.setString(2,kindin);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("pid");
                String name = rs.getString("pname");
                int brandID = rs.getInt("pbrandID");
                int areaID = rs.getInt("pareaID");
                String kind = rs.getString("pkind");
                int amount = rs.getInt("pamount");
                String des = rs.getString("pdescription");
//                ps.add(new Product(id,name, brandID, areaID, kind, amount, des));

                int imei = rs.getInt("uimei");
                int proID = rs.getInt("uproductID");
                String color = rs.getString("ucolor");
                String size = rs.getString("usize");
                double wattage = rs.getDouble("uwattage");
                double price = rs.getDouble("uprice");
                int yearMade = rs.getInt("uyearMade");
                Date dateSql = rs.getDate("udateImport");
                Datee dateImport = new Datee(dateSql);
                int available = rs.getInt("uavailable");
                String phanloai = "" + (color!=""?color:"") + (size!=""?" - " + size:"") + (wattage!=0?" - " + wattage:"");


                int brandid = rs.getInt("bid");
                String brandName = rs.getString("bname");
                String country = rs.getString("bcountry");
                int brandAvailable = rs.getInt("bavailable");

                int imageId = rs.getInt("iid");
                String url = rs.getString("iurl");
                int parentID = rs.getInt("iparentID");

                ProductUnit pu = new ProductUnit(id,name,brandID,areaID,kind,amount,des,imei,color,size,(float) wattage,phanloai,price,yearMade,country,dateImport.toString(),available,null,brandName,url);
                if(!idAdded.contains(id)){
                    idAdded.add(id);
                    re.add(pu);
                }


            }
            JDBCUtil.closeConnection(conn);
            return re;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(ProductUnitDAO.getInstance().selectOneByID(10179));
        ArrayList<ProductUnit> pus = ProductUnitDAO.getInstance().selectByKindAndName("a","máy");
        for(ProductUnit  p : pus){
            System.out.println(p);
        }
    }
}
