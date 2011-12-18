package org.joe.ifttt.server.task.event;
/**
 * File: 			This.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/12
 * Description:
 * The Interface of this events classes
 */
import org.joe.ifttt.server.channel.Channel;

public interface This {
	public boolean thisEvent();
	public void setChannel(Channel channel);
}
