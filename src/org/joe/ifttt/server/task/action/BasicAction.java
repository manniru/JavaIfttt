package org.joe.ifttt.server.task.action;

import org.joe.ifttt.server.type.ChannelType;

public abstract class BasicAction implements That {
	protected ChannelType channelType;
	protected String actionType;
	
	public BasicAction() {
		// TODO Auto-generated constructor stub
	}

}
