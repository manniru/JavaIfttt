package org.joe.ifttt.server.task.event;

import org.joe.ifttt.server.type.ChannelType;

public abstract class BasicEvent implements This {
	protected ChannelType channelType;
	protected String eventType;

	public BasicEvent() {
		// TODO Auto-generated constructor stub
	}

}
