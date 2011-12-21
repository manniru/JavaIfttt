package org.wzz.ifttt.response.Member;

import org.joe.ifttt.server.manager.DataManager;
import org.joe.ifttt.server.manager.UserManager;

public class Login {
	private String MemberId;
	private String UserName;
	private String Password;
	private long loginHash;
	
	
	public long login() throws ClassNotFoundException {
		/***/
		
		this.loginHash = UserManager.getInstance().loginUser(MemberId, Password);
		this.UserName = UserManager.getInstance().getLoginUserByHashcode(loginHash).getScreenName();
		
		/*
		Statement statement = SQL.initialMySQL();
		if(Password.equals(DataBase.GetMemberkey(MemberId, statement))) {
			return DataBase.GetMember(MemberId, statement);
		}
		else return null;
		*/
		return this.loginHash;
	}

	public String getLevel(String s) throws ClassNotFoundException {
		
		/*
		Statement statement = SQL.initialMySQL();
		return DataBase.GetLevel(s, statement);
		*/
		return DataManager.getInstance().getUserTest(s).getUserLevel().toString();
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
