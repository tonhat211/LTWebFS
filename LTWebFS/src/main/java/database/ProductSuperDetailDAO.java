package database;

import model.Product;
import model.ProductSuperDetail;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProductSuperDetailDAO implements IDAO<ProductSuperDetail>{
	public static ProductSuperDetailDAO getInstance() {
		return new ProductSuperDetailDAO();
	}

	@Override
	public int insert(ProductSuperDetail t) {
		// TODO Auto-generated method stub
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "insert into products (id, name, brandID, areaID, kind, description) values (?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setInt(1, t.getId());
//			pst.setString(2, t.getName());
//			pst.setInt(3, t.getBrandID());
//			pst.setInt(4, t.getAreaID());
//			pst.setString(5, t.getKind());
//			pst.setString(6, t.getDescription());

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
	public int update(ProductSuperDetail t) {
		// TODO Auto-generated method stub
		return 0;


	}

	@Override
	public int delete(ProductSuperDetail t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<ProductSuperDetail> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<ProductSuperDetail> ps = new ArrayList<>();
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
//				ps.add(new ProductSuperDetail(id,name, brandID, areaID, kind, des));

			}
			JDBCUtil.closeConnection(conn);
			return ps;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
	}


	public ArrayList<ProductSuperDetail> selectByName(String input) {
		// TODO Auto-generated method stub
		ArrayList<ProductSuperDetail> psds = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select u.productID, p.name, b.country, b.name, p.kind, a.name, u.price, u.size, u.amount, u.yearMade, u.dateImport, u.imei, p.description, i.url, u.available from products p join units u on p.id = u.productID join brands b on b.id = p.brandID join areas a on a.id = p.areaID join images i on i.parentID =p.id where p.name like ?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + input  +  "%");
			ResultSet rs = pst.executeQuery();

			int id=0;
			String name="";
			String country="";
			String brand="";
			String kind="";
			String area="";
			double price=0;
			String type="";
			int amount=0;
			int yearMade=0;
			String dateImport="";
			int imei=0;
			String des="";
			String  url="";
			int available=1;
			int count=0;
			int currentID=0;
			int nextID=0;

			while(rs.next()) {
				type = rs.getString("u.size");
				amount = rs.getInt("u.amount");
				url = rs.getString("i.url");
//				if(available==0) continue;
				available = rs.getInt("u.available");
//				if(id!=0) continue;
				id = rs.getInt("u.productID");
				name = rs.getString("p.name");
				kind = rs.getString("p.kind");
				country = rs.getString("b.country");
				brand = rs.getString("b.name");
				area = rs.getString("a.name");
				price = rs.getDouble("u.price");
				yearMade = rs.getInt("u.yearMade");
				dateImport = rs.getString("u.dateImport");
				imei = rs.getInt("u.imei");
				des = rs.getString("p.description");
				ProductSuperDetail psd = new ProductSuperDetail(id,name, country, brand,kind, area, price, type, amount,   yearMade, dateImport, imei, des, url, available);

				psds.add(psd);

			}




			JDBCUtil.closeConnection(conn);


			return psds;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}


	@Override
	public ProductSuperDetail selectById(int idin) {
		// TODO Auto-generated method stub

		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select u.productID, p.name, b.country, b.name, p.kind, a.name, u.price, u.size, u.amount, u.yearMade, u.dateImport, u.imei, p.description, i.url, u.available from products p join units u on p.id = u.productID join brands b on b.id = p.brandID join areas a on a.id = p.areaID join images i on i.parentID =p.id where u.productID = ?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, idin);
			ResultSet rs = pst.executeQuery();

			int id=0;
			String name="";
			String country="";
			String brand="";
			String kind="";
			String area="";
			double price=0;
			String type="";
			int amount=0;
			int yearMade=0;
			String dateImport="";
			int imei=0;
			String des="";
			String  url="";
			int available=1;
			int count=0;

			while(rs.next()) {
//				select u.productID, p.name, b.country, b.name, p.kind, a.name, u.price, u.size, u.amount, u.yearMade, u.dateImport, u.imei, p.description, i.url

				type += rs.getString("u.size") + "-";
				amount += rs.getInt("u.amount");
				url += rs.getString("i.url")+"==";
				if(available==0) continue;
				available = rs.getInt("u.available");
				if(id!=0) continue;
				id = rs.getInt("u.productID");
				name = rs.getString("p.name");
				kind = rs.getString("p.kind");
				country = rs.getString("b.country");
				brand = rs.getString("b.name");
				area = rs.getString("a.name");
				price = rs.getDouble("u.price");
				yearMade = rs.getInt("u.yearMade");
				dateImport = rs.getString("u.dateImport");
				imei = rs.getInt("u.imei");

				des = rs.getString("p.description");

			}
			type = type.substring(0, type.length()-1);
			url = url.substring(0, url.length()-2);
			ProductSuperDetail psd = new ProductSuperDetail(id,name, country, brand,kind, area, price, type, amount,   yearMade, dateImport, imei, des, url, available);
			JDBCUtil.closeConnection(conn);
			return psd;

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}


	}

	public ArrayList<ProductSuperDetail> selectByKind(String kindin) {
		// TODO Auto-generated method stub

		ArrayList<ProductSuperDetail> psds = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select u.productID, p.name, b.country, b.name, p.kind, a.name, u.price, u.size, u.amount, u.yearMade, u.dateImport, u.imei, p.description, i.url, u.available from products p join units u on p.id = u.productID join brands b on b.id = p.brandID join areas a on a.id = p.areaID join images i on i.parentID =p.id where p.kind = ?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, kindin);
			ResultSet rs = pst.executeQuery();

			int id=0;
			String name="";
			String country="";
			String brand="";
			String kind="";
			String area="";
			double price=0;
			String type="";
			int amount=0;
			int yearMade=0;
			String dateImport="";
			int imei=0;
			String des="";
			String  url="";
			int available=1;
			int count=0;
			int currentID=0;
			int nextID=0;

			while(rs.next()) {
				type = rs.getString("u.size");
				amount = rs.getInt("u.amount");
				url = rs.getString("i.url");
//				if(available==0) continue;
				available = rs.getInt("u.available");
//				if(id!=0) continue;
				id = rs.getInt("u.productID");
				name = rs.getString("p.name");
				kind = rs.getString("p.kind");
				country = rs.getString("b.country");
				brand = rs.getString("b.name");
				area = rs.getString("a.name");
				price = rs.getDouble("u.price");
				yearMade = rs.getInt("u.yearMade");
				dateImport = rs.getString("u.dateImport");
				imei = rs.getInt("u.imei");
				des = rs.getString("p.description");
				ProductSuperDetail psd = new ProductSuperDetail(id,name, country, brand,kind, area, price, type, amount,   yearMade, dateImport, imei, des, url, available);

				psds.add(psd);

			}


