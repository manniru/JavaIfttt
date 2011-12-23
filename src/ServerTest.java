import java.util.Date;

import org.joe.ifttt.server.content.CommonContent;
import org.joe.ifttt.server.manager.TaskManager;
import org.joe.ifttt.server.manager.UserManager;
import org.joe.ifttt.server.task.action.UpdateWeiboAction;
import org.joe.ifttt.server.task.event.GetWeiboEvent;
import org.joe.ifttt.server.task.event.TimeEvent;
import org.joe.ifttt.server.user.ChannelUser;


public class ServerTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserManager.getInstance().createUser("tong", "111", "kakak","t@w.com");
		long authCode = UserManager.getInstance().loginUser("tong", "111");
		
		ChannelUser weiboUser = new ChannelUser();
		weiboUser.setUsername("*");
		weiboUser.setPassword("*");
		UserManager.getInstance().addChannel(authCode, "weibo", weiboUser);
		
		CommonContent content1 = new CommonContent();
		content1.setTextString("test for love: java-ifttt server" + (new Date()).toLocaleString());
		GetWeiboEvent weiboEvent1 = new GetWeiboEvent("joenju", null, "love");
		UpdateWeiboAction weiboAction1 = new UpdateWeiboAction(content1);
		long taskId1 = TaskManager.getInstance().insertTask(authCode, "test1", 
				weiboEvent1, "EVENT-weibo-get", weiboAction1, "ACTION-weibo-update", false);
		
		CommonContent content2 = new CommonContent();
		content2.setTextString("java-ifttt server test: love you~mua~"  + (new Date()).toLocaleString());	
		TimeEvent timeEvent = new TimeEvent("2011", "12", "22", "01", "29");
		UpdateWeiboAction weiboAction2 = new UpdateWeiboAction(content2);
		long taskId2 = TaskManager.getInstance().insertTask(authCode, "test2", 
				timeEvent, "EVENT-time-after", weiboAction2, "ACTION-weibo-update", false);
	//	long taskId = TaskManager.getInstance().insertTask(authCode, "test", 
	//			timeEvent, "EVENT-time-after", weiboAction, "ACTION-weibo-update", false);

	//	TimeEvent timeEvent = new TimeEvent("2011", "12", "10", "17", "00");	
		TaskManager.getInstance().startTask(taskId1);
		TaskManager.getInstance().startTask(taskId2);
		
	}
}