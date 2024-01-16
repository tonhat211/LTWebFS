package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Area;

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
			
			String sql = "insert into areas (name) values (?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, t.getName());
			
			re = pst.executeUpdate();
			
			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return re;
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		

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
		return null;
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
