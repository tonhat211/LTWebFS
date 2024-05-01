package database;

import model.Branch;
import model.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BranchDAO implements IDAO<Branch>{
	public static BranchDAO getInstance(){
		return new BranchDAO();
	}


	@Override
	public int insert(Branch branch) {
		return 0;
	}

	@Override
	public int update(Branch branch) {
		return 0;
	}

	@Override
	public int delete(Branch branch) {
		return 0;
	}

	@Override
	public ArrayList<Branch> selectAll() {
// TODO Auto-generated method stub
		ArrayList<Branch> res = new ArrayList<Branch>();
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "select * from branches;";
			PreparedStatement pst = conn.prepareStatement(sql);


			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				int available = rs.getInt("available");
				res.add(new Branch(id, name, address,phone, available));
			}

//			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return res;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Branch selectById(int id) {
		return null;
	}

	@Override
	public ArrayList<Branch> selectByCondition(Branch branch) {
		return null;
	}

	public static void main(String[] args) {
		System.out.println(BranchDAO.getInstance().selectAll());
	}
}
