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

		UserManager.getInstance().createUser("tong", "111", "t@w.com");
		long authCode = UserManager.getInstance().loginUser("tong", "111");
		
		ChannelUser weiboUser = new ChannelUser();
		weiboUser.setUsername("*");
		weiboUser.setPassword("*");
		UserManager.getInstance().addChannel(authCode, "weibo", weiboUser);
		
		CommonContent content = new CommonContent();
		content.setTextString("test for love: java-ifttt server" + (new Date()).toLocaleString());
	//	TimeEvent timeEvent = new TimeEvent("2011", "12", "10", "17", "00");
		GetWeiboEvent weiboEvent = new GetWeiboEvent("joenju", null, "love");
		UpdateWeiboAction weiboAction = new UpdateWeiboAction(content);
	//	long taskId = TaskManager.getInstance().insertTask(authCode, "test", 
	//			timeEvent, "EVENT-time-after", weiboAction, "ACTION-weibo-update", false);
				
		long taskId = TaskManager.getInstance().insertTask(authCode, "test", 
				weiboEvent, "EVENT-weibo-get", weiboAction, "ACTION-weibo-update", false);
		
		TaskManager.getInstance().startTask(taskId);
		
	}
}