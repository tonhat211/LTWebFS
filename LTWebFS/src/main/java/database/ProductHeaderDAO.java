package database;

import java.sql.Connection;
import java.util.ArrayList;

import model.ProductHeader;

public class ProductHeaderDAO implements IDAO<ProductHeader>{
	public static ProductHeaderDAO getInstance() {
		return new ProductHeaderDAO();
		
	}

	@Override
	public int insert(ProductHeader t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ProductHeader t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(ProductHeader t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<ProductHeader> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductHeader selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ProductHeader> selectByCondition(ProductHeader t) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
