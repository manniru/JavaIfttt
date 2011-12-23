package org.wzz.ifttt.response.Member;
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
	
	public int getMessageCountByAuthcode(long authcode) {
		messageCount = 1;//Wei
		return messageCount;
	}
	
	public void setDataByAuthcode(long authcode) {
		String[] tempSet = null;
		//Wei TODO~~~~
		for(int i=0;i<messageCount;i++) {
			message[i] = tempSet[0+2*i];
			messageSender[i] = tempSet[1+5*i];
			
		}
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
