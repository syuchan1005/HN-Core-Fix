package co.honobono.hncorefix.enums;

public enum SQLType {
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

	private SQLType(final String sql) {
		this.sql = sql;
	}

	@Override
	public String toString() {
		return this.sql;
	}

	public static SQLType[] toArray(SQLType... type ) {
		return type;
	}
}
