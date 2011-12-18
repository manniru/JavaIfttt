package org.joe.ifttt.server.content;
/**
 * File: 			Content.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/10
 * Description:
 * The definition Common Content
 * this class Package the contents in a class 
 */
public class CommonContent {
	/**a common content class*/
	private String textString;
	private String picString;
	private String videoString;
	private String attachString;
	private String[] formatStrings;

	public CommonContent() {
		// TODO Auto-generated constructor stub
	}
	
	public void setTextString(String textString) {
		this.textString = textString;
	}

	public String getTextString() {
		return textString;
	}

	public void setPicString(String picString) {
		this.picString = picString;
	}

	public String getPicString() {
		return picString;
	}

	public void setVideoString(String videoString) {
		this.videoString = videoString;
	}

	public String getVideoString() {
		return videoString;
	}

	public void setFormatStrings(String[] formatStrings) {
		this.formatStrings = formatStrings;
	}

	public String[] getFormatStrings() {
		return formatStrings;
	}

	public String getAttachString() {
		return attachString;
	}

	public void setAttachString(String attachString) {
		this.attachString = attachString;
	}
	public String toString() {
		return "CONTENT: \n" + textString + "\n";
	}
}

