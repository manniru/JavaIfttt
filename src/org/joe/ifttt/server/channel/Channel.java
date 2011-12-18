package org.joe.ifttt.server.channel;

import org.joe.ifttt.server.type.ChannelType;

/**
 * File: 			Channel.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/10
 * Description:
 * Just an abstract class
 */
public abstract class Channel {
	/**this is an abstract class for channel*/
	private ChannelType channelType;

	public Channel() {
		// TODO Auto-generated constructor stub
	}

	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}

	public ChannelType getChannelType() {
		return channelType;
	}
}
