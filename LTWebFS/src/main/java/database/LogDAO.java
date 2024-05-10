package database;

import model.Area;
import model.Log;

import java.sql.*;
import java.util.ArrayList;

public class LogDAO implements IDAO<Log>{
	public static LogDAO getInstance() {
		return new LogDAO();
	}
	
	@Override
	public int insert(Log log) {
		int re= -1;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "insert into logs (ip, info, action, preValue, afterValue, level) values (?,?,?,?,?,?); ";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,log.getIp());
			pst.setString(2,log.getInfo());
			pst.setString(3,log.getAction());
			pst.setString(4,log.getPreValue());
			pst.setString(5,log.getAfterValue());
			pst.setInt(6,log.getLevel());
			re = pst.executeUpdate();
			JDBCUtil.closeConnection(conn);
			return re;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int selectCountTotal() {
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select count(*) from logs;";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				re = rs.getInt("count(*)");

			}
			JDBCUtil.closeConnection(conn);
			return re;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public int selectTheMaxID(){
		int res=-1;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select max(id) from areas;";
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

	public ArrayList<Log> selectSome(int index, int limit) {
		ArrayList<Log> res = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select id, ip, info, action, preValue, afterValue, level, time from logs limit ?, ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,index);
			pst.setInt(2,limit);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String ip = rs.getString("ip");
				String info = rs.getString("info");
				String action = rs.getString("action");
				String preValue = rs.getString("preValue");
				String afterValue = rs.getString("afterValue");
				int level = rs.getInt("level");
				Timestamp time = rs.getTimestamp("time");
				res.add(new Log(id,ip,info,action,preValue,afterValue,level,time));
			}
			JDBCUtil.closeConnection(conn);
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Area selectByName(String namein) {
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select * from areas where name = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, namein);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int available =  rs.getInt("available");
				return new Area(id,name,available);

			}
			JDBCUtil.closeConnection(conn);

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return null;

	}

	@Override
	public int update(Log t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Log t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Log> selectAll() {
		ArrayList<Log> res = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select id, ip, info, action, preValue, afterValue, level, time from logs";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String ip = rs.getString("ip");
				String info = rs.getString("info");
				String action = rs.getString("action");
				String preValue = rs.getString("preValue");
				String afterValue = rs.getString("afterValue");
				int level = rs.getInt("level");
				Timestamp time = rs.getTimestamp("time");
				res.add(new Log(id,ip,info,action,preValue,afterValue,level,time));

			}
			JDBCUtil.closeConnection(conn);
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Log selectById(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Log res = null;
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "select * from areas where id=?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				int idd = rs.getInt("id");
				String name = rs.getString("name");
				int available = rs.getInt("available");
//				res = new Area(idd, name, available);
			}

//			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return res;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<Log> selectByCondition(Log t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
//		Log t = new Log("123","uc",3031,"Awfqi@gmail.com",0,"dang nhap");
//		System.out.println(LogDAO.getInstance().insert(t));


	}

}
