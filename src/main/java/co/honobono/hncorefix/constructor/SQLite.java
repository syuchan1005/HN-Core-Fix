package co.honobono.hncorefix.constructor;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import co.honobono.hncorefix.enums.SQLType;


public class SQLite {
	private Statement state;

	public SQLite(File file) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
		this.state = con.createStatement();
	}

	public void create(String table, LinkedHashMap<String, SQLType> map) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS '" + table + "'(";
		for (Map.Entry<String, SQLType> e : map.entrySet()) {
			sql += "'" + e.getKey() + "' " + e.getValue().toString() + ",";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ");";
		execute(sql);
	}

	public void checkPut(String table, String firstcolumn, Object firstvalue, Object... values) throws SQLException {
		if(!noValue(get(table, firstcolumn, firstcolumn, firstvalue))) {
			delete(table, firstcolumn, firstvalue);
		}
		put(table, firstvalue, values);
	}


	public void put(String table, Object... obj) throws SQLException {
		String sql = "INSERT INTO " + table + " VALUES(";
		for(Object o : obj) {
			sql += "'" + o + "',";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ");";
		execute(sql);
	}

	public void set(String table, String setcolumnname, String setcolumnvalue, String wherecolumn, String wherevalue) throws SQLException {
		String sql = "UPDATE " + table + " SET " + setcolumnname + " = " + "'" + setcolumnvalue + "' where " + wherecolumn + " = " + wherevalue;
		execute(sql);
	}

	public ResultSet get(String table, String... column) throws SQLException {
		String sql = "SELECT ";
		for(String c : column) sql += c + ",";
		sql = sql.substring(0, sql.length() - 1) + " from " + table;
		return executeQuery(sql);
	}

	public ResultSet get(String table, String getcolumn, String wherecolumn, Object wherevalue) throws SQLException {
		String sql = "SELECT " + getcolumn + " from " + table + " where " + wherecolumn + " = '" + wherevalue + "'";
		return executeQuery(sql);
	}

	public void delete(String table, String column, Object columnvalue) throws SQLException {
		String sql = "DELETE FROM " + table + " WHERE '" + column + "' != '" + columnvalue + "'";
		execute(sql);
	}

	public void execute(String sql) throws SQLException {
		state.execute(sql);
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		return state.executeQuery(sql);
	}

	public static void main(String... args) {
		LinkedHashMap<String, SQLType> map = new LinkedHashMap<>();
		map.put("Field1", SQLType.INTEGERNOTNULL);
		map.put("Field2", SQLType.TEXTNOTNULL);
		try {
			SQLite lite = new SQLite(new File("setting.db"));
			lite.create("Home", map);
			lite.put("Home", 0, "aaaaa");
			lite.put("Home", 1, "aaaaa");
			lite.put("Home", 3, "ccccc");
			lite.set("Home", "Field2", "bbbbb", "Field1", "1");
			lite.delete("Home", "Field1", "0");
			ResultSet rs = lite.get("Home", "Field2", "Field1", "0");
			while(rs.next()) System.out.println(rs.getString(1));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private static boolean noValue(ResultSet rs) throws SQLException {
		int c = 0;
		while(rs.next()) c++;
		return c == 0;
	}
}
