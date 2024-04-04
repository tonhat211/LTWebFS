package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Area;
import model.Brand;

public class AreaDAO implements IDAO<Area>{
	public static AreaDAO getInstance() {
		return new AreaDAO();
	}
	
	@Override
	public int insert(Area t) {
		// TODO Auto-generated method stub
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();
			
			String sql = "insert into areas (id, name) values (?,?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getId());
			pst.setString(2, t.getName());
			
			re = pst.executeUpdate();
			
			System.out.println(re + " dong da duoc them vao");
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
	public int update(Area t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Area t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Area> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Area selectById(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Area res = null;
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
				res = new Area(idd, name, available);
			}

//			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return res;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<Area> selectByCondition(Area t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		Area temp = new Area("Gia đình");
		AreaDAO.getInstance().insert(temp);
	}

}
