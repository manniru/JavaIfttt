package org.wzz.ifttt.response.Member;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import org.joe.ifttt.server.manager.UserManager;
import org.joe.ifttt.server.user.CommonMessage;

/**
 * TODO:
 *	getMessageCountByAuthcode
 * 	setDataByAuthcode
 * */
public class Message {
	private long authCode;
	private int messageCount;
	private String[] message;
	private String[] messageSender;
	private static int MAXLENGTH = 100;
	
	public Message() {
		messageCount = 0;
		message = new String[MAXLENGTH];
		messageSender = new String[MAXLENGTH];
	}
	
	public int getMessageCountByAuthcode(long authcode) 
			throws ClassNotFoundException, SQLException {
		messageCount = (UserManager.getInstance().getMessages(authcode, 1)).size();//Wei
		return messageCount;
	}
	
	public void setDataByAuthcode(long authcode) throws ClassNotFoundException, SQLException {
		Vector<CommonMessage> msgs = UserManager.getInstance().getMessages(authcode, 1);
		Iterator<CommonMessage> it = msgs.iterator();
		int i = 0;
		while (it.hasNext()) {
			CommonMessage msg = it.next();
			System.out.println("***In" + msg.getSenderString() + msg.getContent());
			message[i] = "FROM: " + msg.getSenderString() + "  TO: " + msg.getReceiverString();
			messageSender[i] = msg.getContent();
			i ++ ;
		}
		/*
		String[] tempSet = null;
		//Wei TODO~~~~
		for(int i=0;i<messageCount;i++) {
			message[i] = tempSet[0+2*i];
			messageSender[i] = tempSet[1+2*i];
		
		}*/
	}
	
	public String getMessage(int i) {
		return message[i];
	}
	
	public String getMessageSender(int i) {
		return messageSender[i];
	}
	
	public void sendMessage(String sender,String message) {
		System.out.println("Send Message:" + message + " to :" + sender);
	}

	public long getAuthCode() {
		return authCode;
	}

	public void setAuthCode(long authCode) {
		this.authCode = authCode;
	}
	
}
