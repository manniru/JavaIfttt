package org.wzz.ifttt.response.Member;

import java.sql.SQLException;

import org.joe.ifttt.server.manager.DataManager;
import org.joe.ifttt.server.manager.UserManager;
import org.joe.ifttt.server.user.CommonUser;

import sun.net.www.protocol.http.AuthCache;

public class Login {
	private String MemberId;
	private String UserName;
	private String Password;
	private long loginHash;
	
	
	public long login() throws ClassNotFoundException {
		/***/
		
		if (!isLogin(this.loginHash)) {
			this.loginHash = UserManager.getInstance().loginUser(MemberId, Password);
			if (this.loginHash < 0) {
				System.out.println("login in error");
			}
			this.UserName = UserManager.getInstance().getLoginUserByHashcode(this.loginHash).getScreenName();
		}
		/*
		Statement statement = SQL.initialMySQL();
		if(Password.equals(DataBase.GetMemberkey(MemberId, statement))) {
			return DataBase.GetMember(MemberId, statement);
		}
		else return null;
		*/
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
		
		/*
		Statement statement = SQL.initialMySQL();
		return DataBase.GetLevel(s, statement);
		*/
		try {
			return DataManager.getInstance().getUserTest(s).getUserLevel().toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	public double getMoney(String s) throws ClassNotFoundException, SQLException {
		Statement statement = SQL.initialMySQL();
		return DataBase.GetMoney(s, statement);
	}
	*/
	
	public long getIntegral(long s) throws ClassNotFoundException {
		/*
		Statement statement = SQL.initialMySQL();
		return DataBase.GetIntegral(s, statement);
		*/
		System.out.println("get Integral:" + s);
		return UserManager.getInstance().getLoginUserByHashcode(s).getScore();
	}
	
	public String selectUserName(long id) throws ClassNotFoundException {
		/*
		Statement statement = SQL.initialMySQL();
		UserName = DataBase.GetMember(id,statement);
		statement.close();
		return UserName;
		*/
		return UserManager.getInstance().getLoginUserByHashcode(id).getScreenName();
	}

	public long getLoginHash() {
		return loginHash;
	}

	public void setLoginHash(long loginHash) {
		this.loginHash = loginHash;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public String getMemberId() {
		return MemberId;
	}
	
	public void setUserName(String s) {
		UserName = s;
	}
	
	public void setPassword(String s) {
		Password = s;
	}
	
	public void setMemberId(String s) {
		MemberId = s;
	}
	
}
