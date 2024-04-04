package database;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import model.Brand;
import model.Image;
import model.Product;

public class ImageDAO implements IDAO<Image>{
	public static ImageDAO getInstance(){
		return new ImageDAO();
	}
	@Override
	public int insert(Image t) {
		// TODO Auto-generated method stub
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "insert into images values (?,?,?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setInt(1, t.getId());
			pst.setString(2, t.getUrl());
			pst.setInt(3, t.getParentID());

		
			re = pst.executeUpdate();

//			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return re;

		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public int update(Image t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Image t) {
		// TODO Auto-generated method stub
		return 0;
	}


	public ArrayList<Image> selectByParentID(int parentIDIn) {
		// TODO Auto-generated method stub
		ArrayList<Image> res = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "select * from images where parentID = ?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, parentIDIn);


			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				int id = rs.getInt("id");
				String url = rs.getString("url");
				int parentID = rs.getInt("parentID");

				res.add(new Image(id, url, parentID));
			}

			JDBCUtil.closeConnection(conn);
			return res;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<Image> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Image> res = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "select * from images;";
			PreparedStatement pst = conn.prepareStatement(sql);


			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				int id = rs.getInt("id");
				String url = rs.getString("url");
				int parentID = rs.getInt("parentID");
			
				res.add(new Image(id, url, parentID));
			}

			JDBCUtil.closeConnection(conn);
			return res;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Image selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Image> selectByCondition(Image t) {
		// TODO Auto-generated method stub
		return null;
	}

	public int selectTheMaxID(){
		int res=-1;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select max(id) from images;";
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
	
	
	
//	ham nay chi chay 1 lan duy nhat de nap du lieu len database
	public static void insertData() {
//		đọc dữ liệu từ file sản phẩm đã chuẩn bị sẵn 
//		xử lí để trả về một danh sách sản phẩm
//		sau đó đưa ds sản phẩm vào db
		File input = new File("D:\\code\\java\\N3-1\\LTrMang\\ltWeb\\src\\database\\dataltweb.txt");
		Scanner read =null;
		int stt=0;
		ArrayList<Image> imageList = new ArrayList<>();
		
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
		int count = 4;
		while(st.hasMoreTokens()) {
			String aProString = st.nextToken();
			String[] aProTokens = aProString.split("=");
			int id = Integer.parseInt("909" + count);
			String pname = aProTokens[5];
			int parentID = Integer.parseInt("101" + count);
	
			
			
			Image image = new Image(id, pname, parentID);
			imageList.add(image);
			count++;

		

		}
		
//		giờ thêm vào db

		int re = 0;
		for(Image i : imageList) {

//			System.out.println(i);
			re+=ImageDAO.getInstance().insert(i);
		}
		System.out.println(re + " dong da duoc them vao");
		
	}
	
	public static void main(String[] args) {

	}
	

}
