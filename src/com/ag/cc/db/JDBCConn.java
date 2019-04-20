package com.ag.cc.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCConn {

	public Connection connection;
	private java.sql.Statement statement;
	public PreparedStatement pstatement;
	public ResultSet rset;
	public CallableStatement cstmt;

	public Statement getStatement() {
		return statement;
	}

	public PreparedStatement preStatement(String sql) {
		try {
			pstatement = connection.prepareStatement(sql);
			return pstatement;
		} catch (Exception e) {
			return pstatement;
		}
	}

	public boolean getConnection() {
		String dbDriverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbUrlName = "jdbc:sqlserver://127.0.0.1:1433;databaseName=LOGOSPHERE";
		String dbUserName = "sa";
		String dbUserPassword = "logo";

		try {
			Class.forName(dbDriverName).newInstance();
			connection = DriverManager.getConnection(dbUrlName, dbUserName, dbUserPassword);

			// connection = datasource.getConnection();
			statement = connection.createStatement();
			return true;

		} catch (Exception e) {
			System.err.println("JDBC error >> " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public void closeConnection() {
		try {
			if (rset != null)
				try {
					rset.close();
				} catch (Exception e) {
					System.err.println("__CONNECTION RESULTSET KAPATILAMADI: " + e.getMessage());
				}
			if (cstmt != null)
				try {
					cstmt.close();
				} catch (Exception e) {
					System.err.println("__CONNECTION CALLABLE STATEMENT KAPATILAMADI: " + e.getMessage());
				}
			if (statement != null)
				try {
					statement.close();
				} catch (Exception e) {
					System.err.println("__CONNECTION STATEMENT KAPATILAMADI: " + e.getMessage());
				}
			if (pstatement != null)
				try {
					pstatement.close();
				} catch (Exception e) {
					System.err.println("__CONNECTION PREPARED STATEMENT KAPATILAMADI: " + e.getMessage());
				}
			if (connection != null)
				try {
					connection.close();
				} catch (Exception e) {
					System.err.println("__CONNECTION KAPATILAMADI: " + e.getMessage());
				}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
