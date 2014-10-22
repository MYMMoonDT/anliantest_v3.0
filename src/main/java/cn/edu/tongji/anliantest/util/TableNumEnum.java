package cn.edu.tongji.anliantest.util;

public enum TableNumEnum {
	HTPSJL("HTPSJL", "ALJC/JL07-03"),
	GZRWD("GZRWD", "ALJC/JL32-01"),
	KHZLDJD("KHZLDJD", "ALJC/JL32-02");
	
	public String tableName;
	public String tableNum;
	
	TableNumEnum() {}
	
	TableNumEnum(String tableName, String tableNum) {
		this.tableName = tableName;
		this.tableNum = tableNum;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableNum() {
		return tableNum;
	}

	public void setTableNum(String tableNum) {
		this.tableNum = tableNum;
	}
	
	@Override
	public String toString() {
		return tableNum;
	}
}
