/**
 * channelMap
 * channel Type Definition:
 * 		mail
 * 		weibo
 * 		time
 * */
package org.joe.ifttt.server.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.joe.ifttt.server.channel.Channel;
import org.joe.ifttt.server.type.UserLevel;
import org.joe.ifttt.server.type.UserState;


public class CommonUser {
	/***This is the Common User Class*/
	private final int MAX_CHANNEL = 10; 
	private final int MAX_TASK = 10;
	private String 		username;
	private String 		password;
	private String		mailAddress;
	private long		score;
	private UserLevel	userLevel;
	private UserState	userState; 
	private String 		screenName;
	private int			maxTaskNum;
	private int			numOfTasks;
	private String		createTime;
	//map<channelType, channelObj>
	private Map<String, Channel> channelMap;
	private Vector<Long> userTask;
	
	public CommonUser(String username, String password, String mail) {
		this.username = username;
		this.password = password;
		this.mailAddress = mail;
		this.numOfTasks = 0;
		this.userTask = new Vector<Long>(MAX_TASK);
	}
	public CommonUser(String un, String psw, String maddr, 
			long sc, UserLevel ul, UserState us) {
		this.username = un;
		this.password = psw;
		this.mailAddress = maddr;
		this.score = sc;
		this.userLevel = ul;
		this.userState = us;
		this.channelMap = new HashMap<String, Channel>(MAX_CHANNEL);
		this.userTask = new Vector<Long>(MAX_TASK);
		this.numOfTasks = 0;
		System.out.println("size of map: " + this.channelMap.size());
	}
	public CommonUser(String un, String psw, String sn, String maddr, 
			long sc, UserLevel ul, UserState us) {
		this.username = un;
		this.password = psw;
		this.screenName = sn;
		this.mailAddress = maddr;
		this.score = sc;
		this.userLevel = ul;
		this.userState = us;
		this.numOfTasks = 0;
		this.channelMap = new HashMap<String, Channel>(MAX_CHANNEL);
		this.userTask = new Vector<Long>(MAX_TASK);
		System.out.println("size of map: " + this.channelMap.size());
	}
	public String toString() {
		/***to get the basic info of the user*/
		String string;
		string = "USER|USERNAME:" + username + "|EMAIL:" + mailAddress + 
		"|SCORE:" + score + "|LEVEL:" + userLevel + "|STATE:" + userState + "\n";
		return string;
	}
	
	public Channel getChannel (String channelKey) {
		return channelMap.get(channelKey);
	}
	public void setChannel (String key, Channel channel) {
		channelMap.put(key, channel);
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setPassword(String string) {
		this.password = string;
	}
	public String getPassword() {
		return password;
	}
	public void setMailAddres(String mailAddres) {
		this.mailAddress = mailAddres;
	}
	public String getMailAddres() {
		return mailAddress;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public long getScore() {
		return score;
	}
	public void setUserLevel(UserLevel userLevel) {
		this.userLevel = userLevel;
	}
	public UserLevel getUserLevel() {
		return userLevel;
	}
	public void setUserState(UserState userState) {
		this.userState = userState;
	}
	public UserState getUserState() {
		return userState;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public Vector<Long> getUserTask() {
		return userTask;
	}
	public void setUserTask(Vector<Long> userTask) {
		this.userTask = userTask;
	}
	public int getMaxTaskNum() {
		return maxTaskNum;
	}
	public void setMaxTaskNum(int maxTaskNum) {
		this.maxTaskNum = maxTaskNum;
	}
	public int getNumOfTasks() {
		return numOfTasks;
	}
	public void setNumOfTasks(int numOfTasks) {
		this.numOfTasks = numOfTasks;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
