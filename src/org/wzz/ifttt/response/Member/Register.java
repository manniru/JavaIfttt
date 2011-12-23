package org.wzz.ifttt.response.Member;



import org.joe.ifttt.server.manager.UserManager;


public class Register {
	public void Store_Data(String id, String userName, String key, String email) {
		UserManager.getInstance().createUser(id, key, userName, email);
		
		/*
		Statement statement1 = SQL.initialMySQL();
		DataBase.InsertMember(id, userName, key, statement1);
		Statement statement2 = SQL.initialMySQL();
		DataBase.AddPersonalData(id, realName, Date.valueOf(birthday), city, phoneNumber, email, statement2);
		statement1.close();
		statement2.close();
		*/
	}
}
