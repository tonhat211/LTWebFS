package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Brand;

public class BrandDAO implements IDAO<Brand>{
	public static BrandDAO getInstance(){
		return new BrandDAO();
	}
	@Override
	public int insert(Brand t) {
		// TODO Auto-generated method stub
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "insert into brands (name) values (?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getName());

			re = pst.executeUpdate();

			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return re;

		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public int update(Brand t) {
		// TODO Auto-generated method stub
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "update brands " +
					"set name=?, available=? " +
					"where id=?;";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, t.getName());
			pst.setInt(2, t.getAvailable());
			pst.setInt(3, t.getId());

			re = pst.executeUpdate();

			System.out.println(re + " dong da duoc cap nhat");
			JDBCUtil.closeConnection(conn);
			return re;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}




	}

	@Override
	public int delete(Brand t) {
		// TODO Auto-generated method stub
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "delete from brands where id=?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getId());

			re = pst.executeUpdate();

			System.out.println(re + " dong da duoc xoa");
			JDBCUtil.closeConnection(conn);
			return re;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public ArrayList<Brand> selectAll() {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		ArrayList<Brand> res = new ArrayList<Brand>();
=======
		ArrayList<Brand> res = new ArrayList<>();
>>>>>>> 73791a162573caca59402aecb658afd4910c4e02
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "select * from brands;";
			PreparedStatement pst = conn.prepareStatement(sql);


			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int available = rs.getInt("available");
				res.add(new Brand(id, name, available));
			}

//			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return res;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Brand selectById(int id) {
		// TODO Auto-generated method stub
		Brand res = null;
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "select * from brands where id=?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);


			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				int idd = rs.getInt("id");
				String name = rs.getString("name");
				int available = rs.getInt("available");
				res = new Brand(idd, name, available);
			}

//			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return res;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<Brand> selectByCondition(Brand t) {
		// TODO Auto-generated method stub
		return null;
	}

}
