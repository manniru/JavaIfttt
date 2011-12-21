package org.joe.ifttt.server.manager;
/**
 * File: 			UserManage.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/12
 * Description:
 * The definition of User manager, one of the managers.It takes 
 * responsibility for Users managing. It provides a convenient 
 * interface to server and other managers, implements the operations 
 * of Users and processes all requests about Users.
 * 
 * modify 2011/11/19 1:12
 *  add the check for username
 * modify 2011/12/19 12:27
 * 	1.add send message function 
 *  2.separate the data access and the logic control of users
 *  	createUser
 *  	loginUser
 */
import java.util.HashMap;
import java.util.Map;

import org.joe.ifttt.server.GlobalFunctions;
import org.joe.ifttt.server.channel.Channel;
import org.joe.ifttt.server.channel.MailChannel;
import org.joe.ifttt.server.channel.WeiboChannel;
import org.joe.ifttt.server.type.UserLevel;
import org.joe.ifttt.server.type.UserState;
import org.joe.ifttt.server.user.ChannelUser;
import org.joe.ifttt.server.user.CommonUser;

public class UserManager {
	/**The manager to manage the users*/
	protected final static int MAX_NUM_OF_USERS = 1000;
	protected final static int MAX_NUM_OF_LOGIN_USERS = 10;
	private static UserManager _manager = null;
	private static int instanceNum = 0;
	//private Map<String, CommonUser> users;
	private Map<Long, CommonUser> loginUsers;
	private Long currentLoginUsers;
	private long numOfUsers = 0;
	
	public static UserManager getInstance() {
		/**get instance of user manager*/
		if (_manager  == null) {
			instanceNum ++ ;
			_manager = new UserManager();
			return _manager;
		}
		else {
			return _manager;
		}
	}
	
	protected UserManager() {
		//users = new HashMap<String, CommonUser>(MAX_NUM_OF_USERS);
		loginUsers = new HashMap<Long, CommonUser>(MAX_NUM_OF_LOGIN_USERS);
		currentLoginUsers = new Long(0);
	}
	
	public boolean createUser(String un, String psw, String sn, String maddr) {
		/**create a new user*/
		if (null == un || null == psw) {
			return false;
		}
		if (!GlobalFunctions.isLegalUsername(un)) {
			return false;
		}
		
		boolean newUser = DataManager.getInstance().
				newUser(un, psw, sn, maddr, 0, UserLevel.INIT, UserState.ACTIVE);
		if (!newUser) {
			return false;
		}
		//users.put(un, new CommonUser(un, psw, maddr, 0, UserLevel.INIT, UserState.ACTIVE));
		System.out.println("SUCCESS to CREATE A USER");
		setNumOfUsers(getNumOfUsers() + 1);
		return true;
	}
	
	public long loginUser (String un, String psw) {
		/** login the user
		 * PARAMETERS:
		 * 		un	: user name
		 * 		psw	: password
		 * RETURN: 
		 * 		success	: user's hash code (must be positive) 
		 * 				  for this login 
		 * 		fail	: -1(password error); -2(username error)
		 */
		if (!GlobalFunctions.isLegalUsername(un)) {
			return -1;
		}
		//CommonUser currentUser = users.get(un);
		//user get User test
		CommonUser currentUser = DataManager.getInstance().getUserTest(un);
		
		if (null != currentUser) {
			if (currentUser.getPassword().equals(psw)) { //never to use "==", gosh!
				currentLoginUsers ++;
				loginUsers.put(currentLoginUsers, currentUser);
				System.out.println("SUCCESS to LOGIN");
				return currentLoginUsers.longValue();
			}
			else {
				System.out.println("FAIL to LOGIN: psw = " + psw);
				return -1; //password error
			}
		}
		else {
			System.out.println("FAIL to LOGIN: un = " + un);
			return -2; //username error
		}
	}
	
	public void logoutUser (long userHash) {
		//to store the modified user information to database
		DataManager.getInstance().updateUser();
		loginUsers.remove(userHash);
	}
	
	public CommonUser getLoginUserByHashcode(long userHash) {
		return loginUsers.get(userHash);
	}
	public CommonUser getUserByUsername(String username) {
		/**get User by Username*/
		//return users.get(username);
		//need modified
		return DataManager.getInstance().getUserTest(username);
	}
	
	public boolean addChannel (long userHash, String chanType, ChannelUser chanUser) {
		/**add a channel for the user, if exist, then replace it*/
		CommonUser currentUser = loginUsers.get(userHash);
		if (currentUser == null) {
			return false;
		}
		Channel newChannel = null;
		if (chanType.equals("mail")) {
			newChannel = new MailChannel(chanUser);
		}
		else if (chanType.equals("weibo")) {
			newChannel = new WeiboChannel(chanUser);
		}
		currentUser.setChannel(chanType, newChannel);
		return true;
	}
	
	public boolean sendMessgaeTo (long userHash, String receiver, String content) {
		/*send a message to receiver*/
		CommonUser currentUser = loginUsers.get(userHash);
		if (currentUser == null) {
			return false;
		}
		if (!GlobalFunctions.isLegalUsername(receiver)) {
			return false;
		}
		if (!userExists(receiver)) {
			return false;
		}
		return DataManager.getInstance().newMessage(new Long(0), 
				currentUser.getUsername(), receiver, content);
	}
	
	public boolean userExists (String username) {
		if (!GlobalFunctions.isLegalUsername(username)) {
			return false;
		}
		return DataManager.getInstance().isUser(username);
	}
	public long getNumOfUsers() {
		return numOfUsers;
	}

	public void setNumOfUsers(long numOfUsers) {
		this.numOfUsers = numOfUsers;
	}
}
