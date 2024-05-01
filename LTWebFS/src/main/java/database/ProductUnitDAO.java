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
                    "                    join images  i on i.parentID = p.id order by p.id asc;";
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
    public ProductUnit selectById(int idin) {
        ProductUnit re = new ProductUnit();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select p.id  as pid, p.name as pname, p.brandid as pbrandid, p.areaid as pareaid, p.kind as  pkind, p.amount as pamount,  p.description as pdescription, u.imei as uimei, u.productid as uproductid, u.color as ucolor, u.size as usize, u.wattage as uwattage, u.price as uprice, u.amount as uamount, u.yearmade as uyearmade, u.dateimport as udateimport, u.available as uavailable, b.id as bid, b.name as bname, b.country as bcountry, b.available as bavailable, i.id as iid, i.url as iurl, i.parentid as  iparentid\n" +
                    "from products p join units u on p.id = u.productID" +
                    "                     join brands b on b.id = p.brandID" +
                    "                    join images  i on i.parentID = p.id where p.id = ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idin);
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

                return new ProductUnit(id,name,brandID,areaID,kind,amount,des,imei,color,size,(float) wattage,phanloai,price,yearMade,country,dateImport.getDateInMonthDayYear(),available,null,brandName,url);


            }
            JDBCUtil.closeConnection(conn);
            return re;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ProductUnit> selectByIDs(ArrayList<Integer> idins) {
        ArrayList<ProductUnit> res = new ArrayList<>();
        if(idins.isEmpty()) return null;
        String ids = "";
        try {

            for(Integer i : idins) {
                ids +=i +",";
            }
            ids = ids.substring(0,ids.length()-1);
            Connection conn = JDBCUtil.getConnection();
            String sql = "select p.id  as pid, p.name as pname, p.brandid as pbrandid, p.areaid as pareaid, p.kind as  pkind, p.amount as pamount,  p.description as pdescription, u.imei as uimei, u.productid as uproductid, u.color as ucolor, u.size as usize, u.wattage as uwattage, u.price as uprice, u.amount as uamount, u.yearmade as uyearmade, u.dateimport as udateimport, u.available as uavailable, b.id as bid, b.name as bname, b.country as bcountry, b.available as bavailable, i.id as iid, i.url as iurl, i.parentid as  iparentid\n" +
                    "from products p join units u on p.id = u.productID" +
                    "                     join brands b on b.id = p.brandID" +
                    "                    join images  i on i.parentID = p.id" +
                    "                    " +
                    "                    where p.id in  ("+ ids + ");";
            PreparedStatement pst = conn.prepareStatement(sql);

//            ids += ")";
//            pst.setString(1,ids);
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


                res.add(new ProductUnit(id,name,brandID,areaID,kind,amount,des,imei,color,size,(float) wattage,phanloai,price,yearMade,country,dateImport.getDateInMonthDayYear(),available,null,brandName,url));

            }
            JDBCUtil.closeConnection(conn);
            return res;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
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

    public ArrayList<ProductUnit> selectByKindOrName(String input) {
        ArrayList<ProductUnit> re = new ArrayList<>();
        ArrayList<Integer> idAdded  = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select p.id  as pid, p.name as pname, p.brandid as pbrandid, p.areaid as pareaid, p.kind as  pkind, p.amount as pamount,  p.description as pdescription, u.imei as uimei, u.productid as uproductid, u.color as ucolor, u.size as usize, u.wattage as uwattage, u.price as uprice, u.amount as uamount, u.yearmade as uyearmade, u.dateimport as udateimport, u.available as uavailable, b.id as bid, b.name as bname, b.country as bcountry, b.available as bavailable, i.id as iid, i.url as iurl, i.parentid as  iparentid\n" +
                    "from products p join units u on p.id = u.productID" +
                    "                     join brands b on b.id = p.brandID" +
                    "                    join images  i on i.parentID = p.id" +
                    "                    " +
                    "                    where p.name like ? or p.kind = ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,"%"  + input + "%");
            pst.setString(2,input);
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

    public int updateAvailableByProID(int idin, int avaiin) {
        int re=0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "update units set available = ? where productID =?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, avaiin);
            pst.setInt(2, idin);
            re = pst.executeUpdate();
            return re;

        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    public int updatePro(Product p, Brand b ) {
        int re=0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "update products set name = ?, brandID =  ?, kind= ?, amount= ?, description=? where id =?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, p.getName());
            pst.setInt(2, b.getId());
            pst.setString(3, p.getKind());
            pst.setInt(4, p.getAmount());
            pst.setString(5, p.getDescription());
            pst.setInt(6, p.getId());
            re = pst.executeUpdate();
            return re;

        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    public int updateUnit(Product p,Unit u) {
        int re=0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "update units set color = ?, size =  ?, wattage  = ?, price  = ?, yearMade = ?, dateImport  = ?, amount =  ? where productID =?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, u.getColor());
            pst.setString(2, u.getSize());
            pst.setFloat(3, (float) u.getWattage());
            pst.setDouble(4, u.getPrice());
            pst.setInt(5, u.getYearMade());
            pst.setString(6, u.getDateImport().getDateInMonthDayYearSql());
            pst.setInt(7, u.getAmount());
            pst.setInt(8, p.getId());
            re = pst.executeUpdate();
            return re;

        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    public int updateProductUnit(Product p, Brand b, Unit u, ArrayList<Image> imgs) {
//        boolean isBrand = BrandDAO.getInstance().checkBrand(b);
//        int brandid=0;
//        if(!isBrand) {
//            brandid = BrandDAO.getInstance().insert(b);
//        } else {
//            brandid = BrandDAO.getInstance().selectByConditionn(b);
//        }
//        b.setId(brandid);
        updatePro(p,b);
        updateUnit(p,u);

        ImageDAO.getInstance().updateImage(p, imgs);


        return 0;
    }


//    them san pham
public int addPro(Product p) {
    return ProductDAO.getInstance().insert(p);
}

    public int addUnit(Unit u) {
        return UnitDAO.getInstance().insert(u);
    }

    public int  addImage(Image i){
        return  ImageDAO.getInstance().insert(i);
    }
    public int addProductUnit(Product p, Brand b, Unit u, Image i) {
        int re=0;
        boolean isBrand = BrandDAO.getInstance().checkBrand(b);
        int brandid=0;
        if(!isBrand) {
            BrandDAO.getInstance().insert(b);
            brandid = BrandDAO.getInstance().selectTheMaxID();
        } else {
            brandid = BrandDAO.getInstance().selectByConditionn(b);
        }
        b.setId(brandid);
        p.setBrandID(brandid);
        re+= addPro(p);
        re+= addUnit(u);
        re+= addImage(i);


        return re;
    }

    public int selectMaxID(){
        int res=-1;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select max(id) from products;";
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



    public static void main(String[] args) {
//        Brand b = new Brand("iphpne","my");
//        Product p = new Product(12,"test1",0,1,"a",100,"okok");
//        Unit u = new Unit(122,12,"","",0,2,100,2020,new Datee(2023,1,1),1);
//        Image i = new Image("fhwaeijvb",12);
//
//        System.out.println(ProductUnitDAO.getInstance().addProductUnit(p,b,u,i));

        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1015);
        ids.add(1017);
        System.out.println(ids.size());

//        System.out.println(ProductUnitDAO.getInstance().selectByIDs(ids).size());
        System.out.println(ProductUnitDAO.getInstance().selectById(1019));
    }

}
