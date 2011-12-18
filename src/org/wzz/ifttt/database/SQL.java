package org.wzz.ifttt.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQL {
	
	/*public static Statement getStatement() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc://localhost/javaexp", "root", "mysql");
		Statement statement = connection.createStatement();
		return statement;
	}*/
	
	public static ResultSet SQLexe(String SQL,Statement statement) throws SQLException, ClassNotFoundException {
		return statement.executeQuery(SQL);
	}
	
	public static int SQLupdate(String SQL,Statement statement) throws SQLException, ClassNotFoundException {
		return statement.executeUpdate(SQL);
	}
}
