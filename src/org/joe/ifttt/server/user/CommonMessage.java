package org.joe.ifttt.server.user;

public class CommonMessage {
	private Long messageNo;
	private String senderString;
	private String receiverString;
	private String content;
	public String getReceiverString() {
		return receiverString;
	}
	public void setReceiverString(String receiverString) {
		this.receiverString = receiverString;
	}
	public String getSenderString() {
		return senderString;
	}
	public void setSenderString(String senderString) {
		this.senderString = senderString;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String toString() {
		return "Send to:" + receiverString + ", from: " + senderString + 
				", content: \" " + content + " \"";
	}
	public Long getMessageNo() {
		return messageNo;
	}
	public void setMessageNo(Long messageNo) {
		this.messageNo = messageNo;
	}
}
