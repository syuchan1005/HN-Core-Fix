package co.honobono.hncorefix.constructor;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class SQLite {

	public static enum Casts {
		INTEGER("INTEGER"),
		TEXT("TEXT"),
		BLOB("BLOB"),
		REAL("REAL"),
		NUMERIC("NUMERIC"),
		TIMESTAMP("TIMESTAMP"),
		DATA("DATA"),
		NULL("NULL"),
		NOTNULL("NOT NULL"),
		UNIQUE("UNIQUE"),
		PRIMARYKEY("PRIMARY KEY"),
		AUTOINCREMENT("AUTOINCREMENT");

		final private String sql;

		private Casts(final String sql) {
			this.sql = sql;
		}

		@Override
		public String toString() {
			return this.sql;
		}

		public static Casts[] toArray(Casts... type ) {
			return type;
		}
	}

	private Statement state;

	public SQLite(File file) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
		this.state = con.createStatement();

	}

	public void create(String table, LinkedHashMap<String, Casts[]> map) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS \"" + table + "\"(";
		for (Map.Entry<String, Casts[]> e : map.entrySet()) {
			sql += "\"" + e.getKey() + "\" ";
			for (Casts s : e.getValue()) {
				sql += s.toString() + " ";
			}
			sql += ",";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ");";
		executeUpdate(sql);
	}

	public void checkPut(String table, String firstcolumn, Object firstvalue, Object... values) throws SQLException {
		delete(table, firstcolumn, firstvalue);
		Object[] o = new Object[values.length + 1];
		o[0] = firstvalue;
		for (int i = 1; i < o.length; i++) o[i] = values[i - 1];
		put(table, o);
	}

	public void put(String table, Object... obj) throws SQLException {
		String sql = "INSERT INTO " + table + " VALUES(";
		for (Object o : obj) {
			sql += "\"" + o + "\",";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ");";
		executeUpdate(sql);
	}

	public void set(String table, String setcolumnname, String setcolumnvalue, String wherecolumn, String wherevalue)
			throws SQLException {
		String sql = "UPDATE " + table + " SET " + setcolumnname + " = " + "\"" + setcolumnvalue + "\" where "
				+ wherecolumn + " = " + wherevalue;
		executeUpdate(sql);
	}

	public ResultSet get(String table, String[] column) throws SQLException {
		String sql = "SELECT ";
		for (String c : column) sql += "\"" + c + "\",";
		sql = sql.substring(0, sql.length() - 1) + " from " + table;
		return executeQuery(sql);
	}

	public ResultSet get(String table, String wherecolumn, Object wherevalue, String... getcolumn) throws SQLException {
		String sql = "SELECT ";
		for (String s : getcolumn) sql += "\"" + s + "\",";
		sql = sql.substring(0, sql.length() - 1);
		sql += " from " + table + " where \"" + wherecolumn + "\" = \"" + wherevalue + "\";";
		return executeQuery(sql);
	}

	public void delete(String table, String column, Object columnvalue) throws SQLException {
		String sql = "DELETE FROM " + table + " WHERE \"" + column + "\" != \"" + columnvalue + "\"";
		executeUpdate(sql);
	}

	public void executeUpdate(String sql) throws SQLException {
		state.executeUpdate(sql);
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		return state.executeQuery(sql);
	}

	@Deprecated
	public boolean noValue(String table, String countcolumn, Object value) throws SQLException {
		String sql = "select count(\"" + countcolumn + "\") where \"" + countcolumn + "\" = \"" + value + "\"" + " from " + table;
		ResultSet rs = executeQuery(sql);
		rs.next();
		int i = rs.getInt(1);
		return (i == 0);
	}

	public static String getTimeStamp() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
		return simpleDateFormat.format(date);
	}
}
