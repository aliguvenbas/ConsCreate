package com.ag.cc.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ag.cc.ConstantPair;

public class Intarrogator {
	public List<ConstantPair> getConstantPairs(String variableNameColumn, String valueColumn, IDB dbx) {
		List<ConstantPair> constantList = null;
		String sqlText = "select top 10 * from U_001_ITEMS ";
		JDBCConn db = new JDBCConn();
		try {
			if (db.getConnection()) {
				db.rset = db.getStatement().executeQuery(sqlText);
				constantList = new ArrayList<>();
				while (db.rset.next()) {
					constantList.add(new ConstantPair("String", db.rset.getString(variableNameColumn), db.rset.getString(valueColumn)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return constantList;
	}
}
