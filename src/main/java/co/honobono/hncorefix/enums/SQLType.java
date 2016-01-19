package co.honobono.hncorefix.enums;

public enum SQLType {
	INTEGER("INTEGER"),
	TEXT("TEXT"),
	BLOB("BLOB"),
	REAL("REAL"),
	NUMERIC("NUMERIC"),
	INTEGERNOTNULL("INTEGER NOT NULL"),
	TEXTNOTNULL("TEXT NOT NULL"),
	BLOBNOTNULL("BLOB NOT NULL"),
	REALNOTNULL("REAL NOT NULL"),
	NUMERICNOTNULL("NUMERIC NOT NULL"),
	TIMESTAMP("TIMESTAMP DEFAULT (DATETIME('now','localtime'))");

	final private String sql;

	private SQLType(final String sql) {
		this.sql = sql;
	}

	@Override
	public String toString() {
		return this.sql;
	}
}
