package org.wzz.ifttt.database;

import java.sql.SQLException;
import java.util.Vector;


public class DBTest {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		//MsgDBfunc.sendMsg(1, "joe", "lala", "i love you");
		//MsgDBfunc.sendMsg(2, "joe", "lala", "i 22 you");
		Vector<String> vector = MsgDBfunc.getMsgByUserName("joe", 1);
		java.util.Iterator<String> it1;
		it1 = vector.iterator();
		while(it1.hasNext()) {
			String reString = it1.next();
			String[] eleString = reString.split(":");
			for (int i = 0; i < eleString.length; i++) {
				System.out.print(i + " : " + eleString[i] + " ,");
			}
			//System.out.println(it1.next());
		}
		System.out.println("---------------------------------------");
		//MsgDBfunc.sendMsg(4, "fd", "joe", "i love yfdfdfou");
	//	MsgDBfunc.sendMsg(5, "fd", "joe", "i fdfd yfdfdfou");

		vector = MsgDBfunc.getMsgByUserName("joe", 2);
		java.util.Iterator<String> it2;
		it2 = vector.iterator();
		while(it2.hasNext()) {
			System.out.println(it2.next());
		}
		System.out.println("---------------------------------------");
		vector = MsgDBfunc.getMsgByUserName("joe", 3);
		java.util.Iterator<String> it3;
		it3 = vector.iterator();
		while(it3.hasNext()) {
			System.out.println(it3.next());
		}
	}

}
