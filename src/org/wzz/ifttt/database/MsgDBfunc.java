package org.wzz.ifttt.database;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class MsgDBfunc {
	public static int msgNum() throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String send = "select * from Message";
		ResultSet resultSet = SQLconn.SQLexe(send, statement);
		int line = 0;
		while(resultSet.next()) line ++;
		return line;
	}
	public static boolean sendMsg(int msgid, String sender, String reciever, String content) 
			throws SQLException, ClassNotFoundException {
		if(msgid == -1 || sender == null || reciever == null || content == null) return false;
		else {
			Statement statement = SQLconn.initialMySQL();
			String dbString = "insert into Message values(" + 
					msgid + ",'" + sender + "','" 
					+ reciever + "','" + content + "')";
			SQLconn.SQLupdate(dbString, statement);
			statement.close();
			return true;
		}
	}
	
	public static Vector<String> getMsgByUserName(String username, int filter) throws ClassNotFoundException, SQLException {
		Statement statementSend = SQLconn.initialMySQL();
		Statement statementRecv = SQLconn.initialMySQL();
		String send = "select * from Message where sender = '" + username + "'";
		String recv = "select * from Message where reciever = '" + username + "'";
		ResultSet sendSet = SQLconn.SQLexe(send, statementSend);
		ResultSet recvSet = SQLconn.SQLexe(recv, statementRecv);
		Vector<String> msgs = new Vector<String>(10, 5);
		
	//	int lineSend = 0;
	//	int lineRecv = 0;
	//	while(sendSet.next()) lineSend ++;
	//	while(recvSet.next()) lineRecv ++;
		//String[] sendStrings = new String[sendSet.getMetaData().getColumnCount()*lineSend];
		//String[] recvStrings = new String[sendSet.getMetaData().getColumnCount()*lineRecv];
		
		sendSet.beforeFirst();
		recvSet.beforeFirst();
		
		int linenow = 0;
		if (filter == 1 || filter == 3) {
			while(sendSet.next()) {
				String recordString = "";
				for(int i=0; i<sendSet.getMetaData().getColumnCount(); i++) {
					recordString = recordString + sendSet.getString(i + 1) + ":";
					/*
					sendStrings[sendSet.getMetaData().getColumnCount()*linenow + i] = sendSet.getString(i + 1);
				*/
				}
				msgs.add(recordString);
			}
		}
		
		if (filter == 2 || filter == 3) {
			while(recvSet.next()) {
				String recordString = "";
				for(int i=0; i<recvSet.getMetaData().getColumnCount(); i++) {
					recordString = recordString + recvSet.getString(i + 1) + ":";
					/*
					sendStrings[recvSet.getMetaData().getColumnCount()*linenow + i] = recvSet.getString(i + 1);
					System.out.println(recvSet.getString(i + 1));
					*/
				}
				msgs.add(recordString);
			}
		}
		statementSend.close();
		statementRecv.close();
		
		return msgs;
		/*
		if (filter == 1) {
			//send
			return sendStrings;
		}
		else if (filter == 2) {
			//recv
			return recvStrings;
		}
		else {
			//both
			String[] str = new String[sendStrings.length + sendStrings.length];
			int j = 0;
			for (int i = 0; i < sendStrings.length; i++) {
				str[j] = sendStrings[i];
				j++;
			}
			for (int i = 0; i < recvStrings.length; i++) {
				str[j] = sendStrings[i];
				j++;
			}
			return str;
		}
		*/
	}
}
