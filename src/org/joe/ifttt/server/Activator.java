package org.joe.ifttt.server;

import org.joe.ifttt.server.manager.TaskManager;
import org.joe.ifttt.server.task.TaskFrame;

public class Activator implements Runnable {
	private TaskFrame task;
	private int oknok = 1;

	public Activator() {
		// TODO Auto-generated constructor stub
	}
	public Activator(TaskFrame task) {
		this.task = task;
	}
	public void run() {
		while (oknok == 1) {
			if (task.thisEvent()) {
				task.thatAction();
				if (!task.isRepeat()) {
					TaskManager.getInstance().doneTask(task.getTaskId());
				}
			}
			try {
				//sleep 10s
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		
	}
}
