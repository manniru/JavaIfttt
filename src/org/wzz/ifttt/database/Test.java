package org.wzz.ifttt.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Test  {
	public static void main(String[] args) throws SQLException,ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaexp", "root", "mysql");
		
		Statement statement = connection.createStatement();
		
		String a[] = DataBase.GetTask(1,statement);
		for(int i=0;i<a.length;i++) {
			System.out.println(a[i]);
		}
		connection.close();
	}
}
