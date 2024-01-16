package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import model.Datee;
import model.Product;
import model.Unit;

public class UnitDAO implements IDAO<Unit>{
	public static UnitDAO getInstance() {
		return new UnitDAO();
	}

	@Override
	public int insert(Unit t) {
		// TODO Auto-generated method stub
		int re  = 0;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql =  "insert into units values (?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getImei());
			pst.setInt(2, t.getProductID());
			pst.setString(3, t.getColor());
			pst.setString(4, t.getSize());
			pst.setDouble(5, t.getWattage());
			pst.setDouble(6, t.getPrice());
			pst.setInt(7, t.getAmount());
			pst.setInt(8, t.getYearMade());
	        // Chuỗi đại diện cho ngày
	        String dateString = t.getDateImport().toString();

	        // Định dạng của chuỗi ngày
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	        try {
	            // Phân tích chuỗi ngày thành đối tượng Date
	            java.util.Date utilDate = dateFormat.parse(dateString);

	            // Lấy giá trị thời gian từ đối tượng Date
	            long timeInMillis = utilDate.getTime();

	            // Tạo đối tượng java.sql.Date từ giá trị thời gian
	            java.sql.Date sqlDate = new java.sql.Date(timeInMillis);
	            pst.setDate(9, sqlDate);
	        }catch (Exception e) {
				// TODO: handle exception
	        	e.printStackTrace();
			}

			
			pst.setInt(10, t.getAvailable());
			
			re= pst.executeUpdate();
			JDBCUtil.closeConnection(conn);
			return re;
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int update(Unit t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Unit t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Unit> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Unit> uList = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select * from units;";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int imei = rs.getInt("imei");
				int proID = rs.getInt("productID");
				String color = rs.getString("color");
				String size = rs.getString("size");
				double wattage = rs.getDouble("wattage");
				double price = rs.getDouble("price");
				int amount = rs.getInt("amount");
				int yearMade = rs.getInt("yearMade");
				Date dateSql = rs.getDate("dateImport");
				Datee dateImport = new Datee(dateSql);
				int available = rs.getInt("available");
				
				Unit u = new Unit(imei, proID, color, size, wattage, price, amount, yearMade, dateImport, available);
				uList.add(u);
			
			}
			JDBCUtil.closeConnection(conn);
			return uList;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	
	}

	@Override
	public Unit selectById(int id) {
		// TODO Auto-generated method stub
		Unit res = null;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select * from units where productID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
		
			while(rs.next()) {
				int imei = rs.getInt("imei");
				int proID = rs.getInt("productID");
				String color = rs.getString("color");
				String size = rs.getString("size");
				double wattage = rs.getDouble("wattage");
				double price = rs.getDouble("price");
				int amount = rs.getInt("amount");
				int yearMade = rs.getInt("yearMade");
				Date dateSql = rs.getDate("dateImport");
				Datee dateImport = new Datee(dateSql);
				int available = rs.getInt("available");
				
				res =  new Unit(imei, proID, color, size, wattage, price, amount, yearMade, dateImport, available);
				
			
			}
			JDBCUtil.closeConnection(conn);
			return res;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	
	}

