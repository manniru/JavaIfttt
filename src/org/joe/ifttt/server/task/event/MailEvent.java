package org.joe.ifttt.server.task.event;
/**
 * File: 			MailEvent.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/12
 * Description:
 * The definition of mail event, we will define some mail events:
 * 		1.receive a new mail; 					(DONE)
 * 		2.receive a new mail from the person; 	(NOT DONE)
 * 		...
 */
import javax.mail.MessagingException;

import org.joe.ifttt.server.channel.Channel;
import org.joe.ifttt.server.channel.MailChannel;
import org.joe.ifttt.server.user.CommonUser;

public class MailEvent extends BasicEvent implements This{
	private int lastNum = 9999;
	private MailChannel mail;
	
	public MailEvent() {
		
	}
	public MailEvent(CommonUser user) throws MessagingException {
		mail = (MailChannel) user.getChannel("mail");
	}
	public String toString() {
		return super.toString() + "When " + mail.getUser().toString() + " receive new mail \n";
	}
	@Override
	public boolean thisEvent() {
		try {
			int nowNum = mail.numOfMail();
			System.out.println("Now Num: " + nowNum + " ,last num " + lastNum);
			if (nowNum > lastNum) {	
				return true;
			}
			lastNum = nowNum;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void setChannel(Channel channel) {
		this.mail = (MailChannel) channel;
	}
}
