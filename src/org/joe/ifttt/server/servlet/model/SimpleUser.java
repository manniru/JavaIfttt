package org.joe.ifttt.server.servlet.model;

public class SimpleUser {
	private String username;
	private String password;
	private String mail;
	
	public SimpleUser() {
		// TODO Auto-generated constructor stub
	}

	public SimpleUser(String username, String password, String mail) {
		this.username = username;
		this.password = password;
		this.mail = mail;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

}
