package org.wzz.ifttt.database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 2011/12/28 1:53 add the functions:
 * 		setUserAccount
 * 		getUserAccount
 * 
 * 2012/01/02 0:44
 * 		debug some functions, do after close
 * 		
 * */

public class DBfunction {	
	
	//取得用户账户信息
	public static int getUserAccount(String UserName) throws ClassNotFoundException, SQLException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select Account from useraccount where UserName = '" + UserName + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString,statement);
		while(tempSet.next()) {
			int resultString = Integer.parseInt(tempSet.getString(1));
			statement.close();
			return resultString;
		}
		statement.close();
		return -1;
	}
	//添加用户账户信息
	public static boolean setUserAccount(String UserName, int account) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "Select UserName from member where UserName='" + UserName + "'";
		ResultSet resultset = SQLconn.SQLexe(ExeString, statement);
		if(resultset.next()==false) {
			return false;
		}
		String insertString = "INSERT INTO USERACCOUNT VALUES('" + UserName + "'," + account + ")";
		SQLconn.SQLupdate(insertString, statement);
		statement.close();
		return true;
	}
	
	//用户昵称返回接口
	public static String getNickName(String Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select NickName from Member where UserName = '" + Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString,statement);
		while(tempSet.next()) {
			String result = tempSet.getString(1);
			statement.close();
			return result;
		}
		statement.close();
		return null;
	}
	
	//用户密码返回接口
	public static String getPassword(String Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select Password from Member where UserName = '" + Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString,statement);
		while(tempSet.next()) {
			String result = tempSet.getString(1);
			statement.close();
			return result;
		}
		statement.close();
		return null;
	}
	
	//用户余额返回接口
	/*public static double getMoney(String Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQL.initialMySQL();
		String ExeString = "select Money from account where UserName = '" + Id + "'";
		ResultSet tempSet = SQL.SQLexe(ExeString, statement);
		statement.close();
		while(tempSet.next()) {
			return tempSet.getDouble(1);
		}
		return 0;
	}*/
	
	//用户邮箱返回接口
	public static String getMailAddress(String Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select MailAddress from Member where UserName = '" + Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}
	
	//获取会员等级
	public static String getUserLevel(String id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select UserLevel from Member where UserName='" + id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}

	//获取会员积分
	public static String getScore(String id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select Score from Member where UserName='" + id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}
	
	//获取用户状态接口
	public static String getUserState(String Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select UserState from Member where UserName = '" + Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}
	
	//获取用户渠道接口
	public static String getChannels(String Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select Channels from Member where UserName = '" + Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}
	
	//获取用户任务号接口
	public static String getTasks(String Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select Tasks from Member where UserName = '" + Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}
	
	//获取用户可以创建最大任务数目
	public static String getMaxTask(String Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select MaxTask from Member where UserName = '" + Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}
	
	//获取用户当前拥有任务数
	public static String getNumOfTasks(String Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select NumOfTasks from Member where UserName = '" + Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}
	//更新Member表
	public static boolean setMember(String ManagerPassword,String UserName,String Password,String MailAddress,String NickName,long Score,String UserLevel,String UserState,String Channels,String Tasks,int MaxTask,int NumOfTasks,String date) throws SQLException, ClassNotFoundException {
		if(UserName==null) return false;
		else {	
			Statement statement = SQLconn.initialMySQL();
			String ExeString = "Select UserName from Member where UserName='" + UserName + "'";
			ResultSet resultset = SQLconn.SQLexe(ExeString, statement);
			if(resultset.next()==false) {
				if(Password==null||NickName==null||MailAddress==null||Score==-1||UserLevel==null) return false;
				else {
					String ExeStringA = "insert into Member values('" 
						+ UserName + "','" + Password + "','" + NickName+ "','" 
						+ MailAddress + "','" + Channels + "','" + Tasks 
						+ "'," + + Score + "," + MaxTask + "," + NumOfTasks 
						+ ",'"+ date + "','" + UserLevel + "','" + UserState + "')";
					SQLconn.SQLupdate(ExeStringA, statement);
					statement.close();
					return true;
				}		
			}
			else {
				if(Password!=null) {
					String ExeStringB1 = "update Member set Password = '" + Password + "'" + "where UserName = '" + UserName + "'";
					SQLconn.SQLupdate(ExeStringB1, statement);
				}
				if(NickName!=null) {
					String ExeStringB2 = "update Member set NickName = '" + NickName + "'" + "where UserName = '" + UserName + "'";
					SQLconn.SQLupdate(ExeStringB2, statement);
				}
				if(MailAddress!=null) {
					String ExeStringB3 = "update Member set MailAddress = '" + MailAddress + "'" + "where UserName = '" + UserName + "'";
						SQLconn.SQLupdate(ExeStringB3, statement);
				}
				if(UserState!=null) {
					String ExeStringB4 = "update Member set UserState = '" + UserState + "'" + "where UserName = '" + UserName + "'";
					SQLconn.SQLupdate(ExeStringB4, statement);
				}
				if(Channels!=null) {
					String ExeStringB5 = "update Member set Channels = '" + Channels + "'" + "where UserName = '" + UserName + "'";
					SQLconn.SQLupdate(ExeStringB5, statement);
				}
				if(Tasks!=null) {
					String ExeStringB6 = "update Member set Tasks = '" + Tasks + "'" + "where UserName = '" + UserName + "'";
					SQLconn.SQLupdate(ExeStringB6, statement);
				}
				if(Score!=-1) {
					String ExeStringB7 = "update Member set Score = '" + Score + "'" + "where UserName = '" + UserName + "'";
					SQLconn.SQLupdate(ExeStringB7, statement);
				}
				if(MaxTask!=-1) {
					String ExeStringB8 = "update Member set MaxTask = '" + MaxTask + "'" + "where UserName = '" + UserName + "'";
					SQLconn.SQLupdate(ExeStringB8, statement);
				}
				if(NumOfTasks!=-1) {
					String ExeStringB9 = "update Member set NumOfTasks = '" + NumOfTasks + "'" + "where UserName = '" + UserName + "'";
					SQLconn.SQLupdate(ExeStringB9, statement);
				}
				if(date!=null) {
					String ExeStringB10 = "update Member set CreateTime = '" + date + "'" + "where UserName = '" + UserName + "'";
					SQLconn.SQLupdate(ExeStringB10, statement);
				}
				statement.close();
				return true;
			}
		}
	}
	
	
	/**
	 * the ops of Task table 
	 * */
	//获取一个任务的创建时间接口
	public static String getCreateTime(long Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select CreateTime from Task where TaskId = '" + (int)Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}
	
	//获取一个任务编号
	public static String getTaskId(String Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select TaskId from Task where UserName = '" + Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}

	//获取任务名
	public static String getTaskName(long Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select TaskName from Task where TaskId = '" + (int)Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}
	
	//获取任务状态
	public static String getTaskState(long Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select TaskState from Task where TaskId = '" + (int)Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}
	
	//获取任务类型
	public static String getTaskType(long Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select TaskTpye from Task where TaskId = '" + (int)Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}
	
	//获取任务This描述
	public static String getTrigger(long Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select Trigger from Task where TaskId = '" + (int)Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}
	
	//获取任务That描述
	public static String getAction(long Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select Action from Task where TaskId = '" + (int)Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}
	
	//获取任务的执行用户
	public static String getOwner(long Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select UserName from Task where TaskId = '" + (int)Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}
	
	//获取是否重复执行接口
	public static String getRepeat(long Id) throws SQLException, ClassNotFoundException {
		Statement statement = SQLconn.initialMySQL();
		String ExeString = "select Repeat from Task where TaskId = '" + (int)Id + "'";
		ResultSet tempSet = SQLconn.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			String resultString = tempSet.getString(1);
			statement.close();
			return resultString;
		}
		statement.close();
		return null;
	}
	
	
	//更新Task表
	public static boolean setTask(String ManagerPassword,long TaskId,String TaskName,String TaskState,String TaskType,String Trigger,String Action,String UserName,int Repeat,String date) throws SQLException, ClassNotFoundException {
		if(TaskId==-1) return false;
		else {
			Statement statement = SQLconn.initialMySQL();
			String ExeString = "Select TaskId from Task where TaskId=" + (int)TaskId;
			ResultSet resultset = SQLconn.SQLexe(ExeString, statement);
			if(resultset.next()==false) {
				if(TaskState==null||UserName==null||Repeat==-1||date==null) return false;
				else {
					String ExeStringA = "insert into Task values(" + 
							(int)TaskId + ",'" + TaskName + "','" 
							+ TaskState + "','" + TaskType + "','" 
							+ Trigger + "','" + Action + "','" 
							+ UserName + "'," + Repeat + ",'" 
							+ date + "')";
					SQLconn.SQLupdate(ExeStringA, statement);
					statement.close();
					return true;
				}		
			}
			else {
				if(TaskName!=null) {
					String ExeStringB1 = "update Task set TaskName = '" + TaskName + "'" + "where TaskId = " + TaskId;
					SQLconn.SQLupdate(ExeStringB1, statement);
				}
				if(TaskState!=null) {
					String ExeStringB2 = "update Task set TaskState = '" + TaskState + "'" + "where TaskId = " + TaskId;
					SQLconn.SQLupdate(ExeStringB2, statement);
				}
				if(TaskType!=null) {
					String ExeStringB3 = "update Task set TaskType = '" + TaskType + "'" + "where TaskId = " + TaskId;
					SQLconn.SQLupdate(ExeStringB3, statement);
				}
				if(Trigger!=null) {
					String ExeStringB4 = "update Task set Trigger = '" + Trigger + "'" + "where TaskId = " + TaskId;
					SQLconn.SQLupdate(ExeStringB4, statement);
				}
				if(Action!=null) {
					String ExeStringB5 = "update Task set Action = '" + Action + "'" + "where TaskId = " + TaskId;
					SQLconn.SQLupdate(ExeStringB5, statement);
				}
				if(UserName!=null) {
					String ExeStringB6 = "update Task set UserName = '" + UserName + "'" + "where TaskId = " + TaskId;
					SQLconn.SQLupdate(ExeStringB6, statement);
				}
				if(Repeat!=-1) {
					String ExeStringB7 = "update Task set Repeat = " + Repeat + "where TaskId = " + TaskId;
					SQLconn.SQLupdate(ExeStringB7, statement);
				}
				if(date!=null) {
					String ExeStringB8 = "update Task set CreateTime = '" + date + "'" + "where TaskId = " + TaskId;
					SQLconn.SQLupdate(ExeStringB8, statement);
				}
				statement.close();
				return true;
			}
		}
	}
	
	//创建任务时消费记录增加接口
	/*public static boolean AddConsume(String MemberId,String ThisId,String ThatId,Time time,double discount,double cost,double consume,Statement statement) throws ClassNotFoundException {
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
	}*/
	
	//第一次增加会员资料接口
	/*public static boolean AddPersonalData(String MemberId,String Name,Date Birthday,String City,String PhoneNumber,String E_Mail,Statement statement) throws ClassNotFoundException {
		String ExeString = "insert into PersonalData values('" + MemberId + "','" + Name + "','" + City + "','" + Birthday + "','" + PhoneNumber + "','" + E_Mail + "')";
		try {
			@SuppressWarnings("unused")
			int temp = SQL.SQLupdate(ExeString, statement);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}*/
	
	//修改会员资料接口
	/*public static boolean  ModifyPersonalData(String MemberId,String Name,Date Birthday,String City,String PhoneNumber,String E_Mail,Statement statement) throws ClassNotFoundException {
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
	}*/
	
	//获取会员资料接口
	/*public static String[] GetPersonalData(String Id,Statement statement) throws SQLException, ClassNotFoundException {
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
	}*/
	
	//获取消费记录接口
	/*public static String[] GetConsume(String Id,Statement statement) throws SQLException, ClassNotFoundException {
		String ExeString = "select ThisId,ThatId,Time,Consume from Consume where MemberId = '" + Id + "'";
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
	}*/
	
	//会员退会时删除记录接口
	/*public static boolean DeleteMember(String MemberId,Statement statement) throws ClassNotFoundException {
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
	}*/
	
	//获取正在执行的任务列表接口
	/*public static int[] GetRunTask(Statement statement) throws SQLException, ClassNotFoundException {
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
	}*/
	
	//获取任务详细信息接口
	/*public static String[] GetTask(int TaskId,Statement statement) throws SQLException, ClassNotFoundException {
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
	}*/
	
	//获取短消息数目
	/*public static int GetMessageCount(String id,Statement statement) throws SQLException, ClassNotFoundException {

		String ExeString = "select count(*) from Message where MemberId='" + id + "'";
		ResultSet tempSet = SQL.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			return tempSet.getInt(1);
		}
		return 0;
	}*/
	
	//获取短消息信息
	/*public static String[] GetMessage(String id,Statement statement) throws SQLException, ClassNotFoundException {
		String ExeString = "select MessageId,Message,SendMsgId from message where MemberId = '" + id + "'";
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
	}*/
	
	
	
	//建立一个任务
	/*public static boolean CteateTask(String MemberId,int TaskId,String ThisId,String ThatId,Time time,double discount,double consume,int integral,Statement statement) throws ClassNotFoundException {
		String ExeString1 = "insert into Task values(" + TaskId + ",'" + MemberId + "','" + ThisId + "','" + ThatId + "','" + time + "',0)";
		String ExeString2 = "update Account set Money=Money-" + consume + ",Integral=Integral+" + integral; 
  		try {
			@SuppressWarnings("unused")
			int temp1 = SQL.SQLupdate(ExeString1, statement);
			@SuppressWarnings("unused")
			int temp2 = SQL.SQLupdate(ExeString2, statement);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}*/
	
	//获取单人所建立的任务数量
	/*public static int GetTaskCount(String id,Statement statement) throws SQLException, ClassNotFoundException {
		String ExeString = "select count(*) from Task where MemberId='" + id + "'";
		ResultSet tempSet = SQL.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			return tempSet.getInt(1);
		}
		return 0;
	}*/
	
	//获取一个人所拥有的任务详细情况
	/*public static String[] GetMemberTask(String id,Statement statement) throws SQLException, ClassNotFoundException {
		String ExeString = "select TaskId,ThisId,ThatId,Time,IfRun from Task where MemberId='" + id + "'";
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
	}*/
	
	//获取单人消费记录数量
	/*public static int GetConsumeCount(String id,Statement statement) throws SQLException, ClassNotFoundException {
		String ExeString = "select count(*) from Consume where MemberId='" + id + "'";
		ResultSet tempSet = SQL.SQLexe(ExeString, statement);
		while(tempSet.next()) {
			return tempSet.getInt(1);
		}
		return 0;
	}*/
	
	//充值时接口
	/*public static boolean AddMoney(String id,double AddMoney,Statement statement) throws SQLException {
		String ExeString = "update Account set Money=Money+" + AddMoney + "where MemberId ='" + id + "'";
		try {
			@SuppressWarnings("unused")
			int temp = SQL.SQLupdate(ExeString, statement);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}*/
	
	//删除一条短消息的接口
	/*public static boolean DeleteMessage(int id,Statement statement) throws SQLException {
		String ExeString = "delete from Message where MessageId=" + id;
		try {
			@SuppressWarnings("unused")
			int temp = SQL.SQLupdate(ExeString, statement);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}*/
	
	//删除一条任务接口
	/*public static boolean DeleteTask(int id,Statement statement) throws SQLException {
		String ExeString = "delete from Task where TaskId=" + id;
		try {
			@SuppressWarnings("unused")
			int temp = SQL.SQLupdate(ExeString, statement);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}*/
}
