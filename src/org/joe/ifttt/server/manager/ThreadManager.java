package org.joe.ifttt.server.manager;
/**
 * File: 			ThreadManage.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/10
 * Description:
 * The definition of Thread manager, one of the managers.It takes 
 * responsibility for Thread managing. It provides a convenient 
 * interface to server and other managers, implements the operations 
 * of Threads and processes all requests about Thread. 
 */
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.joe.ifttt.server.Activator;
import org.joe.ifttt.server.task.TaskFrame;


public class ThreadManager {
	private final int MAX_THREAD = 100;
	private static ThreadManager _manager = null;
	private static int instanceNum = 0;
	private ExecutorService executor;
	private Map<Long, Thread> threads;
	
	public static ThreadManager getInstance() {
		/**get instance of thread manager*/
		if (_manager  == null) {
			instanceNum ++ ;
			_manager = new ThreadManager();
			return _manager;
		}
		else {
			return _manager;
		}
	}
	
	protected ThreadManager() {
		 executor = Executors.newFixedThreadPool(MAX_THREAD/100);
		 threads = new HashMap<Long, Thread>(MAX_THREAD);
	}
	
	public long putTaskInThreadPool(TaskFrame task) {
		/**put a task to the thread pool */
		//executor.execute(new Activator(task));
		//executor.shutdown();
		if (threads.size() >= MAX_THREAD) {
			return -1;
		}
		Thread thread = new Thread(new Activator(task));
		thread.start();
		threads.put(thread.getId(), thread);
		return thread.getId();
	}
	
	public boolean removeTaskByIdFromThreadPool(long threadId) {
		/**remove a task from the thread pool*/
		Thread currentThread = threads.get(threadId);
		if (currentThread != null) {
			currentThread.stop();
			threads.remove(threadId);
			return true;
		}
		return false;
	}
}
