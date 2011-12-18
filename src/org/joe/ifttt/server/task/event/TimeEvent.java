package org.joe.ifttt.server.task.event;
/**
 * File: 			TimeEvent.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/12
 * Description:
 * The definition of time event, we will define some time events:
 * 		1.after the time; 		(DONE)
 * 		2.same time in days; 	(NOT DONE)
 * 		3.same day in weeks;	(NOT DONE)
 * 		4.same day in month;	(NOT DONE)
 * 		5.same time in hours;	(NOT DONE)	
 * 		...
 * We want to use the fly-weight scheme in the date object, 
 * for we can use the same date in my system, but we don't do 
 * that by now;
 */
import java.util.Date;

import org.joe.ifttt.server.channel.Channel;

public class TimeEvent extends BasicEvent implements This{
	private Date date;
	public TimeEvent() {
		// TODO Auto-generated constructor stub
	}
	public TimeEvent(Date date) {
		this.date = date;
	}
	public TimeEvent(String year, String month, String day, String hour, String minute) {
		// TODO Auto-generated constructor stub
		date = new Date(Integer.parseInt(year) - 1900 , Integer.parseInt(month) - 1,
				Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(minute));
		//System.out.println(date.toLocaleString());
	}
	public String toString() {
		return super.toString() + "Time to do: " + date.toLocaleString() + "\n";
	}
	@Override
	public boolean thisEvent() {
		// TODO Auto-generated method stub
		Date nowDate = new Date();
		
		System.out.println("now: " + nowDate.toLocaleString());
		System.out.println("set: " + date.toLocaleString());
		if(nowDate.after(date))
			return true;
		return false;
	}
	@Override
	public void setChannel(Channel channel) {
		// TODO Auto-generated method stub
	}
}
