package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Brand;

public class BrandDAO implements IDAO<Brand>{
	public static BrandDAO getInstance(){
		return new BrandDAO();
	}
	@Override
	public int insert(Brand t) {
		// TODO Auto-generated method stub
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "insert into brands (name, country) values (?,?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, t.getName());
			pst.setString(2, t.getCountry());
//			pst.setString(1, "nhat");
		
			re = pst.executeUpdate();

			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return re;

		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public int update(Brand t) {
		// TODO Auto-generated method stub
		int re=0;

		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "update brands " +
					"set name=?, country=?, available=? " +
					"where id=?;";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, t.getName());
			pst.setString(2, t.getCountry());
			pst.setInt(3, t.getAvailable());
			pst.setInt(4, t.getId());

			re = pst.executeUpdate();

			System.out.println(re + " dong da duoc cap nhat");
			JDBCUtil.closeConnection(conn);
			return re;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}




	}

	@Override
	public int delete(Brand t) {
		// TODO Auto-generated method stub
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "delete from brands where id=?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getId());

			re = pst.executeUpdate();

			System.out.println(re + " dong da duoc xoa");
			JDBCUtil.closeConnection(conn);
			return re;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public ArrayList<Brand> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Brand> res = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "select * from brands;";
			PreparedStatement pst = conn.prepareStatement(sql);


			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String country = rs.getString("country");
				int available = rs.getInt("available");
				res.add(new Brand(id, name, country, available));
			}

//			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return res;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Brand selectById(int id) {
		// TODO Auto-generated method stub
		Brand res = null;
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "select * from brands where id=?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);


			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				int idd = rs.getInt("id");
				String name = rs.getString("name");
				String country = rs.getString("country");
				int available = rs.getInt("available");
				res = new Brand(idd, name,country, available);
			}

//			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return res;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<Brand> selectByCondition(Brand t) {
		// TODO Auto-generated method stub
		ArrayList<Brand> brands = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select * from brands where id = ? or name = ? or country = ? or available = ?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getId());
			pst.setString(2, t.getName());
			pst.setString(3, t.getCountry());
			pst.setInt(4, t.getAvailable());
			ResultSet rs  = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String country = rs.getString("country");
				int available = rs.getInt("avaible");
				brands.add(new Brand(id, name, country, available));
				
			}
			return brands;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}
	
	public static int updateID(int currentID, int newID) {
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "update brands set id= ? where id =?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, newID);
			pst.setInt(2, currentID);
			re = pst.executeUpdate();
			return re;
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public static ArrayList<String> selectAllContries(){
		ArrayList<String> res= new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select distinct country from brands";
			PreparedStatement pst = conn.prepareStatement(sql);

			ResultSet rs  = pst.executeQuery();
			while(rs.next()) {

				String country = rs.getString("country");

				res.add(country);

			}
			return res;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public ArrayList<Brand> selectBrandsByProductKind(String kind){
		ArrayList<Brand> brands = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "select * from brands where id in (select distinct brandID from products where kind = ?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, kind);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String country = rs.getString("country");
				int available = rs.getInt("available");
				brands.add(new Brand(id, name, country, available));

			}
			return brands;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}
	
	public static void insertData() {

      ArrayList<Brand> temps = new ArrayList<>();
      temps.add(new Brand("Wellmed", "Đức"));
      temps.add(new Brand("Boso", "Đức"));
      temps.add(new Brand("Spirit", "Đài loan"));
      temps.add(new Brand("Wellcare", "Đài loan"));
      temps.add(new Brand("Sturdy", "Đài Loan"));
      temps.add(new Brand("Philips", "Mỹ"));
      temps.add(new Brand("Enno", "Trung Quốc"));
      temps.add(new Brand("Dynmed", "Trung Quốc"));
      temps.add(new Brand("Roaman", "Trung Quốc"));
      temps.add(new Brand("Reiwa", "Nhật Bản"));
      temps.add(new Brand("Nakita", "Nhật Bản"));
      temps.add(new Brand("Fukuda", "Nhật Bản"));
      temps.add(new Brand("Armoline", "Thổ Nhĩ Kì"));     
      temps.add(new Brand("Zerone", "Hàn Quốc"));
      temps.add(new Brand("Changeui", "Hàn Quốc"));
      temps.add(new Brand("Daiwha", "Hàn Quốc"));
      temps.add(new Brand("Hilbro", "Pakistan"));
      temps.add(new Brand("Vinamed", "Việt Nam")); 
      temps.add(new Brand("Pharmacity", "Việt Nam"));
      for(Brand br : temps) {
      	BrandDAO.getInstance().insert(br);
      }
  	
    Brand br = new Brand("DrKare","Đài Loan");
      BrandDAO.getInstance().insert(br);
      System.out.println(BrandDAO.getInstance().selectById(1));
      ArrayList<Brand> res = BrandDAO.getInstance().selectAll();
      for(Brand b : res) {
      	System.out.println(b);
      }

	}
	
	public static void main(String[] args) {
//		insertData(); hàm này chỉ chạy 1 lần duy nhất để nhập db
//		ArrayList<Brand> brandList = BrandDAO.getInstance().selectAll();
//		String country="";
//		ArrayList<String> countries= new ArrayList<>();
//		for(Brand b : brandList) {
//			country = b.getCountry();
//			if(!countries.contains(country))
//				countries.add(country);
//		}
//		
//		for(String countr : countries) {
//			System.out.println(countr);
//		}
		
		
//		for(int i=4;i<=23;i++) {
//			int newID = Integer.parseInt("505" + i);
//			BrandDAO.getInstance().updateID(i, newID);
//		}
		
	}

}
