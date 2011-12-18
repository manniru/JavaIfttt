package org.joe.ifttt.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joe.ifttt.server.manager.UserManager;
import org.joe.ifttt.server.servlet.model.SimpleUser;

import sun.security.jgss.LoginConfigImpl;

public class UserLoginServlet extends HttpServlet {
	/**
	 * Constructor of the object.
	 */
	public UserLoginServlet() {
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
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		SimpleUser user = new SimpleUser(username, password, null);
		
		HttpSession httpSession = request.getSession();
		long userhash = LoginUser(user);
		if (userhash == -1) {
			out.println("Password Login Error!");
		}
		else if (userhash == -2){
			out.println("Username Login Error!");
		}
		else {
			out.println("Login Success!");
			httpSession.setAttribute("userhash", userhash);
		}
		out.println("current user: " + UserManager.getInstance().getNumOfUsers());
		
		out.close();
	}

	private long LoginUser(SimpleUser user) {
		// TODO Auto-generated method stub
		return UserManager.getInstance().loginUser(user.getUsername(), user.getPassword());
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
