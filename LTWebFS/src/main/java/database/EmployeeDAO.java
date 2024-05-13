package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO implements IDAO<Employee> {
    public static EmployeeDAO getInstance() {
        return new EmployeeDAO();
    }


    @Override
    public int insert(Employee employee) {
        int re=0;
        try {
            Connection conn = JDBCUtil.getConnection();

            String sql = "insert into users values (?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, employee.getId());
            pst.setString(2, employee.getName());
            pst.setString(3, employee.getEmail());
            pst.setString(4, employee.getPwd());
            pst.setInt(5, employee.getLevel());
            pst.setString(6, employee.getPhone());
            pst.setString(7, employee.getAddress());
            pst.setInt(8, employee.getBranchID());
            pst.setString(9, employee.getInfo());
            pst.setString(10, employee.getDatein().getDateInMonthDayYearSql());
            pst.setString(11, null);
            pst.setInt(12, employee.getAvailable());
            pst.setString(13, employee.getRole());

            re = pst.executeUpdate();

//            System.out.println(re + " dong da duoc them vao");
            JDBCUtil.closeConnection(conn);
            return employee.getId();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Employee e) {
        int re=0;
        try {

            Connection conn = JDBCUtil.getConnection();
            String sql = "update users set name = ?, email = ?, phone = ?, address = ?, branchID = ?, info =?, datein = ?, role = ? where id =?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,e.getName());
            pst.setString(2,e.getEmail());
            pst.setString(3,e.getPhone());
            pst.setString(4,e.getAddress());
            pst.setInt(5,e.getBranchID());
            pst.setString(6,e.getInfo());
            pst.setString(7,e.getDatein().getDateInMonthDayYearSql());
            pst.setString(8,e.getRole());
            pst.setInt(9, e.getId());
            re = pst.executeUpdate();
            return re;

        } catch (SQLException ex) {
            // TODO: handle exception
            throw new RuntimeException(ex);
        }
    }

    public int updateEmployee(Employee e, Image i) {
        int re= 0;
        re+= this.update(e);
        re+= ImageDAO.getInstance().update(i);
        return re;
    }

    public int insertEmployee(Employee e, Image i) {
        ImageDAO.getInstance().insert(i);
        return this.insert(e);
    }

    @Override
    public int delete(Employee employee) {
        return 0;
    }

    @Override
    public ArrayList<Employee> selectAll() {
        ArrayList<Employee> res = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select u.id as uid, u.name as uname, u.email as uemail, u.pwd as upwd, " +
                    "u.level as ulevel, u.phone as uphone, u.address as uaddress, " +
                    "u.branchID as branchID, u.info as uinfo, " +
                    "u.dateIn as udatein, u.dateOut as udateout, u.available as uavailable, " +
                    "b.name as bname, i.id as imgid, i.url as imgurl, u.role as urole from users u join branches b on u.branchID = b.id join images i on i.parentID = u.id where u.level > 0 order by u.id asc;";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                int id = rs.getInt("uid");
                String name = rs.getString("uname");
                String email = rs.getString("uemail");
                String pwd = rs.getString("upwd");
                int level =  rs.getInt("ulevel");
                String phone = rs.getString("uphone");
                String address  = rs.getString("uaddress");
                int banchID = rs.getInt("branchID");
                String  info = rs.getString("uinfo");
                Date dateInSql = rs.getDate("udateIn");
                Datee dateIn = new Datee(dateInSql);
                Datee dateOut;
                Date dateOutSql = rs.getDate("udateOut");
                if(dateOutSql!=null){
                    dateOut = new Datee(dateOutSql);
                } else
                    dateOut = null;
                int available= rs.getInt("uavailable");
                String bname = rs.getString("bname");
                int imgid= rs.getInt("imgid");
                String imgurl= rs.getString("imgurl");
                String role= rs.getString("urole");

                Employee e = new Employee(id,name,email,pwd,level,phone,address,banchID,bname,info,dateIn,dateOut,available,imgid,imgurl,role);

                res.add(e);

            }
            JDBCUtil.closeConnection(conn);
            return res;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }

    }



    @Override
    public Employee selectById(int idin) {
//        Employee res = new Employee();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select u.id as uid, u.name as uname, u.email as uemail, u.pwd as upwd, " +
                    "u.level as ulevel, u.phone as uphone, u.address as uaddress, " +
                    "u.branchID as branchID, u.info as uinfo, " +
                    "u.dateIn as udatein, u.dateOut as udateout, u.available as uavailable, " +
                    "b.name as bname, i.id as imgid, i.url as imgurl, u.role as urole from users u join branches b on u.branchID = b.id join images i on i.parentID = u.id where u.id = ? and u.level > 0 order by u.id asc;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,idin);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                int id = rs.getInt("uid");
                String name = rs.getString("uname");
                String email = rs.getString("uemail");
                String pwd = rs.getString("upwd");
                int level =  rs.getInt("ulevel");
                String phone = rs.getString("uphone");
                String address  = rs.getString("uaddress");
                int banchID = rs.getInt("branchID");
                String  info = rs.getString("uinfo");
                Date dateInSql = rs.getDate("udateIn");
                Datee dateIn = new Datee(dateInSql);
                Datee dateOut;
                Date dateOutSql = rs.getDate("udateOut");
                if(dateOutSql!=null){
                    dateOut = new Datee(dateOutSql);
                } else
                    dateOut = null;
                int available= rs.getInt("uavailable");
                String bname = rs.getString("bname");
                int imgid= rs.getInt("imgid");
                String imgurl= rs.getString("imgurl");
                String role= rs.getString("urole");


                JDBCUtil.closeConnection(conn);
                return new Employee(id,name,email,pwd,level,phone,address,banchID,bname,info,dateIn,dateOut,available,imgid,imgurl,role);



            }

            return null;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    public int updateAvailableByEmployID(int idin, int avaiin) {
        int re=0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "update users set available = ? where id =?;";
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

    public ArrayList<Employee> selectByNameOrEmailOrPhone(String input) {
        ArrayList<Employee> res = new ArrayList<>();
//        ArrayList<Integer> idAdded  = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select u.id as uid, u.name as uname, u.email as uemail, u.pwd as upwd, " +
                    "u.level as ulevel, u.phone as uphone, u.address as uaddress, " +
                    "u.branchID as branchID, u.info as uinfo, " +
                    "u.dateIn as udatein, u.dateOut as udateout, u.available as uavailable, " +
                    "b.name as bname, i.id as imgid, i.url as imgurl, u.role as urole from users u join branches b on u.branchID = b.id join images i on i.parentID = u.id where (u.name like ? or u.phone like ? or u.email like  ? or b.name like ? or u.id like ?) and u.level>0 order by u.id asc;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,"%"  + input + "%");
            pst.setString(2,"%"  + input + "%");
            pst.setString(3,"%"  + input + "%");
            pst.setString(4,"%"  + input + "%");
            pst.setString(5,"%"  + input + "%");
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                int id = rs.getInt("uid");
                String name = rs.getString("uname");
                String email = rs.getString("uemail");
                String pwd = rs.getString("upwd");
                int level =  rs.getInt("ulevel");
                String phone = rs.getString("uphone");
                String address  = rs.getString("uaddress");
                int banchID = rs.getInt("branchID");
                String  info = rs.getString("uinfo");
                Date dateInSql = rs.getDate("udateIn");
                Datee dateIn = new Datee(dateInSql);
                Datee dateOut;
                Date dateOutSql = rs.getDate("udateOut");
                if(dateOutSql!=null){
                    dateOut = new Datee(dateOutSql);
                } else
                    dateOut = null;
                int available= rs.getInt("uavailable");
                String bname = rs.getString("bname");
                int imgid= rs.getInt("imgid");
                String imgurl= rs.getString("imgurl");
                String role= rs.getString("urole");

                Employee e = new Employee(id,name,email,pwd,level,phone,address,banchID,bname,info,dateIn,dateOut,available,imgid,imgurl,role);
                res.add(e);


            }
            JDBCUtil.closeConnection(conn);
            return res;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Employee> selectByCondition(Employee employee) {
        return null;
    }

    public static void main(String[] args) {

//        System.out.println(EmployeeDAO.getInstance().selectByNameOrEmailOrPhone("hcm"));
        System.out.println(EmployeeDAO.getInstance().selectById(3035));
        Employee e = EmployeeDAO.getInstance().selectById(3035);
        e.setName("too minhh nhatt");
        System.out.println(EmployeeDAO.getInstance().update(e));

    }
}