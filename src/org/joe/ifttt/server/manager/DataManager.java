package org.joe.ifttt.server.manager;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.joe.ifttt.server.type.UserLevel;
import org.joe.ifttt.server.type.UserState;
import org.joe.ifttt.server.user.CommonMessage;
import org.joe.ifttt.server.user.CommonUser;
import org.wzz.ifttt.database.DBfunction;;

/**
 * File: 			DataManage.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/10
 * Description:
 * The definition of data manager, one of the managers
 * be responsible for data managing
 * Provides a convenient interface to server and other managers
 * implement the operation of raw data
 * 
 * PS.we use CommonUser , TaskFrame, CommonMessage in this file for test,
 * will modify these when we complete the database design.
 */
public class DataManager {
	/***/
	private final static int MAX_MSG_NUM = 100;
	protected final static int MAX_NUM_OF_USERS = 1000;
	private static DataManager _manager = null;
	private static int instanceNum = 0;
	private Long messageNum;
	private Map<Long, CommonMessage> msgs;
	private Map<String, CommonUser> users;
	
	public static DataManager getInstance() {
		/**get instance of task manager*/
		if (_manager  == null) {
			instanceNum ++ ;
			_manager = new DataManager();
			return _manager;
		}
		else {
			return _manager;
		}
	}
	public DataManager() {
		messageNum = new Long(0);
		msgs = new HashMap<Long, CommonMessage>(100);
		users = new HashMap<String, CommonUser>(MAX_NUM_OF_USERS);
	}
	
	public boolean newMessage(long msgId, CommonMessage newmsg) {
		msgs.put(msgId, newmsg);
		return true;
	}
	public CommonMessage getMessage(long msgId) {
		return msgs.get(msgId);
	}
	public boolean updateUser() {
		/**to update User when logout, to Persistence the date which modified*/
		return true;
	}
	public boolean newUser(String username, String password, String screenname, String mailaddr, 
			long score, UserLevel userLevel, UserState userState) {
		/*
		java.util.Date currentDate = new java.util.Date();
		try {
			DataBase.setMember("admin", username, password, mailaddr, screenname, score, 
					userLevel.toString(), userState.toString(), "CHANNELS:", "TASKS:", 10, 0, 
					new Date(currentDate.getYear(), currentDate.getMonth(), currentDate.getDay()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		users.put(username, new CommonUser(username, password, 
				screenname, mailaddr, score, userLevel, userState));
				
		return true;
	}
	
	public CommonUser getUserTest(String username) throws SQLException, ClassNotFoundException {
		/**a simple getUser, but not good*/
		/*
		String password = DataBase.getPassword(username);
		if (password == null) {
			return null;
		}
		String nickname = DataBase.getNickName(username);
		String mailaddr = DataBase.getMailAddress(username);
		long score = Long.parseLong(DataBase.getScore(username));
		UserLevel userlevel = UserLevel.INIT;
		String userStateString = DataBase.getUserState(username);
		UserState userstate;
		if (userStateString.equals("ACTIVE")) {
			userstate = UserState.ACTIVE;
		}
		else {
			userstate = UserState.INACTIVE;
		}
		CommonUser curUser = new CommonUser(username, password, nickname, mailaddr, score, userlevel, userstate);
		return curUser;
		*/
		return users.get(username);
	}
	
	public String getUser(String username) {
		/**the real get User*/
		CommonUser curUser = users.get(username);
		if (curUser == null) {
			return null;
		}
/*
		StringBuffer sb = new StringBuffer("User:");
		sb.append(curUser.getUsername() + ",");
		sb.append(curUser.getPassword() + ",");
		sb.append(curUser.getMailAddres() + ",");
		sb.append(curUser.getScore() + ",");
		sb.append(curUser.getUserLevel() + ",");
		sb.append(curUser.getUserState() + ",");
*/			
		
		return null; 
	}
	public boolean isUser(String username) {
		return true;
	}

	public boolean getMessage() {
		return true;
	}
	public boolean delMessage() {
		return true;
	}

	public Long getMessageNum() {
		return messageNum;
	}
	public void setMessageNum(Long messageNum) {
		this.messageNum = messageNum;
	}
}
