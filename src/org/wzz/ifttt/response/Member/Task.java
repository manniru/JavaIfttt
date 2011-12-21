package org.wzz.ifttt.response.Member;

import java.sql.SQLException;

import org.joe.ifttt.server.manager.TaskManager;
import org.joe.ifttt.server.manager.UserManager;
import org.joe.ifttt.server.type.UserLevel;

public class Task {
	private String MemberId;
	private int TaskCount;
	private String[] TaskId;
	private String[] ThisId;
	private String[] ThatId;
	private String[] time;
	private String[] IfRun;
	private static int MAXLENGTH=100;
	
	public Task() {
		TaskId = new String[MAXLENGTH];
		ThisId = new String[MAXLENGTH];
		ThatId = new String[MAXLENGTH];
		time = new String[MAXLENGTH];
		IfRun = new String[MAXLENGTH];
		TaskCount = 0;
	}
	public int getTaskCount() {
		return TaskCount;
	}
	
	public void setMemberId(String id) {
		MemberId = id;
	}
	
	public void setTaskCount() throws ClassNotFoundException, SQLException {
		/*
		Statement statement = SQL.initialMySQL();
		TaskCount = DataBase.GetTaskCount(MemberId, statement);
		statement.close();
		*/
	}
	
	public void getMemberTask(long code) throws ClassNotFoundException, SQLException {
		/*
		Statement statement = SQL.initialMySQL();
		String[] temp = DataBase.GetMemberTask(MemberId, statement);
		*/
		String[] temp = TaskManager.getInstance().getTasksByUser(code);
		int tasknum = UserManager.getInstance().getLoginUserByHashcode(code).getUserTask().size();
		for(int i = 0; i < tasknum; i++) {
			TaskId[i] = temp[0+5*i];
			ThisId[i] = temp[1+5*i];
			ThatId[i] = temp[2+5*i];
			time[i] = temp[3+5*i];
			IfRun[i] = temp[4+5*i];
		}
	}
	
	public String getTaskId(int i) {
		return TaskId[i];
	}
	
	public String getThisId(int i) {
		return ThisId[i];
	}
	
	public String getThatId(int i) {
		return ThatId[i];
	}
	
	public String gettime(int i) {
		return time[i];
	}
	
	public String getIfRun(int i) {
		if(IfRun[i].equals("0")) return "Unactive";
		else return "Active";
	}
}
