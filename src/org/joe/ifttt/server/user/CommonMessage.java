package org.joe.ifttt.server.user;

public class CommonMessage {
	private int messageNo;
	private String senderString;
	private String receiverString;
	private String content;
	
	public CommonMessage(String sender, String recver, String content) {
		this.senderString = sender;
		this.receiverString = recver;
		this.content = content;
	}

	public CommonMessage(int i, String sender, String recver,
			String content) {
		this.messageNo = i;
		this.senderString = sender;
		this.receiverString = recver;
		this.content = content;
		// TODO Auto-generated constructor stub
	}
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
	public int getMessageNo() {
		return messageNo;
	}
	public void setMessageNo(int messageNo) {
		this.messageNo = messageNo;
	}
}
