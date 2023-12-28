package database;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDAO<T> {
	public int insert(T t);
	public int update(T t);
	public int delete(T t);
	public ArrayList<T> selectAll() throws SQLException;
	public T selectById(int id);
	public ArrayList<T> selectByCondition(T t);
	
}
