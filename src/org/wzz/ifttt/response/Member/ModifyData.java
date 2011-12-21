package org.wzz.ifttt.response.Member;

import org.joe.ifttt.server.manager.UserManager;

public class ModifyData {
	private long userhash;
	private String MemberId;
	private String[] Datatemp;
	
	public String[] getDatatemp() throws ClassNotFoundException {
		/*
		Statement statement = SQL.initialMySQL();
		Datatemp = DataBase.GetPersonalData(MemberId, statement);
		*/
		Datatemp = UserManager.getInstance().getLoginUserByHashcodeString(userhash);
		return Datatemp;
	}
	
	public void setDatatemp(String screenname, String email) {
		/*
		Statement statement = SQL.initialMySQL();
		DataBase.ModifyPersonalData(MemberId, Name, Date.valueOf(Birthday), City, PhoneNumber, EMail, statement);
		*/
		UserManager.getInstance().modifyUserByHashcode(userhash, screenname, email);
	}
	
	public void setUserhash(long code) {
		this.userhash = code;
	}
	
	public long getUserhash() {
		return this.userhash;
	}
	public void setMemberId(String id) {
		MemberId = id;
	}
	
	public String getMemberId() {
		return MemberId;
	}
}
