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

import org.joe.ifttt.server.channel.Channel;
import org.joe.ifttt.server.type.UserLevel;
import org.joe.ifttt.server.type.UserState;


public class CommonUser {
	/***This is the Common User Class*/
	private final int MAX_CHANNEL = 10; 
	private String 		username;
	private String 		password;
	private String		mailAddres;
	private long		score;
	private UserLevel	userLevel;
	private UserState	userState; 
	//map<channelType, channelObj>
	private Map<String, Channel> channelMap;
	
	public CommonUser(String username, String password, String mail) {
		this.username = username;
		this.password = password;
		this.mailAddres = mail;
	}
	public CommonUser(String un, String psw, String maddr, 
			long sc, UserLevel ul, UserState us) {
		this.username = un;
		this.password = psw;
		this.mailAddres = maddr;
		this.score = sc;
		this.userLevel = ul;
		this.userState = us;
		this.channelMap = new HashMap<String, Channel>(MAX_CHANNEL);
		System.out.println("size of map: " + this.channelMap.size());
	}
	public String toString() {
		/***to get the basic info of the user*/
		String string;
		string = "USER|USERNAME:" + username + "|EMAIL:" + mailAddres + 
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
		this.mailAddres = mailAddres;
	}
	public String getMailAddres() {
		return mailAddres;
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
}
