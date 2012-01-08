package org.joe.ifttt.server.manager;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.joe.ifttt.server.channel.MailChannel;
import org.joe.ifttt.server.channel.WeiboChannel;
import org.joe.ifttt.server.task.TaskFrame;
import org.joe.ifttt.server.task.action.That;
import org.joe.ifttt.server.task.event.This;
import org.joe.ifttt.server.type.UserLevel;
import org.joe.ifttt.server.type.UserState;
import org.joe.ifttt.server.user.CommonMessage;
import org.joe.ifttt.server.user.CommonUser;
import org.wzz.ifttt.database.DBfunction;
import org.wzz.ifttt.database.MsgDBfunc;

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
	
	public boolean DB_newTask(long taskid, TaskFrame task, String username) throws SQLException, ClassNotFoundException {
		DBfunction.setTask("0", taskid, task.getTaskName(), 
				task.getTaskState().toString(), task.getTaskType().toString(), 
				task.getThisParam(), task.getThatParam(), username, 0, 
				(new java.util.Date()).toString());
		return true;
	}
	
	public TaskFrame DB_getTask(long taskid) throws SQLException, ClassNotFoundException {
		TaskFrame task = new TaskFrame();
		task.setTaskId(taskid);
		task.setTaskName(DBfunction.getTaskName(taskid));
		task.setTaskType(DBfunction.getTaskType(taskid));
		task.setOwner(DBfunction.getOwner(taskid));
		task.setThisParam(DBfunction.getTrigger(taskid));
		task.setThatParam(DBfunction.getAction(taskid));
		task.setRepeat(DBfunction.getRepeat(taskid)=="0"?false:true);
		return task;
	}
	public int DB_getMsgCount() throws SQLException, ClassNotFoundException {
		return MsgDBfunc.msgNum();
	}
	
	public boolean DB_updateUser(String username, CommonUser usr) 
			throws SQLException, ClassNotFoundException {
		String channelString = "";
		String tasksString = "";
		WeiboChannel wChannel = (WeiboChannel)(usr.getChannel("weibo"));
		MailChannel mChannel = (MailChannel)(usr.getChannel("mail"));
		if (wChannel != null) {
			channelString = channelString + "|weibo" + ":" +
					wChannel.getUser().getUsername() + ":" + wChannel.getUser().getPassword();
		}
		if (mChannel != null) {
			channelString = channelString + "|mail" + ":" + 
					mChannel.getUser().getUsername() + ":" + mChannel.getUser().getPassword();
		}
		
		java.util.Iterator<Long> it = usr.getUserTask().iterator();
		while(it.hasNext()) {
			Long task = it.next();
			tasksString = tasksString + task.toString() + ",";
		}
		
		DBfunction.setMember("0", username, usr.getPassword(), usr.getMailAddres(), 
				usr.getScreenName(), usr.getScore(), usr.getUserLevel().toString(), 
				usr.getUserState().toString(), channelString, tasksString, usr.getMaxTaskNum(), usr.getNumOfTasks(), usr.getCreateTime());	
		return true;
	}
	public boolean DB_newMessage(int msgId, CommonMessage newmsg) throws SQLException, ClassNotFoundException {
		MsgDBfunc.sendMsg(msgId, newmsg.getSenderString(), 
				newmsg.getReceiverString(), newmsg.getContent());
		return true;
	}
	
	public Vector<CommonMessage> DB_getSendMessage(String username) throws ClassNotFoundException, SQLException {
		Vector<String> vector = MsgDBfunc.getMsgByUserName(username, 1);
		Vector<CommonMessage> msgs = new Vector<CommonMessage>(10, 5);
		java.util.Iterator<String> it;
		it = vector.iterator();
		while(it.hasNext()) {
			String reString = it.next();
			String[] eleString = reString.split(":");
			CommonMessage newMsg = new CommonMessage(eleString[1], eleString[2], eleString[3]);
			msgs.add(newMsg);
			/*
			for (int i = 0; i < eleString.length; i++) {
				System.out.print(i + " : " + eleString[i] + " ,");
			}
			*/
			//System.out.println(it.next());
		}
		return msgs;
	}
	
	public Vector<CommonMessage> DB_getRecvMessage(String username) throws ClassNotFoundException, SQLException {
		Vector<String> vector = MsgDBfunc.getMsgByUserName(username, 2);
		Vector<CommonMessage> msgs = new Vector<CommonMessage>(10, 5);
		java.util.Iterator<String> it;
		it = vector.iterator();
		while(it.hasNext()) {
			String reString = it.next();
			String[] eleString = reString.split(":");
			CommonMessage newMsg = new CommonMessage(eleString[1], eleString[2], eleString[3]);
			msgs.add(newMsg);
			/*
			for (int i = 0; i < eleString.length; i++) {
				System.out.print(i + " : " + eleString[i] + " ,");
			}
			*/
			//System.out.println(it.next());
		}
		return msgs;
	}
	
	public Vector<CommonMessage> DB_getAllMessage(String username) throws ClassNotFoundException, SQLException {
		Vector<String> vector = MsgDBfunc.getMsgByUserName(username, 3);
		Vector<CommonMessage> msgs = new Vector<CommonMessage>(10, 5);
		java.util.Iterator<String> it;
		it = vector.iterator();
		while(it.hasNext()) {
			String reString = it.next();
			String[] eleString = reString.split(":");
			System.out.println("***DataManager:" + reString + " : " + eleString[1]+ "," + eleString[2]
					+ "," + eleString[3]);
			CommonMessage newMsg = new CommonMessage(eleString[1], eleString[2], eleString[3]);
			msgs.add(newMsg);
			/*
			for (int i = 0; i < eleString.length; i++) {
				System.out.print(i + " : " + eleString[i] + " ,");
			}
			*/
			//System.out.println(it.next());
		}
		return msgs;
	}
	
	public boolean DB_newUser(String username, String password, String screenname, String mailaddr, 
			long score, UserLevel userLevel, UserState userState) throws SQLException, ClassNotFoundException {
		DBfunction.setMember("0", username, password, mailaddr, screenname, score, userLevel.toString(), 
				userState.toString(), "...", "...", 1000, 0, (new java.util.Date()).toLocaleString());	
		return true;
	}
	
	public CommonUser DB_getUser(String username) throws SQLException, ClassNotFoundException {
		/**the real get User*/
		String password = DBfunction.getPassword(username);
		String mail = DBfunction.getMailAddress(username);
		CommonUser curUser = new CommonUser(username, password, mail);
		curUser.setScreenName(DBfunction.getNickName(username));
		curUser.setMaxTaskNum(Integer.parseInt(DBfunction.getMaxTask(username)));
		curUser.setScore(Integer.parseInt(DBfunction.getScore(username)));
		curUser.setUserLevel(DBfunction.getUserLevel(username) == "INIT" ? UserLevel.INIT : UserLevel.INIT);
		curUser.setNumOfTasks(Integer.parseInt(DBfunction.getNumOfTasks(username)));
		curUser.setUserState(UserState.ACTIVE);
		curUser.setCreateTime((new java.util.Date()).toLocaleString());
		String channel = DBfunction.getChannels(username);
		if (curUser == null) {
			return null;
		}
		return curUser; 
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
