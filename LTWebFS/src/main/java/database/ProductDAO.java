package database;

import java.beans.BeanDescriptor;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
//
//import com.mysql.cj.protocol.PacketReceivedTimeHolder;
//import com.mysql.cj.protocol.Resultset;

import model.Brand;
import model.Product;

public class ProductDAO implements IDAO<Product>{
	public static ProductDAO getInstance() {
		return new ProductDAO();
	}

	@Override
	public int insert(Product t) {
		// TODO Auto-generated method stub
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "insert into products (id, name, brandID, areaID, kind, amount, description) values (?,?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getId());
			pst.setString(2, t.getName());
			pst.setInt(3, t.getBrandID());
			pst.setInt(4, t.getAreaID());
			pst.setString(5, t.getKind());
			pst.setInt(6, t.getAmount());
			pst.setString(7, t.getDescription());
			
			re = pst.executeUpdate();
			System.out.println(re + " dong da duoc them");
			JDBCUtil.closeConnection(conn);
			
			
			
			return re;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public int update(Product t) {
		// TODO Auto-generated method stub
//		int re=0;
//		try {
//			Connection conn = JDBCUtil.getConnection();
//			String sql = "update products set amount = ? where id =?;";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			Random rand = new Random();
//			int ranNum = rand.nextInt(300)+1;
//
//			pst.setInt(1, ranNum);
//			pst.setInt(2, currentID);
//			re = pst.executeUpdate();
//			return re;
//
//		} catch (SQLException e) {
//			// TODO: handle exception
//			throw new RuntimeException(e);
//		}
		return 0;
	}

	public int update(String kind, int amount) {
		// TODO Auto-generated method stub
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "update products set amount = ? where kind =?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, amount);
			pst.setString(2, kind);
			re = pst.executeUpdate();
			return re;

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}
	@Override
	public int delete(Product t) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "delete from products where id=?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getId());


			re = pst.executeUpdate();
			System.out.println(re + " dong da duoc xoa");
			JDBCUtil.closeConnection(conn);
			return re;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<Product> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Product> ps = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select * from products;";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int brandID = rs.getInt("brandID");
				int areaID = rs.getInt("areaID");
				String kind = rs.getString("kind");
				String des = rs.getString("description");
				ps.add(new Product(id,name, brandID, areaID, kind, des));
				
			}
			JDBCUtil.closeConnection(conn);
			return ps;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
	}

	public ArrayList<Product> selectByKind(String kindin) {
		// TODO Auto-generated method stub
		ArrayList<Product> ps = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select * from products where kind = ?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,kindin);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int brandID = rs.getInt("brandID");
				int areaID = rs.getInt("areaID");
				String kind = rs.getString("kind");
				int amount = rs.getInt("amount");
				String des = rs.getString("description");
				ps.add(new Product(id,name, brandID, areaID, kind, amount, des));

			}
			JDBCUtil.closeConnection(conn);
			return ps;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	public ArrayList<Product> selectByName(String input) {
		// TODO Auto-generated method stub
		ArrayList<Product> ps = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select * from products where name like ?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,"%"  + input + "%");
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int brandID = rs.getInt("brandID");
				int areaID = rs.getInt("areaID");
				String kind = rs.getString("kind");
				String des = rs.getString("description");
				ps.add(new Product(id,name, brandID, areaID, kind, des));

			}
			JDBCUtil.closeConnection(conn);
			return ps;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	public ArrayList<Integer> selectIDsByKind(String kindin) {
		// TODO Auto-generated method stub
		ArrayList<Integer> re = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select id from products where kind like ?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,kindin);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				re.add(id);

			}
			JDBCUtil.closeConnection(conn);
			return re;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}


	@Override
	public Product selectById(int idin) {
		// TODO Auto-generated method stub

		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select * from products where id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, idin);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int brandID = rs.getInt("brandID");
				int areaID = rs.getInt("areaID");
				String kind = rs.getString("kind");
				String des = rs.getString("description");
				return new Product(id,name, brandID, areaID, kind, des);

			}
			JDBCUtil.closeConnection(conn);

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return null;

	}

	@Override
	public ArrayList<Product> selectByCondition(Product t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ArrayList<Product> selectByBrandID(ArrayList<Product> ps, ArrayList<Integer> ids){
		ArrayList<Product> res = new ArrayList<>();
		for(Product p : ps) {
			if(ids.contains(p.getBrandID())) res.add(p);
		}
		return res;
	}
	
	public static void insertData() {
//		đọc dữ liệu từ file sản phẩm đã chuẩn bị sẵn 
//		xử lí để trả về một danh sách sản phẩm
//		sau đó đưa ds sản phẩm vào db
		File input = new File("D:\\code\\java\\N3-1\\LTrMang\\ltWeb\\src\\database\\dataltweb.txt");
		Scanner read =null;
		int stt=0;
		ArrayList<Product> proList = new ArrayList<>();
		
		try {
			read = new Scanner(input);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ko mo duoc tap tin");
			return;
		}
		
		String dataString = "";
		while(read.hasNextLine()) {
			dataString += read.nextLine();
		}
		read.close();
		System.out.println(dataString);
		StringTokenizer st = new StringTokenizer(dataString, "|");
		
		while(st.hasMoreTokens()) {
			String aProString = st.nextToken();
			String[] aProTokens = aProString.split("=");
			String pname = aProTokens[0];
			int pBrand = Integer.parseInt( aProTokens[1]);
			int pArea = Integer.parseInt( aProTokens[2]);
			String pKind = aProTokens[3];
			String pDes = aProTokens[4];
			
			Product pr = new Product(pname, pBrand, pArea, pKind, pDes);
			proList.add(pr);

		

		}
		
//		giờ thêm vào db
		for(Product p : proList) {
			ProductDAO.getInstance().insert(p);
		}
	}
	
	public static ArrayList<Product> getProByKind(String kind) {
		ArrayList<Product> ps = ProductDAO.getInstance().selectAll();
		ArrayList<Product> res = new ArrayList<>();
		for(Product p : ps) {
			if(p.getKind().equalsIgnoreCase(kind)) {
				res.add(p);
			}
		}
		return res;
	}
	
	public static int updateID(int currentID, int newID) {
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "update products set id= ? where id =?;";
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

	public ArrayList<Product> selectProsByNameAndKind(String kindin, String namein){
		ArrayList<Product> ps = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select * from products where kind = ? and name like ?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,kindin);
			pst.setString(2,"%" + namein + "%");
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int brandID = rs.getInt("brandID");
				int areaID = rs.getInt("areaID");
				String kind = rs.getString("kind");
				String des = rs.getString("description");
				ps.add(new Product(id,name, brandID, areaID, kind, des));

			}
			JDBCUtil.closeConnection(conn);
			return ps;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}
	public int insert2(int idin, Product t) {
		// TODO Auto-generated method stub
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "insert into products (id, name, brandID, areaID, kind, description) values (?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, idin);
			pst.setString(2, t.getName());
			pst.setInt(3, t.getBrandID());
			pst.setInt(4, t.getAreaID());
			pst.setString(5, t.getKind());
			pst.setString(6, t.getDescription());

			re = pst.executeUpdate();
			System.out.println(re + " dong da duoc them");
			JDBCUtil.closeConnection(conn);



			return re;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}



	public int selectTheMaxID(){
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

		System.out.println(ProductDAO.getInstance().update("D", 100));

	}

}
