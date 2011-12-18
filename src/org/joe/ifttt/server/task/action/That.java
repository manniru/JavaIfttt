package org.joe.ifttt.server.task.action;
/**
 * File: 			That.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/12
 * Description:
 * The Interface of that actions classes
 */
import org.joe.ifttt.server.channel.Channel;

public interface That {
	public void thatAction();
	public void setChannel(Channel channel);
}
