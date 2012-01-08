package org.wzz.ifttt.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class SQLconn {
	
	public static Statement initialMySQL() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/ifttt_database", "root", "19910111");
		Statement statement = connection.createStatement();
		return statement;
	}
	
	public static void Close(Statement statement) throws SQLException {
		statement.close();
	}
	
	public static ResultSet SQLexe(String SQL,Statement statement) throws SQLException, ClassNotFoundException {
		return statement.executeQuery(SQL);
	}
	
	public static int SQLupdate(String SQL,Statement statement) throws SQLException, ClassNotFoundException {
		return statement.executeUpdate(SQL);
	}
}
