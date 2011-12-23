package org.wzz.ifttt.response.Member;

import java.sql.SQLException;

import javax.mail.MessagingException;

import org.joe.ifttt.server.content.CommonContent;
import org.joe.ifttt.server.manager.TaskManager;
import org.joe.ifttt.server.manager.UserManager;
import org.joe.ifttt.server.task.action.SendMailAction;
import org.joe.ifttt.server.task.action.That;
import org.joe.ifttt.server.task.action.UpdateWeiboAction;
import org.joe.ifttt.server.task.event.GetWeiboEvent;
import org.joe.ifttt.server.task.event.MailEvent;
import org.joe.ifttt.server.task.event.This;
import org.joe.ifttt.server.task.event.TimeEvent;
import org.joe.ifttt.server.type.UserLevel;
import org.joe.ifttt.server.user.ChannelUser;
import org.joe.ifttt.server.user.CommonUser;

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
	
	public void createTask(long authCode, String taskname, String taskDscrip, String thisDscrip, 
			String thisType, String thatDscrip, String thatType, boolean repeat) {
		/**
		 * thisType: time-after,
		 * thisDscrip: 
		 * thatDscrip:
		 * */
		System.out.println(authCode + taskname + taskDscrip + thisDscrip + thisType +
				thatDscrip + thatType + repeat);
		This event = null;
		That action = null;
		String thisTypeString = "";
		String thatTypeString = "";
		CommonUser currentUser = UserManager.getInstance().getLoginUserByHashcode(authCode);
		String[] thisPara = thisDscrip.split("#$#");
		String[] thatPara = thatDscrip.split("#&#");
		
		if (thisType.equals("time-after")) {
			String time[] = thisPara[0].split("-");
			event = new TimeEvent(time[0], time[1], time[2], time[3], time[4]);
			System.out.println(time[0] + "," + time[1] + "," + time[2] + "," + time[3] + "," + time[4]);
			thisTypeString = "EVENT-time-after";
		}
		else if (thisType.equals("weibo-update")) {
			event = new GetWeiboEvent(thisPara[0], null, thisPara[1]);
			thisTypeString = "EVENT-weibo-get";
		}
		else if (thisType.equals("email-receive")) {
			try {
				event = new MailEvent(currentUser);
				thisTypeString = "EVENT-mail-receive";
				
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (thatType.equals("weibo-update")) {
			CommonContent content = new CommonContent();
			ChannelUser weiboUser = new ChannelUser();
			weiboUser.setUsername(thatPara[0]);
			weiboUser.setPassword(thatPara[1]);
			UserManager.getInstance().addChannel(authCode, "weibo", weiboUser);
			System.out.println(thatPara[0] +","+ thatPara[1]);
			content.setTextString(thatPara[2]);
			action = new UpdateWeiboAction(currentUser, content);
			thatTypeString = "ACTION-weibo-update";
			
		}
		else if (thatType.equals("email-send")) {
			CommonContent content = new CommonContent();
			ChannelUser mailUser = new ChannelUser();
			mailUser.setUsername(thatPara[0]);
			mailUser.setPassword(thatPara[1]);
			UserManager.getInstance().addChannel(authCode, "mail", mailUser);
			
			content.setTextString(thatPara[2]);
			action = new SendMailAction(currentUser, content, thatPara[3]);
			thatTypeString = "ACTION-mail-send";
		}
		System.out.println("A NEW TASK: thisType: " + thisTypeString + "thatType: " + thatTypeString);
		long taskId = TaskManager.getInstance().insertTask(
				authCode, taskname, event, thisTypeString, action, thatTypeString, repeat);
		TaskManager.getInstance().startTask(taskId);
		
		
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