//				currentID = rs.getInt("u.productID");
//				if(!rs.next()) {
//					rs.previous();
//				}
//				nextID = rs.getInt("u.productID");
//				rs.previous();
//				if(currentID != nextID) {
//
//
////					id = rs.getInt("u.productID");
//
//					type = rs.getString("u.size");
//					amount = rs.getInt("u.amount");
//					url = rs.getString("i.url");
//					available = rs.getInt("u.available");
//					id = rs.getInt("u.productID");
//					name = rs.getString("p.name");
//					kind = rs.getString("p.kind");
//					country = rs.getString("b.country");
//					brand = rs.getString("b.name");
//					area = rs.getString("a.name");
//					price = rs.getDouble("u.price");
//					yearMade = rs.getInt("u.yearMade");
//					dateImport = rs.getString("u.dateImport");
//					imei = rs.getInt("u.imei");
//
//					des = rs.getString("p.description");
//					ProductSuperDetail psd = new ProductSuperDetail(id,name, country, brand,kind, area, price, type, amount,   yearMade, dateImport, imei, des, url, available);
//					psds.add(psd);
//				} else  {
//					rs.previous();
//					available = -1;
//					id=0;
//					while(rs.next()) {
////						select u.productID, p.name, b.country, b.name, p.kind, a.name, u.price, u.size, u.amount, u.yearMade, u.dateImport, u.imei, p.description, i.url
//						if(rs.getInt("u.productID")!=currentID) {
//							rs.previous();
//							break;
//						}
//						type += rs.getString("u.size") + "-";
//						amount += rs.getInt("u.amount");
//						url += rs.getString("i.url")+"==";
//						if(available==0) continue;
//						available = rs.getInt("u.available");
//						if(id!=0) continue;
//						id = rs.getInt("u.productID");
//						name = rs.getString("p.name");
//						kind = rs.getString("p.kind");
//						country = rs.getString("b.country");
//						brand = rs.getString("b.name");
//						area = rs.getString("a.name");
//						price = rs.getDouble("u.price");
//						yearMade = rs.getInt("u.yearMade");
//						dateImport = rs.getString("u.dateImport");
//						imei = rs.getInt("u.imei");
//
//						des = rs.getString("p.description");
//
//
//
//
//
//					}
//					type = type.substring(0, type.length()-1);
//					url = url.substring(0, url.length()-2);
//					ProductSuperDetail psd = new ProductSuperDetail(id,name, country, brand,kind, area, price, type, amount,   yearMade, dateImport, imei, des, url, available);
//					psds.add(psd);
//
//				}
//
//			}
//
//
////			}
////			type = type.substring(0, type.length()-1);
////			url = url.substring(0, url.length()-2);

			JDBCUtil.closeConnection(conn);


			return psds;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}


	@Override
	public ArrayList<ProductSuperDetail> selectByCondition(ProductSuperDetail t) {
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
//		for(Product p : proList) {
//			ProductSuperDetailDAO.getInstance().insert(p);
//		}
	}
	
	public static ArrayList<Product> getProByKind(String kind) {
		ArrayList<ProductSuperDetail> ps = ProductSuperDetailDAO.getInstance().selectAll();
		ArrayList<Product> res = new ArrayList<>();
//		for(ProductSuperDetail p : ps) {
//			if(p.getKind().equalsIgnoreCase(kind)) {
//				res.add(p);
//			}
//		}
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
		System.out.println(ProductSuperDetailDAO.getInstance().selectTheMaxID());
	}

}
