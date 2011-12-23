package org.joe.ifttt.server.task.action;
/**
 * File: 			SendMailEvent.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/12
 * Description:
 * The definition of send mail action;
 * send a mail to somebody
 * ! we don't add the receiver now, change the sendSimpleMail function
 * in mail channel!
 */
import org.joe.ifttt.server.channel.Channel;
import org.joe.ifttt.server.channel.MailChannel;
import org.joe.ifttt.server.content.CommonContent;
import org.joe.ifttt.server.user.CommonUser;

public class SendMailAction extends BasicAction implements That {
	private MailChannel mail;
	private CommonContent content;
	private String receiver;
	
	public SendMailAction () {
		
	}
	public SendMailAction (CommonContent content) {
		this.content = content;
	}
	public SendMailAction (CommonUser user, CommonContent content) {
		mail = (MailChannel) user.getChannel("mail");
		this.content = content;
	}
	public SendMailAction (CommonUser user, CommonContent content, String receiver) {
		mail = (MailChannel) user.getChannel("mail");
		this.content = content;
		this.receiver = receiver;
	}
	@Override
	public void setChannel(Channel channel) {
		// TODO Auto-generated method stub
		mail = (MailChannel) channel;
	}
	@Override
	public void thatAction() {
		// TODO Auto-generated method stub
		try {
			mail.sendSimpleMail(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

}
