package org.joe.ifttt.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joe.ifttt.server.manager.UserManager;

import com.sun.xml.internal.stream.writers.XMLWriter;


public class SendMessage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SendMessage() {
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
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
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
		//get the new message
		String receiver = request.getParameter("reciever");
		String message = request.getParameter("message");
		System.out.println("**********" + request.getParameter("authcode") + "send message");
		Long authCode = Long.parseLong(request.getParameter("authcode"));
		System.out.println("**********" + authCode + "send message");
		try {
			UserManager.getInstance().sendMessgaeTo(authCode.longValue(), receiver, message);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//reflesh the list of the messages
		System.out.println("receiver:" + receiver + "message:" + message);
		String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + 
						"<msg>" + 
						"<reciever>" +"joa"+ "</reciever>" + 
						"<content>" + "i love you" + "</content>" + 
						"</msg>";
	    response.setContentType("text/xml;charset=utf-8");  
	    
		PrintWriter out = response.getWriter();
		out.println(xmlString);
		out.close();
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
