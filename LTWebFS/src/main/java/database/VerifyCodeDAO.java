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

	public int insertNewCode(String codee, String email){
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();
			Random rs =new Random();


			String sql = "insert into verifycode (code,email,isVerify) values (?,?,0);";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, codee);
			pst.setString(2, email);

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

	public int disableCode(String code) {
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "update verifycode set isVerify = 1 where code = ?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, code);
			re = pst.executeUpdate();
			return re;

		} catch (SQLException e) {
			// TODO: handle exception
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

	public VerifyCode selectTheLastCode(String emailin) {
		VerifyCode res = null;
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "select * from verifycode where email = ? ORDER by id desc LIMIT 1;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, emailin);


			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				String code = rs.getString("code");
				String email = rs.getString("email");
				Timestamp time = rs.getTimestamp("time");
				int isVerify = rs.getInt("isVerify");

				res = new VerifyCode(code,email,time,isVerify);
			}

//			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return res;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public boolean isVerifyOk(String codein, String emailin){
		boolean res = false;
		VerifyCode code = VerifyCodeDAO.getInstance().selectTheLastCode(emailin);
		if(code == null) {
			return false;
		}
		if(!codein.equals(code.getCode())){
			return false;
		}
		if (code.getIsVerify() > 0) {
			return false;
		}
		long codeTime = code.getTime().getTime();
		long currentTimestamp = System.currentTimeMillis();
		long timeDifferenceMillis = currentTimestamp - codeTime;


		long seconds = timeDifferenceMillis / 1000;
		if(seconds > 0 && seconds < 300 ) {
			VerifyCodeDAO.getInstance().disableCode(codein);
			return true;
		}

		return false;
	}
	
	public static void main(String[] args) {

		System.out.println(VerifyCodeDAO.getInstance().isVerifyOk("1111","no665ok@gmail.com"));
//		System.out.println(ts1);
//		ts1 = ts1.getTime();
		
	}
	

}
