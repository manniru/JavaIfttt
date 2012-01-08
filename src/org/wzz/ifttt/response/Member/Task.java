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

import com.sun.jndi.url.ldaps.ldapsURLContextFactory;
import com.sun.org.apache.bcel.internal.generic.NEW;
/**
 *  * 2011/12/28 1:33 add the functions:
 * 		runTask
 * 		stopTask
 * */
public class Task {
	private final static int MAX_TASK_NUM = 10;
	private String userName;
	private long authCode;
	private int taskCount;
	private String[] TaskId;
	private String[] ThisId;
	private String[] ThatId;
	private String[] time;
	private String[] IfRun;
	private static int MAXLENGTH=100;
	
	public Task() {
		TaskId = new String[MAX_TASK_NUM];
		ThisId = new String[MAX_TASK_NUM];
		ThatId = new String[MAX_TASK_NUM];
		time = new String[MAX_TASK_NUM];
		IfRun = new String[MAX_TASK_NUM];
	}
	
	public boolean runTask(long authcode, long taskId) {
		return true;
	}
	
	public boolean stopTask(long authcode, long taskId) {
		return true;
	}
	
	public int getTaskCountByAuthcode(long authcode) {
		
		taskCount = 3;/*need to add the impl to get the count of the task*/
		int tasknum = UserManager.getInstance().getLoginUserByHashcode(authcode).getUserTask().size();
		return tasknum;
	}
	
	public void setUserName(String id) {
		userName = id;
	}
	
	public void createTask(long authCode, String taskname, String taskDscrip, String thisDscrip, 
			String thisType, String thatDscrip, String thatType, boolean repeat) 
					throws SQLException, ClassNotFoundException {
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
		String thisParams = "";
		String thatParams = "";
		CommonUser currentUser = UserManager.getInstance().getLoginUserByHashcode(authCode);
		String[] thisPara = thisDscrip.split("#&#");
		String[] thatPara = thatDscrip.split("#&#");
		
		if (thisType.equals("time-after")) {
			String time[] = thisPara[0].split("-");
			event = new TimeEvent(time[0], time[1], time[2], time[3], time[4]);
			System.out.println(time[0] + "," + time[1] + "," + time[2] + "," + time[3] + "," + time[4]);
			thisTypeString = "EVENT-time-after";
			thisParams = thisParams + thisPara[0];
		}
		else if (thisType.equals("weibo-update")) {
		
			event = new GetWeiboEvent(thisPara[0], null, thisPara[1]);
			thisTypeString = "EVENT-weibo-get";
			thisParams = thisParams + thisPara[0];
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
			thatParams = thatParams + thatPara[2];
			System.out.println("***IN TASK.java, thatParams :" + thatParams);
		}
		else if (thatType.equals("gmail-send")) {
			CommonContent content = new CommonContent();
			ChannelUser mailUser = new ChannelUser();
			mailUser.setUsername(thatPara[0]);
			mailUser.setPassword(thatPara[1]);
			UserManager.getInstance().addChannel(authCode, "mail", mailUser);
			
			content.setTextString(thatPara[2]);
			action = new SendMailAction(currentUser, content, thatPara[3]);
			thatTypeString = "ACTION-mail-send";
			thatParams = thatParams + thatPara[2] + "|" + thatPara[3];
		}
		System.out.println("A NEW TASK: thisType: " + thisTypeString + "thatType: " + thatTypeString);
		long taskId = TaskManager.getInstance().insertTask(
				authCode, taskname, event, thisTypeString, thisParams, 
				action, thatTypeString, thatParams, repeat);
		//TaskManager.getInstance().startTask(taskId);
	}
	public void getMemberTask(long code) throws ClassNotFoundException, SQLException {
		System.out.println("**in get member task func");
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
		String state = IfRun[i];
		if (state.equals("RUN")) {
			return "ACTIVE : " + "RUN";
		}
		else {
			return "UNACTIVE : " + IfRun[i];
		}
	}
}
