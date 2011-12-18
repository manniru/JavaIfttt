package org.joe.ifttt.server.manager;
/**
 * File: 			DataManage.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/10
 * Description:
 * The definition of data manager, one of the managers
 * be responsible for data managing
 * Provides a convenient interface to server and other managers
 * implement the operation of raw data
 */
public class DataManager {
	/***/
	private static DataManager _manager = null;
	private static int instanceNum = 0;
	
	public static DataManager getInstance() {
		/**get instance of task manager*/
		if (_manager  == null) {
			instanceNum ++ ;
			_manager = new DataManager();
			return _manager;
		}
		else {
			return _manager;
		}
	}
}
