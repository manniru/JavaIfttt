package org.joe.ifttt.server.task.event;

import java.util.List;

import javax.mail.MessagingException;

import org.joe.ifttt.server.channel.Channel;
import org.joe.ifttt.server.channel.WeiboChannel;
import org.joe.ifttt.server.user.CommonUser;

import com.sina.weibo4j.Status;

public class GetWeiboEvent extends BasicEvent implements This{
	private WeiboChannel weibo;
	private String pattern;
	private String screenName;
	public GetWeiboEvent() {
		// TODO Auto-generated constructor stub
	}
	public GetWeiboEvent(CommonUser user) throws MessagingException {
		setWeibo((WeiboChannel) user.getChannel("weibo"));
	}
	public GetWeiboEvent(String pat) {
		this.pattern = pat;
	}
	public GetWeiboEvent(String screeName, Long id, String pat) {
		// TODO Auto-generated constructor stub
		this.screenName = screeName;
		pattern = pat;
	}
	@Override
	public boolean thisEvent() {
		// TODO Auto-generated method stub
		Status status = weibo.getStatusByName(screenName);
		String textString = status.getText();
		return textString.contains(pattern);
	}

	@Override
	public void setChannel(Channel channel) {
		// TODO Auto-generated method stub
		this.setWeibo((WeiboChannel) channel);
	}
	
	public WeiboChannel getWeibo() {
		return weibo;
	}
	public void setWeibo(WeiboChannel weibo) {
		this.weibo = weibo;
	}
}
