package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;

import model.Brand;
import model.VerifyCode;

public class VerifyCodeDAO implements IDAO<VerifyCode>{
	public static VerifyCodeDAO getInstance(){
		return new VerifyCodeDAO();
	}
	
	@Override
	public int insert(VerifyCode t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(VerifyCode t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(VerifyCode t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<VerifyCode> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VerifyCode selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<VerifyCode> selectByCondition(VerifyCode t) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertNewCode(String codee){
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();
			Random rs =new Random();


			String sql = "insert into verifycode (code) values (?);";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, codee);

			re = pst.executeUpdate();

			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return re;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Timestamp selectTheFirst(String codee) {
		Timestamp res = null;
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "select time from verifycode where code= ? ORDER by id asc LIMIT 1;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, codee);


			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				res= rs.getTimestamp("time");
			}

//			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return res;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Timestamp selectTheLast(String codee) {
		Timestamp res = null;
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "select time from verifycode where code= ? ORDER by id desc LIMIT 1;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, codee);


			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				res= rs.getTimestamp("time");
			}

//			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return res;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isVerifyOk(String codee){
		boolean res = false;
		Timestamp ts1 = VerifyCodeDAO.getInstance().selectTheFirst(codee);
		Timestamp ts2 = VerifyCodeDAO.getInstance().selectTheLast(codee);
		long timeDifferenceMillis = ts2.getTime() - ts1.getTime();

		// Chuyển đổi chênh lệch thời gian từ millisecs sang giây, phút, giờ, etc.
		long seconds = timeDifferenceMillis / 1000;
		long minutes = seconds / 60;
		if(minutes <=5){
			res=true;
		}
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println();
		Timestamp ts1 = VerifyCodeDAO.getInstance().selectTheFirst("4466");
		Timestamp ts2 = VerifyCodeDAO.getInstance().selectTheLast("4466");

		   long timeDifferenceMillis = ts2.getTime() - ts1.getTime();

	        // Chuyển đổi chênh lệch thời gian từ millisecs sang giây, phút, giờ, etc.
	        long seconds = timeDifferenceMillis / 1000;
	        long minutes = seconds / 60;
	        System.out.println(minutes);
		
	}
	

}
