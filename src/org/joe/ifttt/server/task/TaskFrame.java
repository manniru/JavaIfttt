package org.joe.ifttt.server.task;
/**
 * File: 			TaskFrame.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/10
 * Description:
 * The definition of TaskFramer, all Tasks user this frame
 * we specify different by different events and actions, and 
 * the Property also give the info of the tasks, its format 
 * is "eventChannelType|actionChannelType"
 */
import org.joe.ifttt.server.channel.Channel;
import org.joe.ifttt.server.task.action.That;
import org.joe.ifttt.server.task.event.This;
import org.joe.ifttt.server.type.TaskState;

public class TaskFrame implements This, That{
	/***This is the Task Frame Class Definition*/
	private long 		taskId;
	private String 		taskName;
	private long 		threadId; // need fix
	private TaskState 	taskState;
	private String	 	taskType;
	private This 		trigger;
	private That 		action;
	private String		owner;
	private boolean		repeat;
	
	public TaskFrame() {
		// TODO Auto-generated constructor stub
	}
	
	public TaskFrame(This t, That a) {
		this.trigger = t;
		this.action = a;
	}

	@Override
	public boolean thisEvent() {
		// TODO Auto-generated method stub
		return trigger.thisEvent();
	}

	@Override
	public void thatAction() {
		// TODO Auto-generated method stub
		action.thatAction();
	}
	
	public String toString() {
		/***to get the basic info of the task*/
		String string;
		string = "BASIC|ID:" + taskId + "|NAME:" + taskName + 
		"|STATE:" + taskState + "|TYPE:" + taskType + "\n";
		return string;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public long getTaskId() {
		return taskId;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskName() {
		return taskName;
	}

	public void setThreadId(long threadId) {
		this.threadId = threadId;
	}
	public long getThreadId() {
		return threadId;
	}
	
	public void setTaskState(TaskState taskState) {
		this.taskState = taskState;
	}
	public TaskState getTaskState() {
		return taskState;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getTaskType() {
		return taskType;
	}

	@Override
	public void setChannel(Channel channel) {
		// TODO Auto-generated method stub
		
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public boolean isRepeat() {
		return repeat;
	}
}
