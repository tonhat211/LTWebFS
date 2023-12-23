package database;

import model.Brand;

import java.sql.*;

public class test {
    public static void main(String[] args) throws SQLException {
        Connection c = null;

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String url = "jdbc:mySQL://localhost:3306/ltweb";
            String user = "root";
            String pwd ="";
            c = DriverManager.getConnection(url,user,pwd);
            // end connection

            String sql = "select * from brands;";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int available = rs.getInt("available");
                System.out.println(new Brand(id, name, available));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

}

