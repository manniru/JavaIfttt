package org.wzz.ifttt.database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;


public class DataBase {
	//会员注册使用接口
	public static boolean InsertMember(String Id,String Name,String Key,Statement statement) throws ClassNotFoundException {
		String ExeString = "insert into Member values('" + Id + "','" + Name + "','" + Key + "')"; 
		try {
			@SuppressWarnings("unused")
			int temp = SQL.SQLupdate(ExeString,statement);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	//用户昵称返回接口
	public static String GetMember(String Id,Statement statement) throws SQLException, ClassNotFoundException {
		String ExeString = "select MemberName from Member where MemberId = '" + Id + "'";
		ResultSet tempSet = SQL.SQLexe(ExeString,statement);
		while(tempSet.next()) {
			return tempSet.getString(1);
		}
		return null;
	}
	
	//用户余额返回接口
	public static float GetMoney(String Id,Statement statement) throws SQLException, ClassNotFoundException {
		String ExeString = "select Money from account where MemberId = '" + Id + "'";
		ResultSet tempSet = SQL.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			return tempSet.getFloat(1);
		}
		return 0;
	}
	
	//创建任务时消费记录增加接口
	public static boolean AddConsume(String MemberId,int ThisId,int ThatId,Time time,double discount,double cost,double consume,Statement statement) throws ClassNotFoundException {
		String ExeString = "insert into Consume values('" + MemberId + "','" + time + "'," + cost + "," + ThisId + "," + ThatId + "," + discount + "," + consume + ")";
		try {
			@SuppressWarnings("unused")
			int temp = SQL.SQLupdate(ExeString, statement);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	//第一次增加会员资料接口
	public static boolean AddPersonalData(String MemberId,String Name,Date Birthday,String City,String PhoneNumber,String E_Mail,Statement statement) throws ClassNotFoundException {
		String ExeString = "insert into PersonalData values('" + MemberId + "','" + Name + "','" + Birthday + "','" + City + "','" + PhoneNumber + "','" + E_Mail + "')";
		try {
			@SuppressWarnings("unused")
			int temp = SQL.SQLupdate(ExeString, statement);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	//修改会员资料接口
	public static boolean  ModifyPersonalData(String MemberId,String Name,Date Birthday,String City,String PhoneNumber,String E_Mail,Statement statement) throws ClassNotFoundException {
		String ExeString = "update PersonalData set RealName='" + Name + "',Birthday='" + Birthday + "',City='" + City + "',PhoneNumber='" + PhoneNumber + "',EMail='" + E_Mail + "' where MemberId='" + MemberId + "'";
		try {
			@SuppressWarnings("unused")
			int temp = SQL.SQLupdate(ExeString, statement);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	//获取会员资料接口
	public static String[] GetPersonalData(String Id,Statement statement) throws SQLException, ClassNotFoundException {
		String ExeString = "select RealName,City,Birthday,PhoneNumber,EMail from PersonalData where MemberId = '" + Id + "'";
		ResultSet tempSet = SQL.SQLexe(ExeString, statement);
		int line = 0;
		while(tempSet.next()) {
			line++;
		}
		String[] resultStrings = new String[tempSet.getMetaData().getColumnCount()*line];
		tempSet.beforeFirst();
		int linenow = 0;
		while(tempSet.next()) {
			for(int i=0;i<tempSet.getMetaData().getColumnCount();i++) {
				resultStrings[tempSet.getMetaData().getColumnCount()*linenow+i] = tempSet.getString(i+1);
			}
			linenow++;
		}
		return resultStrings;
	}
	
	//获取消费记录接口
	public static String[] GetConsume(String Id,Statement statement) throws SQLException, ClassNotFoundException {
		String ExeString = "select Time,Consume from Consume where MemberId = '" + Id + "'";
		ResultSet tempSet = SQL.SQLexe(ExeString, statement);
		int line = 0;
		while(tempSet.next()) {
			line++;
		}
		String[] resultStrings = new String[tempSet.getMetaData().getColumnCount()*line];
		tempSet.beforeFirst();
		int linenow = 0;
		while(tempSet.next()) {
			for(int i=0;i<tempSet.getMetaData().getColumnCount();i++) {
				resultStrings[tempSet.getMetaData().getColumnCount()*linenow+i] = tempSet.getString(i+1);
			}
			linenow++;
		}
		return resultStrings;
	}
	
	//会员退会时删除记录接口
	public static boolean DeleteMember(String MemberId,Statement statement) throws ClassNotFoundException {
		String ExeString1 = "delete from Member where MemberId='" + MemberId + "'";
		String ExeString2 = "delete from Consume where MemberId='" + MemberId + "'";
		String ExeString3 = "delete from Task where MemberId='" + MemberId + "'";
		String ExeString4 = "delete from PersonalData where MemberId='" + MemberId + "'";
		String ExeString5 = "delete from Account where MemberId='" + MemberId + "'";
		try {
			@SuppressWarnings("unused")
			int temp1 = SQL.SQLupdate(ExeString1,statement);
			@SuppressWarnings("unused")
			int temp2 = SQL.SQLupdate(ExeString2,statement);
			@SuppressWarnings("unused")
			int temp3 = SQL.SQLupdate(ExeString3,statement);
			@SuppressWarnings("unused")
			int temp4 = SQL.SQLupdate(ExeString4,statement);
			@SuppressWarnings("unused")
			int temp5 = SQL.SQLupdate(ExeString5,statement);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	//获取正在执行的任务列表接口
	public static int[] GetRunTask(Statement statement) throws SQLException, ClassNotFoundException {
		String ExeString = "select TaskId from Task where IfRun=1";
		ResultSet tempSet = SQL.SQLexe(ExeString, statement);
		int line = 0;
		while(tempSet.next()) {
			line++;
		}
		int[] resultInts = new int[tempSet.getMetaData().getColumnCount()*line];
		tempSet.beforeFirst();
		int linenow = 0;
		while(tempSet.next()) {
			for(int i=0;i<tempSet.getMetaData().getColumnCount();i++) {
				resultInts[tempSet.getMetaData().getColumnCount()*linenow+i] = tempSet.getInt(i+1);
			}
			linenow++;
		}
		return resultInts;
	}
	
	//获取任务详细信息接口
	public static String[] GetTask(int TaskId,Statement statement) throws SQLException, ClassNotFoundException {
		String ExeString = "select MemberId,ThisId,ThatId,Time,IfRun from Task where TaskId = " + TaskId;
		ResultSet tempSet = SQL.SQLexe(ExeString, statement);
		int line = 0;
		while(tempSet.next()) {
			line++;
		}
		String[] resultStrings = new String[tempSet.getMetaData().getColumnCount()*line];
		tempSet.beforeFirst();
		int linenow = 0;
		while(tempSet.next()) {
			for(int i=0;i<tempSet.getMetaData().getColumnCount();i++) {
				resultStrings[tempSet.getMetaData().getColumnCount()*linenow+i] = tempSet.getString(i+1);
			}
			linenow++;
		}
		return resultStrings;
	}
}
