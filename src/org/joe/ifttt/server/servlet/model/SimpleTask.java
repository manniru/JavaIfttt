package org.joe.ifttt.server.servlet.model;

public class SimpleTask {
	private String taskname;
	private String params;
	private long authcode;
	public SimpleTask (String taskname, String params, long authcode) {
		
		this.taskname = taskname;
		this.params = params;
		this.authcode = authcode;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public long getAuthcode() {
		return authcode;
	}
	public void setAuthcode(long authcode) {
		this.authcode = authcode;
	}
}