	public ArrayList<Unit> selectByProId(int id) {
		// TODO Auto-generated method stub
		ArrayList<Unit>  res = new ArrayList<>();
//		Unit res = null;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select * from units where productID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				int imei = rs.getInt("imei");
				int proID = rs.getInt("productID");
				String color = rs.getString("color");
				String size = rs.getString("size");
				double wattage = rs.getDouble("wattage");
				double price = rs.getDouble("price");
				int amount = rs.getInt("amount");
				int yearMade = rs.getInt("yearMade");
				Date dateSql = rs.getDate("dateImport");
				Datee dateImport = new Datee(dateSql);
				int available = rs.getInt("available");

				res.add(new Unit(imei, proID, color, size, wattage, price, amount, yearMade, dateImport, available));


			}
			JDBCUtil.closeConnection(conn);
			return res;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	@Override
	public ArrayList<Unit> selectByCondition(Unit t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
//	ham nay chi chay 1 lan duy nhat de nap du lieu vao db
	public void insertData(){
//		đọc dữ liệu từ file sản phẩm đã chuẩn bị sẵn 
//		xử lí để trả về một danh sách sản phẩm
//		sau đó đưa ds sản phẩm vào db
		File input = new File("D:\\code\\java\\N3-1\\LTrMang\\ltWeb\\src\\database\\dataltweb.txt");
		Scanner read =null;
		int stt=0;
		
		ArrayList<Unit> unitList = new ArrayList<>();
		
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
//		System.out.println(dataString);
		StringTokenizer st = new StringTokenizer(dataString, "|");
		int countImei=1;
		int countProID = 4;

		String testt="";
		
		while(st.hasMoreTokens()) {
			String aUnitString = st.nextToken();
			String[] aUnitTokens = aUnitString.split("=");
			
//			xu ly unit
			String sizeTemp = aUnitTokens[7];
			String[] sizeTokens = sizeTemp.split("-");
			if(sizeTokens.length==1) {
				int uImei = Integer.parseInt("202" + countImei);
				int uProID = Integer.parseInt("101" + countProID);
				countImei++;
				countProID++;
			
				String color = null;
				String size = sizeTokens[0];
				double wattage = 0.0;
				
//				xu li price
				String priceString = aUnitTokens[6];
				int k = priceString.indexOf('k');
				double price = Double.parseDouble(priceString.substring(0, k)+"000");
				
//				xu ly so luong
				int amounts[] = {100,200,300,400,500,10,20,40,60};
				int rd = new Random().nextInt(amounts.length);
				int amount = amounts[rd];
					
//				xu ly year made
				int years[] = {2017,2018,2019,2020,2021,2022,2023};
				int year = years[new Random().nextInt(years.length)];
				
//				xu ly date import
				int month = new Random().nextInt(12)+1;
				int day=1;
				if(month == 2) {
					day = new Random().nextInt(28)+1;
				} else if (month ==1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 )
					day = new Random().nextInt(31)+1;
				 else if (month ==4 || month == 6 || month == 9 || month == 11)
					 day = new Random().nextInt(30)+1;
				
				Datee d = new Datee(2023, month, day); 
				int available = 1;
				
				Unit u = new Unit(uImei, uProID, color, size, wattage, price, amount, year, d, available);
				unitList.add(u);
//				testt+= u.toString() +"\n";
			} else {
				for(int i =0; i<sizeTokens.length;i++) {
					int uImei = Integer.parseInt("202" + countImei);
					countImei++;
					int uProID = Integer.parseInt("101" + countProID);
					String color = null;
					String size = sizeTokens[i];
					Float wattage = 0.f;
				

//					xu li price
					String priceString = aUnitTokens[6];
					int k = priceString.indexOf('k');
					float price = Float.parseFloat(priceString.substring(0, k)+"000");
					
//					xu ly so luong
					int amounts[] = {100,200,300,400,500,10,20,40,60};
					int rd = new Random().nextInt(amounts.length);
					int amount = amounts[rd];
					
//					xu ly year made
					int years[] = {2017,2018,2019,2020,2021,2022,2023};
					int year = years[new Random().nextInt(years.length)];
					
//					xu ly date import
					int month = new Random().nextInt(12)+1;
					int day=1;
					if(month == 2) {
						day = new Random().nextInt(28)+1;
					} else if (month ==1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 )
						day = new Random().nextInt(31)+1;
					 else if (month ==4 || month == 6 || month == 9 || month == 11)
						 day = new Random().nextInt(30)+1;
					
					Datee d = new Datee(2023, month, day); 
					int available = 1;
					Unit u = new Unit(uImei, uProID, color, size, wattage, price, amount, year, d, available);
					unitList.add(u);
//					testt+= u.toString() +"\n";
					
					
				}
				countProID++;
			
				
			}
			
		}
		int count=0;
		for(Unit u : unitList) {
			count += UnitDAO.getInstance().insert(u);
			
		}
		System.out.println("so row da them: " + count);
	}
	
	public static void main(String[] args) {
//		ArrayList<Unit> us =  UnitDAO.getInstance().selectAll();
//		for(Unit u : us) {
//			System.out.println(u);
//		}
		System.out.println(UnitDAO.getInstance().selectById(1014));

	}

}
