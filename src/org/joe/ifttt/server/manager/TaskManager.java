package org.joe.ifttt.server.manager;
/**
 * File: 			TaskManage.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/12
 * Description:
 * The definition of task manager, one of the managers
 * be responsible for task managing
 * Provides a convenient interface to server and other managers,
 * implement the operations of tasks
 * process all requests about task 
 */
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.joe.ifttt.server.task.*;
import org.joe.ifttt.server.task.action.That;
import org.joe.ifttt.server.task.event.This;
import org.joe.ifttt.server.type.TaskState;
import org.joe.ifttt.server.user.CommonUser;

public class TaskManager {
	/***TaskManager class to manager user's tasks*/
	protected final static int MAX_NUM_OF_TASKS = 10000;
	
	private static TaskManager _manager = null;
	private static int instanceNum = 0;
	private Long currentTaskNum;
	
	private Map<Long, TaskFrame> tasks;
	
	public static TaskManager getInstance() {
		/**get instance of task manager*/
		if (_manager  == null) {
			instanceNum ++ ;
			_manager = new TaskManager();
			return _manager;
		}
		else {
			return _manager;
		}
	}
	
	protected TaskManager() {
		/**constructor of this class*/
		tasks = new HashMap<Long, TaskFrame>(MAX_NUM_OF_TASKS);
		currentTaskNum = new Long(0);
	}
	public void loadTasks () {
		
	}
	public long insertTask (long userHash, String taskname, This event, 
			String eType, That action, String aType, boolean repeat) {
		/**Insert a task to the task list, and update the database
		 * PARAMETERS:
		 * 		@param userHash: the hash code of the user
		 * 		@param taskname: task name
		 * 		@param event	: this event object
		 * 		@param eType	: this event's type , format:
		 * 						"EVENT-$(ctype)-$(etype)"
		 * 						$(ctype): channel type
		 * 						$(etype): event type of the channel
		 * 								  eg.   receive mail
		 * 										after time
		 * 		@param action	: that action object
		 * 		@param aType	: that action's type , format:
		 * 						"ACTION-$(ctype)-$(atype)"
		 * 						$(ctype): channel type
		 * 						$(atype): action type of the channel
		 * 								  eg.	send mail
		 * 										update weibo
		 * 		@param repeat	: is a repeat task
		 * RETURN: 
		 * 		success	: task id
		 * 		fail	: -1
		 * */
		CommonUser currentUser = UserManager.getInstance().getLoginUserByHashcode(userHash);
		if (null != currentUser) {
			String eChannelType = eType.split("-")[1]; //get event channel type
			String aChannelType = aType.split("-")[1]; //get action channel type
			System.out.println(eChannelType);
			System.out.println(aChannelType);
			event.setChannel(currentUser.getChannel(eChannelType));
			action.setChannel(currentUser.getChannel(aChannelType));
			
			TaskFrame newTask = new TaskFrame(event, action);
			currentTaskNum ++;
			newTask.setTaskId(currentTaskNum.longValue());
			newTask.setTaskName(taskname);
			newTask.setTaskState(TaskState.INIT);
			newTask.setTaskType(eType + "|" + aType);
			newTask.setOwner(currentUser.getUsername());
			newTask.setRepeat(repeat);
			newTask.setCreateTime((new Date()).toGMTString());
			tasks.put(currentTaskNum, newTask);
			UserManager.getInstance().getLoginUserByHashcode(userHash).getUserTask().add(currentTaskNum);
			return newTask.getTaskId();
		}
		System.out.println("error insert");
		return -1;
	}
	
	public TaskFrame getTaskById(long id) {
		return tasks.get(id);
	}
	
	public String[] getTasksByUser(long userHash) {
		CommonUser currentUser = UserManager.getInstance().getLoginUserByHashcode(userHash);
		if (currentUser == null) {
			return null;
		}
		Vector<Long> taskIds = currentUser.getUserTask();
		if (taskIds.size() == 0) {
			return null;
		}
		
		java.util.Iterator<Long> it = taskIds.iterator();
		String[] tasksEl = new String[50];
		int i = 0;
		while(it.hasNext()) {
			Long bTask = it.next();
			TaskFrame currentTask = getTaskById(bTask.longValue());
			tasksEl[0+5*i] = String.valueOf(currentTask.getTaskId());
			tasksEl[1+5*i] = currentTask.getTaskType().split("|")[0];
			tasksEl[2+5*i] = currentTask.getTaskType().split("|")[1];
			tasksEl[3+5*i] = currentTask.getCreateTime();
			tasksEl[4+5*i] = String.valueOf(currentTask.getTaskState());
			i ++;
		}
		return tasksEl;
	}
	
	public boolean setTaskStateById (long id, TaskState state) {
		/**set the task state by task id*/
		tasks.get(id).setTaskState(state);
		return true;
	}
	
	public boolean removeTask (TaskFrame task) {
		/**Remove the task by task object*/
		return removeTaskById(task.getTaskId());
	}
	
	public boolean removeTaskById (long taskId) {
		/**Remove the task by task id*/
		TaskFrame currentTask = tasks.get(taskId);
		if (currentTask != null) {
			//task exists
			if (currentTask.getTaskState() == TaskState.RUN) {
				//the state of task is RUN, remove from thread Pool first
				long threadId = tasks.get(taskId).getThreadId();
				ThreadManager.getInstance().removeTaskByIdFromThreadPool(threadId);
			}
			tasks.remove(taskId);
			return true;
		}
		return false;
	}
	
	public long allocTaskId() {
		/**allocation a task id which must be unique*/
		return -1;
	}
	
	public boolean startTask(long taskId) {
		/**start a task*/
		TaskFrame currentTask = tasks.get(taskId);
		if (null != currentTask) {
			ThreadManager.getInstance().putTaskInThreadPool(currentTask);
			return true;
		}
		return false;
	}
	
	public boolean pauseTask(long taskId) {
		/**pause a task*/
		TaskFrame currentTask = tasks.get(taskId);
		if (currentTask != null) {
			long threadId = currentTask.getThreadId();
			ThreadManager.getInstance().removeTaskByIdFromThreadPool(threadId);
			currentTask.setTaskState(TaskState.PAUSE);
			return true;
		}
		return false;
	}
	
	public boolean stopTask(long taskId) {
		/**stop a task*/
		TaskFrame currentTask = tasks.get(taskId);
		if (currentTask != null) {
			long threadId = currentTask.getThreadId();
			ThreadManager.getInstance().removeTaskByIdFromThreadPool(threadId);
			currentTask.setTaskState(TaskState.STOP);
			return true;
		}
		return false;
	}
	
	public boolean doneTask(long taskId) {
		/**complete a task*/
		TaskFrame currentTask = tasks.get(taskId);
		if (currentTask != null) {
			long threadId = currentTask.getThreadId();
			ThreadManager.getInstance().removeTaskByIdFromThreadPool(threadId);
			currentTask.setTaskState(TaskState.DONE);
			return true;
		}
		return false;
	}
	
}
