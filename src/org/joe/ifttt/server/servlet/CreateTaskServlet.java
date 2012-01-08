package org.joe.ifttt.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

import org.joe.ifttt.server.content.CommonContent;
import org.joe.ifttt.server.manager.TaskManager;
import org.joe.ifttt.server.servlet.model.SimpleTask;
import org.joe.ifttt.server.task.action.UpdateWeiboAction;
import org.joe.ifttt.server.task.event.TimeEvent;

public class CreateTaskServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CreateTaskServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String taskname = request.getParameter("taskname");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String textContent = request.getParameter("textcontent");
		long authcode = Long.parseLong(request.getParameter("authcode"));
		
		HttpSession httpSession = request.getSession();
		String params = date + "|" + time + "|" + textContent;
		SimpleTask task = new SimpleTask(taskname, params, authcode);
		httpSession.setAttribute("task", task);
		
		out.println("You enter the following data");
		out.println("<p>taskname	: " + taskname);
		out.println("<p>date		: " + date);
		out.println("<p>time		: " + time);
		out.println("<p>textContent	: " + textContent);
		out.println("<p>authcode	: " + authcode);
		
		out.println("<p><form method=\"post\" action=" + 
				 "CreateTaskServlet");
		out.println("<p><input type=\"submit\" value=\"Confirm\" >");
		out.println("</form>");
		
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession httpSession = request.getSession();
		
		SimpleTask task = (SimpleTask)(httpSession.getAttribute("task"));
		
		String[] params = task.getParams().split("|");
		
		CommonContent content = new CommonContent();
		content.setTextString(params[2] + (new Date()).toLocaleString());
		String[] date = params[0].split(",");
		String[] time = params[1].split(":");
		TimeEvent timeEvent = new TimeEvent(date[0], date[1], date[2], time[0], time[1]);
		UpdateWeiboAction weiboAction = new UpdateWeiboAction(content);
		try {
			long taskId = TaskManager.getInstance().insertTask(task.getAuthcode(), task.getTaskname(), 
					timeEvent, "EVENT-time-after", params[0] + "|" + params[1],
					weiboAction, "ACTION-weibo-update", params[2], false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("taskname : " + task.getTaskname() + "\n" 
				  + "date : " + 	params[0] + "\n"
				  + "time	  : " + params[1] + "\n"
				  + "textConten:" + params[2]);
	}

	private void createTask(SimpleTask task) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
