package database;

import java.beans.BeanDescriptor;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.mysql.cj.protocol.PacketReceivedTimeHolder;
import com.mysql.cj.protocol.Resultset;

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
			String sql = "insert into products (name, brandID, areaID, kind, description) values (?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getName());
			pst.setInt(2, t.getBrandID());
			pst.setInt(3, t.getAreaID());
			pst.setString(4, t.getKind());
			pst.setString(5, t.getDescription());
			
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
		return 0;
	}

	@Override
	public int delete(Product t) {
		// TODO Auto-generated method stub
		return 0;
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
	public static void main(String[] args) {
//		insertData(); hàm này chỉ chạy 1 lần duy nhất để tải dữ liệu lên db
		
//		ArrayList<Product> temps = getProByKind("c");
//		for(Product p : temps) {
//			System.out.println(p);
//		}
//		int count=0;
//		for(int i=4;i<=203;i++) {
//			int newID = Integer.parseInt("101" + i);
//			
//			count += ProductDAO.getInstance().updateID(i, newID);
//		
//		}
//		System.out.println("so luot update: " + count);
	}

}
