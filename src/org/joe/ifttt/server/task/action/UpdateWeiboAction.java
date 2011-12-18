package org.joe.ifttt.server.task.action;
/**
 * File: 			UpdateWeiboAction.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/12
 * Description:
 * The definition of update weibo action;
 * we should add pic content for weibo update;
 */
import org.joe.ifttt.server.channel.Channel;
import org.joe.ifttt.server.channel.WeiboChannel;
import org.joe.ifttt.server.content.CommonContent;
import org.joe.ifttt.server.user.CommonUser;

public class UpdateWeiboAction extends BasicAction implements That {
	private WeiboChannel weibo;
	private CommonContent content;
	public UpdateWeiboAction () {
		
	}
	public UpdateWeiboAction (CommonContent content) {
		this.content = content;
	}
	public UpdateWeiboAction (CommonUser user, CommonContent content) {
		weibo = (WeiboChannel) user.getChannel("weibo");
		this.content = content;
	}
	@Override
	public void setChannel(Channel channel) {
		// TODO Auto-generated method stub
		weibo = (WeiboChannel) channel;
	}
	@Override
	public void thatAction() {
		// TODO Auto-generated method stub
		weibo.update(content);
	}
}