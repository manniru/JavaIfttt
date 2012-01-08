package org.joe.ifttt.server.servlet.model;

import java.sql.SQLException;

import org.joe.ifttt.server.manager.UserManager;

public class Server {
	public static long LoginUser(SimpleUser user) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return UserManager.getInstance().loginUser(user.getUsername(), user.getPassword());
	}
	public static boolean RegisterUser(SimpleUser user) {
		// TODO Auto-generated method stub
		//UserManager.getInstance().createUser(user.getUsername(), user.getPassword(), user.getMail());
		return true;
	}
	public static boolean CreateTask(String taskDescription) {
		return true;
	}
	public static boolean DeleteTask(long taskId) {
		return true;
	}
	public static boolean AddChannel(String channelDescription) {
		return true;
	}
	public static boolean SendMsg(String username) {
		return true;
	}
}
