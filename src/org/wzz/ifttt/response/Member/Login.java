package org.wzz.ifttt.response.Member;

import java.sql.SQLException;

import org.joe.ifttt.server.manager.DataManager;
import org.joe.ifttt.server.manager.UserManager;
import org.joe.ifttt.server.user.CommonUser;


public class Login {
	private String userName;
	private String nickName;
	private String password;
	private long loginHash;
	
	
	public long login() throws ClassNotFoundException, SQLException {
		/***/
		
		if (!isLogin(this.loginHash)) {
			this.loginHash = UserManager.getInstance().loginUser(userName, password);
			if (this.loginHash < 0) {
				System.out.println("login in error");
			}
			this.nickName = UserManager.getInstance().getLoginUserByHashcode(this.loginHash).getScreenName();
		}
		return this.loginHash;
	}
	
	public static boolean isLogin(long authCode) {
		if (UserManager.getInstance().getLoginUserByHashcode(authCode) != null) {
			return true;
		}
		else {
			return false;
		}
	}
	public String getUsernameByHashcode (long authCode) {
		CommonUser currentUser = UserManager.getInstance().getLoginUserByHashcode(authCode);
		if (currentUser != null) {
			return currentUser.getUsername();
		}
		return null;
	}
	
	public String getLevel(String s) throws ClassNotFoundException {
		try {
			//String level = DataManager.getInstance().getUserTest(s).getUserLevel().toString();
			String level = DataManager.getInstance().DB_getUser(s).getUserLevel().toString();
			if (level == null) {
				return "";
			}
			else {
				return level;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public long getIntegral(long s) throws ClassNotFoundException {
		System.out.println("get Integral:" + s);
		return UserManager.getInstance().getLoginUserByHashcode(s).getScore();
	}
	
	public String selectNickName(long id) throws ClassNotFoundException {
		return UserManager.getInstance().getLoginUserByHashcode(id).getScreenName();
	}

	public long getLoginHash() {
		return loginHash;
	}

	public void setLoginHash(long loginHash) {
		this.loginHash = loginHash;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setNickName(String s) {
		nickName = s;
	}
	
	public void setPassword(String s) {
		password = s;
	}
	
	public void setUserName(String s) {
		userName = s;
	}
	
}
