package database;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DecartDAO implements IDAO<cartitem>{
	public static DecartDAO getInstance(){
		return new DecartDAO();
	}
	@Override
	public int insert(cartitem t) {
		// TODO Auto-generated method stub
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "insert into de_carts (idCart,  idProduct, qty) values (?,?,?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getId());
			pst.setInt(2, t.getProID());
			pst.setInt(3, t.getQty());


			re = pst.executeUpdate();

			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return t.getId();

		} catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

	public int insertCart(int idin) {
		// TODO Auto-generated method stub
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "insert into carts (id,  idUser) values (?,?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, idin);
			pst.setInt(2, idin);


			re = pst.executeUpdate();

			System.out.println(re + " dong da duoc them vao");
			JDBCUtil.closeConnection(conn);
			return idin;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public int update(cartitem t) {
//		// TODO Auto-generated method stub
//		int re=0;
//
//		try {
//			Connection conn = JDBCUtil.getConnection();
//
//			String sql = "update brands " +
//					"set name=?, country=?, available=? " +
//					"where id=?;";
//			PreparedStatement pst = conn.prepareStatement(sql);
//
//			pst.setString(1, t.getName());
//			pst.setString(2, t.getCountry());
//			pst.setInt(3, t.getAvailable());
//			pst.setInt(4, t.getId());
//
//			re = pst.executeUpdate();
//
//			System.out.println(re + " dong da duoc cap nhat");
//			JDBCUtil.closeConnection(conn);
//			return re;
//
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
	return 0;



	}

	@Override
	public int delete(cartitem t) {
//		// TODO Auto-generated method stub
//		int re=0;
//		try {
//			Connection conn = JDBCUtil.getConnection();
//
//			String sql = "delete from brands where id=?;";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setInt(1, t.getId());
//
//			re = pst.executeUpdate();
//
//			System.out.println(re + " dong da duoc xoa");
//			JDBCUtil.closeConnection(conn);
//			return re;
//
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
		return 0;

	}

	@Override
	public ArrayList<cartitem> selectAll() {
		// TODO Auto-generated method stub
//		ArrayList<Brand> res = new ArrayList<Brand>();
//		try {
//			Connection conn = JDBCUtil.getConnection();
//
//			String sql = "select * from brands;";
//			PreparedStatement pst = conn.prepareStatement(sql);
//
//
//			ResultSet rs = pst.executeQuery();
//
//			while(rs.next()){
//				int id = rs.getInt("id");
//				String name = rs.getString("name");
//				String country = rs.getString("country");
//				int available = rs.getInt("available");
//				res.add(new Brand(id, name, country, available));
//			}
//
////			System.out.println(re + " dong da duoc them vao");
//			JDBCUtil.closeConnection(conn);
//			return res;
//
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
		return null;
	}

	@Override
	public cartitem selectById(int id) {
		// TODO Auto-generated method stub
//		Brand res = null;
//		try {
//			Connection conn = JDBCUtil.getConnection();
//
//			String sql = "select * from brands where id=?;";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setInt(1, id);
//
//
//			ResultSet rs = pst.executeQuery();
//
//			while(rs.next()){
//				int idd = rs.getInt("id");
//				String name = rs.getString("name");
//				String country = rs.getString("country");
//				int available = rs.getInt("available");
//				res = new Brand(idd, name,country, available);
//			}
//
////			System.out.println(re + " dong da duoc them vao");
//			JDBCUtil.closeConnection(conn);
//			return res;
//
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
		return null;
	}

	@Override
	public ArrayList<cartitem> selectByCondition(cartitem t) {
		// TODO Auto-generated method stub
//		ArrayList<Brand> brands = new ArrayList<>();
//		try {
//			Connection conn = JDBCUtil.getConnection();
//			String sql = "select * from brands where id = ? or name = ? or country = ? or available = ?;";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setInt(1, t.getId());
//			pst.setString(2, t.getName());
//			pst.setString(3, t.getCountry());
//			pst.setInt(4, t.getAvailable());
//			ResultSet rs  = pst.executeQuery();
//			while(rs.next()) {
//				int id = rs.getInt("id");
//				String name = rs.getString("name");
//				String country = rs.getString("country");
//				int available = rs.getInt("avaible");
//				brands.add(new Brand(id, name, country, available));
//
//			}
//			return brands;
//		} catch (SQLException e) {
//			// TODO: handle exception
//			throw new RuntimeException(e);
//		}
	return null;
	}


	public boolean checkBrand(Brand t) {
		// TODO Auto-generated method stub
		Brand b = new Brand();
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select * from brands where name = ? and country = ?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getName());
			pst.setString(2, t.getCountry());
			ResultSet rs  = pst.executeQuery();
			while(rs.next()) {
				return true;

			}
			return false;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}
	
	public static int updateID(int currentID, int newID) {
		int re=0;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "update brands set id= ? where id =?;";
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
	public int selectTheMaxID(){
		int res=-1;
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select max(id) from brands;";
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
	public Brand selectByName(String namein) {
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select * from brands where name = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, namein);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String country =  rs.getString("country");
				int available =  rs.getInt("available");
				return new Brand(id,name, country,available);

			}
			JDBCUtil.closeConnection(conn);

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return null;

	}


	public static ArrayList<String> selectAllContries(){
		ArrayList<String> res= new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "select distinct country from brands";
			PreparedStatement pst = conn.prepareStatement(sql);

			ResultSet rs  = pst.executeQuery();
			while(rs.next()) {

				String country = rs.getString("country");

				res.add(country);

			}
			return res;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public ArrayList<DeCart> selectByCusID(int idin) {
		ArrayList<DeCart> res = new ArrayList<>();
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "select * from de_carts where idCart = ?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, idin);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int idc = rs.getInt("idCart");
				int idp = rs.getInt("idProduct");
				int qty = rs.getInt("qty");
				res.add(new DeCart(idc, idp, qty));

			}
			JDBCUtil.closeConnection(conn);
			return res;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	public ArrayList<DeCart> selectByCusaProID(int cusidin, ArrayList<Integer> idproins) {
		ArrayList<DeCart> res = new ArrayList<>();
		String idpros ="";
		for(Integer i : idproins) {
			idpros+=i +",";
		}
		idpros = idpros.substring(0,idpros.length()-1);
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "select * from de_carts where idCart = ? and idProduct in (" + idpros + ");";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, cusidin);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int idc = rs.getInt("idCart");
				int idp = rs.getInt("idProduct");
				int qty = rs.getInt("qty");
				res.add(new DeCart(idc, idp, qty));

			}
			return res;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public DeCart selectOneByCusaProID(int cusidin, int idproin) {
		DeCart re = new DeCart();
		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "select * from de_carts where idCart = ? and idProduct = ?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, cusidin);
			pst.setInt(2, idproin);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int idc = rs.getInt("idCart");
				int idp = rs.getInt("idProduct");
				int qty = rs.getInt("qty");
				re = new DeCart(idc, idp, qty);

			}
			JDBCUtil.closeConnection(conn);
			return re;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	public ArrayList<cartitem> getCartItems(int id) {
		ArrayList<DeCart> dcs = DecartDAO.getInstance().selectByCusID(id);
		ArrayList<Integer> idpros = new ArrayList<>();

		Map<Integer,ProductUnit> pMap = new HashMap<>();

		ArrayList<cartitem> cartitems = new ArrayList<>();

		for(DeCart d : dcs) {
			idpros.add(d.getIdProduct());
		}

		ArrayList<ProductUnit> ps = ProductUnitDAO.getInstance().selectByIDs(idpros);
		if(ps == null) {
			return null;
		}
		for(ProductUnit p : ps ) {
			pMap.put(p.getId(),p);
		}

		for(DeCart d : dcs) {
			cartitems.add(new cartitem(d.getIdCart(),pMap.get(d.getIdProduct()),d.getQty()));
		}

		return cartitems;
	}

	public ArrayList<cartitem> getCartItemsByCaP(int idcart, ArrayList<Integer> idProducts) {
		ArrayList<DeCart> dcs = DecartDAO.getInstance().selectByCusaProID(idcart,idProducts);
		ArrayList<Integer> idpros = new ArrayList<>();

		Map<Integer,ProductUnit> pMap = new HashMap<>();

		ArrayList<cartitem> cartitems = new ArrayList<>();

		for(DeCart d : dcs) {
			idpros.add(d.getIdProduct());
		}

		ArrayList<ProductUnit> ps = ProductUnitDAO.getInstance().selectByIDs(idpros);
		if(ps == null) {
			return null;
		}
		for(ProductUnit p : ps ) {
			pMap.put(p.getId(),p);
		}

		for(DeCart d : dcs) {
			cartitems.add(new cartitem(d.getIdCart(),pMap.get(d.getIdProduct()),d.getQty()));
		}

		return cartitems;
	}

	public int updateDecart(int idcart, int idProduct, int qty) {
		int re=0;

		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "update de_carts " +
					"set qty= ? " +
					"where idCart = ? and idProduct =?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, qty);
			pst.setInt(2, idcart);
			pst.setInt(3, idProduct);

			re = pst.executeUpdate();

			System.out.println(re + " dong da duoc cap nhat");
			JDBCUtil.closeConnection(conn);
			return re;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int deleteDecart(int idcart, int idProduct) {
		int re=0;

		try {
			Connection conn = JDBCUtil.getConnection();

			String sql = "DELETE FROM de_carts " +
					" where idCart = ? and idProduct = ?;";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, idcart);
			pst.setInt(2, idProduct);

			re = pst.executeUpdate();

			System.out.println(re + " dong da duoc cap nhat");
			JDBCUtil.closeConnection(conn);
			return re;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int deleteDecarts(int idcart, ArrayList<Integer> idProduct) {
		int re=0;

		try {
			Connection conn = JDBCUtil.getConnection();
			String idProducts="(";
			for(Integer i : idProduct) {
				idProducts+= i +",";
			}
			idProducts= idProducts.substring(0,idProducts.length()-1);
			idProducts+=")";
			String sql = "DELETE FROM de_carts " +
					" where idCart = ? and idProduct in " + idProducts + ";";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, idcart);

			re = pst.executeUpdate();

			System.out.println(re + " dong da duoc cap nhat");
			JDBCUtil.closeConnection(conn);
			return re;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



	
	public static void main(String[] args) {
//		insertData(); hàm này chỉ chạy 1 lần duy nhất để nhập db
//		ArrayList<Brand> brandList = BrandDAO.getInstance().selectAll();
//		String country="";
//		ArrayList<String> countries= new ArrayList<>();
//		for(Brand b : brandList) {
//			country = b.getCountry();
//			if(!countries.contains(country))
//				countries.add(country);
//		}
//		
//		for(String countr : countries) {
//			System.out.println(countr);
//		}
		
		
//		for(int i=4;i<=23;i++) {
//			int newID = Integer.parseInt("505" + i);
//			BrandDAO.getInstance().updateID(i, newID);
//		}

//		System.out.println(DecartDAO.getInstance().checkBrand(new Brand(1,"Bosos", "Đứcs",1)));
//		System.out.println(DecartDAO.getInstance().getCartItems(3032).size());

//		System.out.println(DecartDAO.getInstance().updateDecart(3031,10111,4));
//		System.out.println(DecartDAO.getInstance().deleteDecart(3031,10111));

		ArrayList<Integer> ids = new ArrayList<>();
		ids.add(10111);
		ids.add(10110);
//		System.out.println(DecartDAO.getInstance().getCartItemsByCaP(3032,ids).size());

//		System.out.println(DecartDAO.getInstance().selectOneByCusaProID(1204312,12412));

//		System.out.println(DecartDAO.getInstance().deleteDecarts(3031,ids));
	}


}
